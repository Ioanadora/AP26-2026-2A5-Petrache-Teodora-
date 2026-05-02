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
    public void reset() {
        if (maze != null) {
            maze.reset();
            canvas.draw();
        }
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
        }
    }

    public void export() {
        try {
            ImageExporter.export(canvas, canvas.getScene().getWindow());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public void startGame() {
        if (maze == null) return;

        boolean solvable = MazeSolver.isSolvable(maze);

        if (!solvable) {
            System.out.println("Maze not solvable");
            return;
        }

        System.out.println("Maze valid-starting ");

        game = new Game(maze);
        Set<Position> used = new HashSet<>();
        Bunny bunny = new Bunny(game, new Position(0, 0));
        Robot robot = new Robot(game, randomPos(used));
        Robot robot1 = new Robot(game, randomPos(used));
        Robot robot2 = new Robot(game, randomPos(used));
        game.startGame();
        bunny.start();
        robot.start();
        robot1.start();
        robot2.start();
        simulationStarted = true;
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
}