package com.lab7.lab7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;
    private double score;

    public Film() {}

    public Film(String title, String genre, double score) {
        this.title = title;
        this.genre = genre;
        this.score = score;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}