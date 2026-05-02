package com.compulsory8.lab8.game;

import com.compulsory8.lab8.model.Cell;

import java.util.*;

public class Bunny extends Thread {//f important pentru examen runnable vs extend thread
    private final Game game;
    private Position position;
    private final Random random = new Random();

    public Bunny(Game game, Position start) {
        this.game = game;
        this.position = start;
        game.setBunny(start);
    }
    @Override
    public void run() {
        game.waitForStart();

        while (!game.isGameOver()) {
            moveRandom();
            sleepRandom();
        }
    }

    private void moveRandom() {
        List<Position> moves = getMoves();


        if (moves.isEmpty()) return;

        Position next = moves.get(random.nextInt(moves.size()));


        if (game.move(position, next)) {
            position = next;
            game.setBunny(position);

            System.out.println("[bunny] moved to " + position.row + "," + position.col);


            if (position.row == game.getMaze().getRows() - 1 && position.col == game.getMaze().getCols() - 1) {
                System.out.println("bunny wins");
                System.exit(0);}
        }}
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

    private void sleepRandom() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException exception) {}
    }
}