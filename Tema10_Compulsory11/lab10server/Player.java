package com.compulsory10.lab10server;


public class Player {

    private final String name;
    private int score;
    private long totalResponseTime;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.totalResponseTime = 0;
    }

    public synchronized void addPoint() {
        score++;
    }

    public synchronized void addResponseTime(long time) {
        totalResponseTime += time;
    }

    public String getName() {
        return name;
    }

    public synchronized int getScore() {
        return score;
    }

    public synchronized long getTotalResponseTime() {
        return totalResponseTime;
    }

    @Override
    public String toString() {
        return name + " | score=" + score + " | totalTime=" + totalResponseTime + "ms";
    }
}
