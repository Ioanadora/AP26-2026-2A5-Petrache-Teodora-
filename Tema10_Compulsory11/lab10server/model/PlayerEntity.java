package com.compulsory10.lab10server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private int score;
    private long totalResponseTime;
    public PlayerEntity() {}

    public PlayerEntity(String name) {
        this.name = name;
        this.score = 0;
        this.totalResponseTime = 0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public long getTotalResponseTime() { return totalResponseTime; }
    public void setTotalResponseTime(long totalResponseTime) { this.totalResponseTime = totalResponseTime; }
    @Override
    public String toString() {
        return name + " | score=" + score + " | totalTime=" + totalResponseTime + "ms";
    }
}
