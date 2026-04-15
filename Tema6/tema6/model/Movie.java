package com.lab6.tema6.model;

import java.util.Date;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private Date releaseDate;
    private int duration;
    private double score;

    private Genre genre;
    private List<Actor> actors;

    public Movie() {}

    public Movie(int id, String title, Date releaseDate, int duration, double score, Genre genre) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.score = score;
        this.genre = genre;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public Date getReleaseDate() { return releaseDate; }
    public int getDuration() { return duration; }
    public double getScore() { return score; }
    public Genre getGenre() { return genre; }
    public List<Actor> getActors() { return actors; }
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setReleaseDate(Date releaseDate) { this.releaseDate = releaseDate; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setScore(double score) { this.score = score; }
    public void setGenre(Genre genre) { this.genre = genre; }
    public void setActors(List<Actor> actors) { this.actors = actors; }
}