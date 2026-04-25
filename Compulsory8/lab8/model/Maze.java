package com.compulsory8.lab8.model;
import java.io.Serializable;
import java.util.Random;

public class Maze implements Serializable {
    private final int rows;
    private final int cols;
    private final Cell[][] grid;
    private final Random random = new Random();
    public Maze(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        init();
    }
    private void init(){

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                grid[i][j] = new Cell(i,j);
    }
    public Cell[][] getGrid() {return grid;}
    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public void generate() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (i > 0 && random.nextBoolean()) {
                    grid[i][j].top = false;
                    grid[i - 1][j].bottom = false;
                }

                if (j > 0 && random.nextBoolean()) {
                    grid[i][j].left = false;
                    grid[i][j - 1].right = false;
                }
            }
        }
    }
    public void reset() {
        init();
    }
}
