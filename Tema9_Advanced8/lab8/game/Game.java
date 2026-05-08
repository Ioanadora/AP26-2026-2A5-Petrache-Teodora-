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
    private Position lastKnownBunnyPos;
    private Bunny bunny;
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
        bunny = b;
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
    public synchronized void setBunny(Position pos) {
        bunnyPos = pos;
    }
    public synchronized Position getBunnyPos() {return bunnyPos;}
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

    public synchronized void updateRobot(Position oldPos, Position newPos) {
        robotPositions.remove(oldPos);
        robotPositions.add(newPos);
        occupied[oldPos.row][oldPos.col] = false;
        occupied[newPos.row][newPos.col] = true;
    }

    public synchronized List<Position> getRobotPositions() {
        return new ArrayList<>(robotPositions);
    }
    public synchronized void updateBunny(Position pos) {
        bunnyPos = pos;
    }
    public synchronized boolean isVisited(Position pos) {
        return sharedVisited.contains(pos);
    }
    public synchronized void markVisited(Position pos) {
        sharedVisited.add(pos);
    }
    public synchronized void shareSighting(Position pos) {
        if (lastKnownBunnyPos == null || !lastKnownBunnyPos.equals(pos)) {
            this.lastKnownBunnyPos = pos;
            System.out.println("[ROBOT] Shared bunny sighting at " + pos);
        }
    }
    public synchronized Position getLastKnownBunnyPos() {
        return lastKnownBunnyPos;
    }
    public void speedUpAll() {
        System.out.println("[COMMAND] Speeding up all ");
        if (bunny != null) bunny.speedUp();
        robots.forEach(Robot::speedUp);
    }
    public void slowDownAll() {
        System.out.println("[COMMAND] Slowing down all ");
        if (bunny != null) bunny.slowDown();
        robots.forEach(Robot::slowDown);
    }
    public void speedUpRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Speeding up Robot " + robot);
            robots.get(robot).speedUp();
        }
    }
    public void speedUpBunny() {
        if (bunny != null) {
            System.out.println("[COMMAND] Speeding up Bunny");
            bunny.speedUp();
        }
    }
    public void slowDownBunny() {
        if (bunny != null) {
            System.out.println("[COMMAND] Slowing down Bunny");
            bunny.slowDown();
        }
    }
    public void slowDownRobot(int robot) {
        if (robot >= 0 && robot < robots.size()) {
            System.out.println("[COMMAND] Slowing down Robot " + robot);
            robots.get(robot).slowDown();
        }
    }
    public void pauseBunny() {
        if (bunny != null) {
            System.out.println("[COMMAND] Pausing Bunny");
            bunny.pauseAgent();
        }
    }
    public void resumeBunny() {
        if (bunny != null) {
            System.out.println("[COMMAND] Resuming Bunny");
            bunny.resumeAgent();
        }
    }
    public void pauseAll() {
        System.out.println("[COMMAND] Pausing all entities");
        if (bunny != null)
            bunny.pauseAgent();
        robots.forEach(Robot::pauseAgent);
    }
    public void resumeAll() {
        System.out.println("[COMMAND] Resuming all entities");
        if (bunny != null)
            bunny.resumeAgent();
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
        builder.append("Bunny: ").append(bunnyPos).append(" | Robots: ");
        for (int robot = 0; robot < robots.size(); robot++) {
            builder.append("R").append(robot).append(": ").append(robots.get(robot).getPosition());
            if (robot < robots.size() - 1)
                builder.append(", ");
        }return builder.toString();
    }
}