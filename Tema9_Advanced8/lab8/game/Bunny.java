package com.compulsory8.lab8.game;

import com.compulsory8.lab8.model.Cell;
import com.compulsory8.lab8.view.MazeCanvas;
import javafx.application.Platform;

import java.util.*;

public class Bunny extends Thread {// f important pentru examen runnable vs extend thread
    private final Game game;
    private Position position;
    private final Random random = new Random();
    private final MazeCanvas canvas;
    private volatile int delay = 600;
    private volatile boolean paused = false;
    public Bunny(Game game, Position start, MazeCanvas canvas) {
        this.game = game;
        this.position = start;
        this.canvas = canvas;
        game.setBunny(start);

    }

    @Override
    public void run() {
        game.waitForStart();



        while (!game.isGameOver()) {
            moveRandom();
            sleepControlled();
        }
    }

    private void moveRandom() {
        List<Position> moves = getMoves();

        if (moves.isEmpty())
            return;

        Position next = moves.get(random.nextInt(moves.size()));

        if (game.move(position, next)) {
            Position old = position;
            position = next;
            game.updateBunny( position);
            Platform.runLater(canvas::draw);
           // Platform.runLater(() -> canvas.draw());
            System.out.println("[bunny] moved to " + position.row + "," + position.col);
            if (position.row == game.getMaze().getRows() - 1 && position.col == game.getMaze().getCols() - 1) {
                game.endGame("Bunny wins");
            }
        }
    }
    private List<Position> getMoves() {
        List<Position> list = new ArrayList<>();
        Cell cell = game.getMaze().getGrid()[position.row][position.col];
        if (!cell.top && position.row > 0)
            list.add(new Position(position.row - 1, position.col));
        if (!cell.bottom && position.row < game.getMaze().getRows() - 1)
            list.add(new Position(position.row + 1, position.col));
        if (!cell.left && position.col > 0)
            list.add(new Position(position.row, position.col - 1));
        if (!cell.right && position.col < game.getMaze().getCols() - 1)
            list.add(new Position(position.row, position.col + 1));

        return list;
    }
    private void sleepControlled() {
        try {
            while (paused && !game.isGameOver()) {
                Thread.sleep(100);
            }
            if (game.isGameOver())
                return;
            Thread.sleep(delay);
        } catch (InterruptedException ignored) {
        }
    }
    public void speedUp() {
        delay = Math.max(50, delay - 50);
    }
    public void slowDown() {
        delay += 50;
    }
    public void pauseAgent() {
        paused = true;
    }
    public void resumeAgent() {
        paused = false;
    }
}