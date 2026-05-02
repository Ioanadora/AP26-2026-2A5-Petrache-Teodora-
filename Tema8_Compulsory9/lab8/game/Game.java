package com.compulsory8.lab8.game;

import com.compulsory8.lab8.model.Maze;

import java.util.*;

public class Game {

    private final Maze maze;
    private final boolean[][] occupied;
    private volatile boolean gameOver = false;
    private Position bunnyPos;
    private boolean started = false;

    public synchronized void startGame() {
        started = true;
        notifyAll();
    }
    public synchronized void waitForStart() {
        while (!started) {
            try {
                wait();
            } catch (InterruptedException ignored) {}
        }
    }

    public Game(Maze maze) {
        this.maze = maze;
        this.occupied = new boolean[maze.getRows()][maze.getCols()];
    }

    public synchronized boolean move(Position oldPos, Position newPos) {
        if (gameOver) return false;
        if (occupied[newPos.row][newPos.col]) return false;


        occupied[oldPos.row][oldPos.col] = false;
        occupied[newPos.row][newPos.col] = true;
        return true;}

    public synchronized void setBunny(Position pos) {
        bunnyPos = pos;
        occupied[pos.row][pos.col] = true;}
    public synchronized Position getBunnyPos() {
        return bunnyPos;
    }

    public synchronized void catchBunny() {
        gameOver = true;
        System.out.println("[ROBOT] Bunny caught");
    }
    public synchronized boolean isGameOver() {
        return gameOver;
    }
    public Maze getMaze() {
        return maze;
    }
    public synchronized void stopGame(String message) {
        if (!gameOver) {
            gameOver = true;
            System.out.println(message);
        }
    }
}