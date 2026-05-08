package com.compulsory8.lab8.util;

import com.compulsory8.lab8.model.*;

import java.util.*;

public class MazeSolver {

    public static boolean isSolvable(Maze maze) {
        int rows = maze.getRows();
        int cols = maze.getCols();
        boolean[][] visited = new boolean[rows][cols];
        Queue<Cell> linkedList = new LinkedList<>();



        linkedList.add(maze.getGrid()[0][0]);
        visited[0][0] = true;

        while (!linkedList.isEmpty()) {
            Cell cell = linkedList.poll();

            if (cell.row == rows - 1 && cell.col == cols - 1)
                return true;
            if (!cell.top && !visited[cell.row - 1][cell.col]&& cell.row > 0) {
                visited[cell.row - 1][cell.col] = true;
                linkedList.add(maze.getGrid()[cell.row - 1][cell.col]);}
            if (!cell.bottom && cell.row + 1 < rows && !visited[cell.row + 1][cell.col]) {
                visited[cell.row + 1][cell.col] = true;
                linkedList.add(maze.getGrid()[cell.row + 1][cell.col]);
            }
            if (!cell.left && !visited[cell.row][cell.col - 1]&& cell.col > 0) {
                visited[cell.row][cell.col - 1] = true;
                linkedList.add(maze.getGrid()[cell.row][cell.col - 1]);
            }
            if (!cell.right && cell.col + 1 < cols && !visited[cell.row][cell.col + 1]) {
                visited[cell.row][cell.col + 1] = true;
                linkedList.add(maze.getGrid()[cell.row][cell.col + 1]);
            }
        }return false;
    }
}