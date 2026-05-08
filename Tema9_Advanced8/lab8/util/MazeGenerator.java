package com.compulsory8.lab8.util;

import com.compulsory8.lab8.model.Cell;
import com.compulsory8.lab8.model.Maze;
import com.compulsory8.lab8.view.MazeCanvas;
import javafx.application.Platform;

import java.util.*;

public class MazeGenerator {

    private final Maze maze;
    private final MazeCanvas canvas;
    private int delay = 50;

    public MazeGenerator(Maze maze, MazeCanvas canvas) {
        this.maze = maze;
        this.canvas = canvas;
    }



    public void setDelay(int delay) {
        this.delay = delay;
    }



    private void recursiveBacktrack(int rows, int colums, boolean[][] visited) {
        visited[rows][colums] = true;
        List<int[]> neighbors = getUnvisitedNeighbors(rows, colums, visited);
        Collections.shuffle(neighbors);

        for (int[] next : neighbors) {
            int nextRow = next[0];
            int nextCol = next[1];
            if (!visited[nextRow][nextCol]) {
                removeWall(rows, colums, nextRow, nextCol);
                Platform.runLater(canvas::draw);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException exception) {
                    Thread.currentThread().interrupt();
                    return;
                }
                recursiveBacktrack(nextRow, nextCol, visited);
            }
        }}
    public void generateAnimated() {
        new Thread(() -> {
            maze.resetWalls();
            recursiveBacktrack(0, 0, new boolean[maze.getRows()][maze.getCols()]);
            Platform.runLater(canvas::draw);
            System.out.println(" Maze generation ");
        }).start();
    }


    private List<int[]> getUnvisitedNeighbors(int row, int col, boolean[][] visited) {
        List<int[]> neighbors = new ArrayList<>();
        int rows = maze.getRows();
        int cols = maze.getCols();
        if (row > 0 && !visited[row - 1][col]) neighbors.add(new int[]{row - 1, col});
        if (row < rows - 1 && !visited[row + 1][col]) neighbors.add(new int[]{row + 1, col});
        if (col > 0 && !visited[row][col - 1]) neighbors.add(new int[]{row, col - 1});
        if (col < cols - 1 && !visited[row][col + 1]) neighbors.add(new int[]{row, col + 1});
        return neighbors;
    }
    private void removeWall(int row1, int col1, int roe2, int col2) {
        Cell cell1 = maze.getGrid()[row1][col1];
        Cell cell2 = maze.getGrid()[roe2][col2];
        if (row1 == roe2) {
            if (col1 < col2) {
                cell1.right = false;
                cell2.left = false;
            } else {
                cell1.left = false;
                cell2.right = false;
            }
        } else {if (row1 < roe2) {
                cell1.bottom = false;
                cell2.top = false;
            } else {
                cell1.top = false;
                cell2.bottom = false;
            }
        }
    }
}
