package com.compulsory8.lab8.game;

public class GameMonitor extends Thread {

    private final Game game;
    private final long startTime;
    private final long limitMillis;
    public GameMonitor(Game game, long limitSeconds) {
        this.game = game;
        this.startTime = System.currentTimeMillis();
        this.limitMillis = limitSeconds * 1000;
        setDaemon(true);
    }
    @Override
    public void run() {

        if (game == null) return;
        while (!game.isGameOver()) {
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println("Time: " + elapsed / 1000 + "s | " + game.getGameState());
            if (elapsed > limitMillis) {
                game.endGame("TIME LIMIT REACHED");
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
        }}
}
