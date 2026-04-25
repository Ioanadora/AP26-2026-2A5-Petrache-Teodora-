package com.compulsory8.lab8.view;
import com.compulsory8.lab8.model.Cell;
import com.compulsory8.lab8.model.Maze;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MazeCanvas extends Canvas {

    private Maze maze;

    public MazeCanvas() {
        setWidth(600);
        setHeight(500);

        setOnMouseClicked(e -> {
            if (maze == null) return;

            int row = (int)(e.getY() / (getHeight() / maze.getRows()));
            int col = (int)(e.getX() / (getWidth() / maze.getCols()));

            toggleWall(row, col, e.getX(), e.getY());
            draw();
        });
    }
    public void setMaze(Maze maze) {
        this.maze = maze;
        draw();
    }
    private void toggleWall(int row, int col, double mouseX, double mouseY) {
        Cell c = maze.getGrid()[row][col];

        double cellW = getWidth() / maze.getCols();
        double cellH = getHeight() / maze.getRows();

        double x = col * cellW;
        double y = row * cellH;

        double margin = 10;

        // sus
        if (Math.abs(mouseY - y) < margin) {
            c.top = !c.top;
            if (row > 0) maze.getGrid()[row - 1][col].bottom = c.top;
        }
        // jos
        else if (Math.abs(mouseY - (y + cellH)) < margin) {
            c.bottom = !c.bottom;
            if (row < maze.getRows() - 1)
                maze.getGrid()[row + 1][col].top = c.bottom;
        }
        // stanga
        else if (Math.abs(mouseX - x) < margin) {
            c.left = !c.left;
            if (col > 0)
                maze.getGrid()[row][col - 1].right = c.left;
        }
        // dreapta
        else if (Math.abs(mouseX - (x + cellW)) < margin) {
            c.right = !c.right;
            if (col < maze.getCols() - 1)
                maze.getGrid()[row][col + 1].left = c.right;
        }
    }

    public void draw() {
        if (maze == null) return;
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, getWidth(), getHeight());
        graphicsContext.setFill(Color.AZURE);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());

        int rows = maze.getRows();
        int cols = maze.getCols();
        double cellW = getWidth() / cols;
        double cellH = getHeight() / rows;

        graphicsContext.setStroke(Color.HOTPINK);
        Cell[][] grid = maze.getGrid();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double x = j * cellW;
                double y = i * cellH;


                Cell c = grid[i][j];
                if (c.top) graphicsContext.strokeLine(x, y, x + cellW, y);
                if (c.right)
                    graphicsContext.strokeLine(x + cellW, y, x + cellW, y + cellH);
                if (c.bottom)
                    graphicsContext.strokeLine(x, y + cellH, x + cellW, y + cellH);
                if (c.left) graphicsContext.strokeLine(x, y, x, y + cellH);
            }
        }
    }
}