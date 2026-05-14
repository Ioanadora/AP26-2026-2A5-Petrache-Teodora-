package com.compulsory10.lab10server.model;

import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
    private int score;
    private long totalTime;
    public ResultEntity() {}
    public ResultEntity(PlayerEntity player, GameEntity game, int score, long totalTime) {
        this.player = player;
        this.game = game;
        this.score = score;
        this.totalTime = totalTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public PlayerEntity getPlayer() { return player; }
    public void setPlayer(PlayerEntity player) { this.player = player; }
    public GameEntity getGame() { return game; }
    public void setGame(GameEntity game) { this.game = game; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public long getTotalTime() { return totalTime; }
    public void setTotalTime(long totalTime) { this.totalTime = totalTime; }
}
