package com.compulsory10.lab10server.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class GameEntity {//la sesiune anotatii min ->entity id in principal
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ResultEntity> results = new ArrayList<>();
    public GameEntity() {
        this.startTime = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public List<ResultEntity> getResults() { return results; }
    public void setResults(List<ResultEntity> results) { this.results = results; }
}
