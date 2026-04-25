package com.compulsory8.lab8.controller;

import com.compulsory8.lab8.model.Maze;
import com.compulsory8.lab8.view.MazeCanvas;
import com.compulsory8.lab8.util.*;
import com.compulsory8.lab8.game.*;
import java.util.Random;

public class MazeController {

    private Maze maze;
    private final MazeCanvas canvas;
    private Game game;
    private boolean simulationStarted = false;

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
        boolean ok = MazeSolver.isSolvable(maze);
        System.out.println(ok ? "Solvable" : "Not solvable");
    }

    public void save() {
        try {
            MazeSerializer.save(maze, "maze.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            maze = MazeSerializer.load("maze.ser");
            canvas.setMaze(maze);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void export() {
        try {
            ImageExporter.export(canvas, "maze.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startGame() {
        if (maze == null) return;

        boolean ok = MazeSolver.isSolvable(maze);

        if (!ok) {
            System.out.println("Maze NOT solvable!");
            return;
        }

        System.out.println("Maze is valid. Starting simulation...");

        game = new Game(maze);

        Bunny bunny = new Bunny(game, new Position(0, 0));

        Robot r1 = new Robot(game, randomPos());
        Robot r2 = new Robot(game, randomPos());
        Robot r3 = new Robot(game, randomPos());

        game.startGame();
        bunny.start();
        r1.start();
        r2.start();
        r3.start();

        simulationStarted = true;
    }
    private Position randomPos() {
        Random rand = new Random();
        int r, c;

        do {
            r = rand.nextInt(maze.getRows());
            c = rand.nextInt(maze.getCols());
        } while (r == 0 && c == 0); // nu peste bunny

        return new Position(r, c);
    }
}