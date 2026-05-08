package com.compulsory8.lab8.controller;

import com.compulsory8.lab8.model.Maze;
import com.compulsory8.lab8.view.MazeCanvas;
import com.compulsory8.lab8.util.*;
import com.compulsory8.lab8.game.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MazeController {
    private Maze maze;
    private final MazeCanvas canvas;
    private Game game;
    private boolean simulationStarted = false;
    private final Random rand = new Random();
    private int genDelay = 50;
    public MazeController(MazeCanvas canvas) {
        this.canvas = canvas;
    }
    public void createMaze(int rows, int cols) {
        maze = new Maze(rows, cols);
        canvas.setMaze(maze);
    }
    public void generate() {
        if (maze != null) {
            maze.generate();
            canvas.draw();
        }}
    public void generateAnimated() {
        if (maze != null) {
            MazeGenerator generator = new MazeGenerator(maze, canvas);
            generator.setDelay(genDelay);
            generator.generateAnimated();
        }
    }

    public void setGenDelay(int delay) {
        this.genDelay = delay;
    }
    public void reset() {
        if (maze != null) {
            maze.reset();
            canvas.draw();}
    }
    public void validate() {
        boolean solvable = MazeSolver.isSolvable(maze);
        System.out.println(solvable ? "Solvable" : "Not solvable");
    }
    public void save() {
        try {
            MazeSerializer.save(maze);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void load() {
        try {
            maze = MazeSerializer.load();
            canvas.setMaze(maze);
        } catch (Exception exception) {
            exception.printStackTrace();
        }}
    public void export() {
        try {
            ImageExporter.export(canvas, canvas.getScene().getWindow());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void startGame() {
        if (maze == null) return;

        if (game != null) {
            game.stopGame("game stop");
        }



        boolean solvable = MazeSolver.isSolvable(maze);
        if (!solvable) {
            System.out.println("Maze not solvable");
            return;
        }

        System.out.println("Maze valid-starting ");
        game = new Game(maze);
        canvas.setGame(game);
        GameMonitor monitor = new GameMonitor(game, 60);
        monitor.setDaemon(true);
        monitor.start();
        Set<Position> used = new HashSet<>();

        Bunny bunny0 = new Bunny(game, new Position(0, 0), canvas);
        Robot robot = new Robot(game, randomPos(used), canvas);
        Robot robot1 = new Robot(game, randomPos(used), canvas);
        Robot robot2 = new Robot(game, randomPos(used), canvas);
        game.registerBunny(bunny0);
        game.registerRobot(robot);
        game.registerRobot(robot1);
        game.registerRobot(robot2);
        game.startGame();
        bunny0.start();
        robot.start();
        robot1.start();
        robot2.start();
        simulationStarted = true;
        canvas.draw();
    }
    private Position randomPos(Set<Position> used) {
        int row, col;
        Position position;
        do {
            row = rand.nextInt(maze.getRows());
            col = rand.nextInt(maze.getCols());
            position = new Position(row, col);
        } while ((row == 0 && col == 0) || used.contains(position));
        used.add(position);
        return position;
    }
    public void speedUpAll() {
        if (game != null) game.speedUpAll();
    }
    public void slowDownAll() {
        if (game != null) game.slowDownAll();
    }
    public void pauseAll() {
        if (game != null) game.pauseAll();
    }
    public void resumeAll() {
        if (game != null) game.resumeAll();
    }
    public void speedUpRobot(int i) {
        if (game != null) game.speedUpRobot(i);
    }
    public void speedUpBunny() {
        if (game != null) game.speedUpBunny();
    }
    public void pauseBunny() {
        if (game != null) game.pauseBunny();
    }
    public void resumeBunny() {
        if (game != null) game.resumeBunny();
    }
    public void slowDownBunny() {
        if (game != null) game.slowDownBunny();
    }
    public void pauseRobot(int i) {
        if (game != null) game.pauseRobot(i);
    }
    public void resumeRobot(int i) {
        if (game != null) game.resumeRobot(i);
    }
    public void slowDownRobot(int i) {
        if (game != null) game.slowDownRobot(i);
    }
}