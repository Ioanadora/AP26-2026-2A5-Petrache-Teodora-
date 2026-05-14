package com.compulsory8.lab8.view;
import com.compulsory8.lab8.model.Cell;
import com.compulsory8.lab8.model.Maze;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.compulsory8.lab8.game.*;
import javafx.scene.image.Image;

//import static sun.jvm.hotspot.oops.MethodData.cellSize;

public class MazeCanvas extends Canvas {

    private Maze maze;
    private Game game;
    private final Image bunnyImage = new Image(getClass().getResourceAsStream("/images/bunny.png"));
    private final Image robotImage = new Image(getClass().getResourceAsStream("/images/robot.png"));
    public void setGame(Game game) {
        this.game = game;
    }

    public MazeCanvas() {
        setWidth(600);
        setHeight(500);

        setOnMouseClicked(mouseEvent -> {
            if (maze == null) return;

            int row = (int)(mouseEvent.getY() / (getHeight() / maze.getRows()));
            int col = (int)(mouseEvent.getX() / (getWidth() / maze.getCols()));

            toggleWall(row, col, mouseEvent.getX(), mouseEvent.getY());
            draw();
        });
    }
    public void setMaze(Maze maze) {
        this.maze = maze;
        draw();
    }
    private void toggleWall(int row, int col, double mouseX, double mouseY) {
        Cell cell = maze.getGrid()[row][col];

        double cellW = getWidth() / maze.getCols();
        double cellH = getHeight() / maze.getRows();
        double numberX = col * cellW;
        double numberY = row * cellH;
        double margin = 10;


        if (Math.abs(mouseY - numberY) < margin) {
            cell.top = !cell.top;
            if (row > 0) maze.getGrid()[row - 1][col].bottom = cell.top;
        }
        else if (Math.abs(mouseY - (numberY + cellH)) < margin) {
            cell.bottom = !cell.bottom;
            if (row < maze.getRows() - 1)
                maze.getGrid()[row + 1][col].top = cell.bottom;
        }
        else if (Math.abs(mouseX - numberX) < margin) {
            cell.left = !cell.left;
            if (col > 0)
                maze.getGrid()[row][col - 1].right = cell.left;
        }
        else if (Math.abs(mouseX - (numberX + cellW)) < margin) {
            cell.right = !cell.right;
            if (col < maze.getCols() - 1)
                maze.getGrid()[row][col + 1].left = cell.right;
        }
    }

    public void draw() {
        if (maze == null) return;

        GraphicsContext graphicsContext = getGraphicsContext2D();

        graphicsContext.clearRect(0, 0, getWidth(), getHeight());



        graphicsContext.setFill(Color.ALICEBLUE);
        graphicsContext.fillRect(0, 0, getWidth(), getHeight());

        int rows = maze.getRows();
        int cols = maze.getCols();
        double cellW = getWidth() / cols;
        double cellH = getHeight() / rows;
        graphicsContext.setStroke(Color.HOTPINK);
        Cell[][] grid = maze.getGrid();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                double xnumber = col * cellW;
                double ynumber = row * cellH;

                if (row == 0 && col == 0) {
                    graphicsContext.setFill(Color.AQUAMARINE);
                    graphicsContext.fillRect(xnumber, ynumber, cellW, cellH);
                }
                if (row == rows - 1 && col == cols - 1) {
                    graphicsContext.setFill(Color.DEEPPINK);
                    graphicsContext.fillRect(xnumber, ynumber, cellW, cellH);
                }
                Cell cell = grid[row][col];
                if (cell.top)
                    graphicsContext.strokeLine(xnumber, ynumber, xnumber + cellW, ynumber);
                if (cell.right)
                    graphicsContext.strokeLine(xnumber + cellW, ynumber, xnumber + cellW, ynumber + cellH);
                if (cell.bottom)
                    graphicsContext.strokeLine(xnumber, ynumber + cellH, xnumber + cellW, ynumber + cellH);
                if (cell.left)
                    graphicsContext.strokeLine(xnumber, ynumber, xnumber, ynumber + cellH);
            }}

        if (game != null) {
            // aici
            for (Position bunny : game.getAllBunnyPositions()) {
                graphicsContext.drawImage(
                        bunnyImage,
                        bunny.col * cellW + 5,
                        bunny.row * cellH + 5,
                        cellW - 10,
                        cellH - 10
                );
            }
            for (Position robot : game.getRobotPositions()) {
                graphicsContext.drawImage(
                        robotImage,
                        robot.col * cellW + 5,
                        robot.row * cellH + 5,
                        cellW - 10,
                        cellH - 10
                );
            }
        }
    }

}
