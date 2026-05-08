package com.compulsory8.lab8.game;

import com.compulsory8.lab8.model.Cell;
import com.compulsory8.lab8.view.MazeCanvas;
import javafx.application.Platform;
import java.util.*;

public class Robot extends Thread {

    private final Game game;
    private Position position;
    private final Random random = new Random();
    private final MazeCanvas canvas;
    private final Deque<Position> stack = new ArrayDeque<>();
    private volatile int delay = 800;
    private volatile boolean paused = false;

    public Robot(Game game, Position start, MazeCanvas canvas) {
        this.game = game;
        this.position = start;
        this.canvas = canvas;
        stack.push(start);
        game.markVisited(start);

    }

    @Override
    public void run() {

        game.waitForStart();
        while (!game.isGameOver()) {
            Position target = game.getLastKnownBunnyPos();
            if (target != null) {
                moveTowards(target);
            } else {
                moveSystematic();
            }
            sleepControlled();
        }
    }
    //(O_O)  (0o0) (ToT)bruh
    private void moveTowards(Position target) {
        List<Position> path = findShortestPath(position, target);
        if (path != null && path.size() > 1) {
            Position next = path.get(1);
            if (game.move(position, next)) {
                moveTo(next);
                return;
            }
        }
        moveSystematic();
    }



    private List<Position> findShortestPath(Position start, Position target) {
        Queue<List<Position>> queue = new LinkedList<>();
        queue.add(Collections.singletonList(start));//eficient
        Set<Position> visitedBFS = new HashSet<>();
        visitedBFS.add(start);
        while (!queue.isEmpty()) {
            List<Position> path = queue.poll();
            Position current = path.get(path.size() - 1);
            if (current.equals(target)) return path;
            for (Position neighbor : getMoves(current)) {
                if (!visitedBFS.contains(neighbor)) {
                    visitedBFS.add(neighbor);
                    List<Position> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }

    private void moveSystematic() {
        List<Position> moves = getMoves();
        Collections.shuffle(moves);


        for (Position next : moves) {
            if (!game.isVisited(next)) {
                if (game.move(position, next)) {
                    game.markVisited(next);
                    stack.push(position);
                    moveTo(next);
                    return;
                }}}
        if (!stack.isEmpty()) {
            Position prev = stack.peek();
            if (game.move(position, prev)) {
                stack.pop();
                moveTo(prev);
                return;
            }
        }
        for (Position next : moves) {
            if (game.move(position, next)) {
                moveTo(next);
                return;
            }
        }
        if (stack.isEmpty()) {}
    }

    private void moveTo(Position next) {
        Position old = position;
        position = next;
        game.updateRobot(old, next);
        Platform.runLater(canvas::draw);
        //Platform.runLater(() -> canvas.draw());
        Position bunnyPos = game.getBunnyPos();
        if (bunnyPos != null) {
            int dist = Math.abs(position.row - bunnyPos.row) + Math.abs(position.col - bunnyPos.col);
            if (dist <= 2) {
                game.shareSighting(bunnyPos);
            }
            if (position.equals(bunnyPos)) {
                game.endGame("Robot wins - Bunny caught at " + position);
            }
        }
    }
/*
      private void moveRandom() {
      List<Position> moves = getMoves();
      if (moves.isEmpty()) return;

      Position next = moves.get(random.nextInt(moves.size()));

      if (game.move(position, next)) {
      Position old = position;
      position = next;
      game.updateRobot(old, next);
      Platform.runLater(() -> canvas.draw());


      System.out.println("[robot] at " + position.row + "," + position.col);

      Position bunny = game.getBunnyPos();

      if (bunny.row == position.row && bunny.col == position.col) {
      game.catchBunny();
      }
     }
      Position bunny = game.getBunnyPos();
      if (bunny.row == position.row && bunny.col == position.col) {
      game.stopGame("robots win");
      }
      }
*/
    private List<Position> getMoves(Position position) {
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
    public Position getPosition() {
        return position;
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
