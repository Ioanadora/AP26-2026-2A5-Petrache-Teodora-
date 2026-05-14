package com.compulsory8.lab8.game;

import com.compulsory8.lab8.model.Maze;

import java.util.*;

public class Game {

    private final Maze maze;
    private final boolean[][] occupied;
    private volatile boolean gameOver = false;
    private Position bunnyPos;
    private boolean started = false;
    private final List<Position> robotPositions = new ArrayList<>();
    private final Set<Position> sharedVisited = new HashSet<>();
    // asta ToT :( o_o
    private final Map<Bunny, Position> lastKnownBunnyPositions = new HashMap<>();
    // asta
    private final List<Bunny> bunnies = new ArrayList<>();
    private final List<Robot> robots = new ArrayList<>();

    public synchronized void startGame() {
        started = true;
        notifyAll();
    }
    public synchronized void waitForStart() {
        while (!started) {
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }}
    public Game(Maze maze) {
        this.maze = maze;
        this.occupied = new boolean[maze.getRows()][maze.getCols()];
    }

    public void registerBunny(Bunny b) {
        bunnies.add(b);
    }
    public void registerRobot(Robot r) {
        robots.add(r);
        addRobot(r.getPosition());
    }
    public synchronized boolean move(Position oldPos, Position newPos) {

        if (gameOver)
            return false;

        boolean isRobot = Thread.currentThread() instanceof Robot;

        if (isRobot) {

            if (occupied[newPos.row][newPos.col] && !newPos.equals(bunnyPos)) {
                return false;
            }

            occupied[oldPos.row][oldPos.col] = false;
            occupied[newPos.row][newPos.col] = true;
        } else {
            if (occupied[newPos.row][newPos.col]) {
                return false;
            }
        }

        return true;
    }
    public synchronized void setBunny(Bunny bunny, Position pos) { bunnyPos = pos;}

    public synchronized List<Position> getAllBunnyPositions() {
        List<Position> positions = new ArrayList<>();
        for (Bunny b : bunnies) {
            positions.add(b.getPosition());
        }
        return positions;
    }
    /*public synchronized void catchBunny() {
        gameOver = true;
        System.out.println("[ROBOT] Bunny caught");
    }*/
    public synchronized boolean isGameOver() {return gameOver;}
    public synchronized void endGame(String msg) {
        if (!gameOver) {
            gameOver = true;
            System.out.println(msg);
        }
    }
    /*public synchronized void checkCollision(Position pos) {
        if (pos.equals(bunnyPos)) {
            endGame("Robot wins");
        }
    }*/

    public Maze getMaze() {return maze;}


    public synchronized void stopGame(String message) {
        if (!gameOver) {
            gameOver = true;
            System.out.println(message);
        }
    }

    public synchronized void addRobot(Position pos) {
        robotPositions.add(pos);
        occupied[pos.row][pos.col] = true;
    }
    public List<Robot> getRobots() {
        return robots;
    }
    public List<Bunny> getBunnies() {
        return bunnies;
    }

    public synchronized void updateRobot(Position oldPos, Position newPos) {
        robotPositions.remove(oldPos);
        robotPositions.add(newPos);
        occupied[oldPos.row][oldPos.col] = false;
        occupied[newPos.row][newPos.col] = true;
    }

    public synchronized boolean isVisited(Position pos) {
        return sharedVisited.contains(pos);
    }
    public synchronized void markVisited(Position pos) {
        sharedVisited.add(pos);
    }

    public synchronized List<Position> getRobotPositions() {
        return new ArrayList<>(robotPositions);
    }
    public synchronized void updateBunny(Bunny bunny, Position pos) {
        bunnyPos = pos;

    }

    // atsa
    public synchronized void shareSighting(Bunny bunny, Position pos) {
        lastKnownBunnyPositions.put(bunny, pos);
        System.out.println("[ROBOT] bunny at " + pos);
    }

    // asta
    public synchronized Position getNearestSightedBunny(Position robotPos) {
        Position nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Position pos : lastKnownBunnyPositions.values()) {
            double dist = Math.abs(robotPos.row - pos.row) + Math.abs(robotPos.col - pos.col);
            if (dist < minDistance) {
                minDistance = dist;
                nearest = pos;
            }
        }
        return nearest;
    }

    // asta
    public synchronized void removeBunny(Bunny bunny) {
        bunnies.remove(bunny);
        lastKnownBunnyPositions.remove(bunny);
        if (bunnies.isEmpty()) {
            endGame("Robot wins ");
        }
    }

    public synchronized void clearSighting(Position pos) {
        lastKnownBunnyPositions.values().removeIf(p -> p.equals(pos));
    }
    public void speedUpAll() {
        System.out.println("[COMMAND] Speeding up all ");
        bunnies.forEach(Bunny::speedUp);
        robots.forEach(Robot::speedUp);
    }
    public void slowDownAll() {
        System.out.println("[COMMAND] Slowing down all ");
        bunnies.forEach(Bunny::slowDown);
        robots.forEach(Robot::slowDown);
    }
    public void speedUpRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Speeding up Robot " + robot);
            robots.get(robot).speedUp();
        }
    }
    public void speedUpBunny() {
        System.out.println("[COMMAND] Speeding up Bunnies");
        bunnies.forEach(Bunny::speedUp);
    }
    public void slowDownBunny() {
        System.out.println("[COMMAND] Slowing down Bunnies");
        bunnies.forEach(Bunny::slowDown);
    }
    public void slowDownRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Slowing down Robot " + robot);
            robots.get(robot).slowDown();
        }
    }
    public void pauseBunny() {
        System.out.println("[COMMAND] Pausing Bunnies");
        bunnies.forEach(Bunny::pauseAgent);
    }
    public void resumeBunny() {
        System.out.println("[COMMAND] Resuming Bunnies");
        bunnies.forEach(Bunny::resumeAgent);
    }
    public void pauseAll() {
        System.out.println("[COMMAND] Pausing all entities");
        bunnies.forEach(Bunny::pauseAgent);
        robots.forEach(Robot::pauseAgent);
    }
    public void resumeAll() {
        System.out.println("[COMMAND] Resuming all entities");
        bunnies.forEach(Bunny::resumeAgent);
        robots.forEach(Robot::resumeAgent);
    }
    public void pauseRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Pausing Robot " + robot);
            robots.get(robot).pauseAgent();
        }
    }
    public void resumeRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Resuming Robot " + robot);
            robots.get(robot).resumeAgent();
        }
    }
    public synchronized String getGameState() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bunnies: ").append(bunnies.size()).append(" | Robots: ").append(robots.size());
        return builder.toString();
    }
}