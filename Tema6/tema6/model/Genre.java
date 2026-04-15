package com.lab6.tema6.model;

public class Genre {
    private int id;
    private String nameGenre;

    public Genre() {}

    public Genre(int id, String name) {
        this.id = id;
        this.nameGenre = name;
    }

    public int getId() { return id; }
    public String getNameGenre() { return nameGenre; }

    public void setId(int id) { this.id = id; }
    public void setNameGenre(String nameGenre) { this.nameGenre = nameGenre; }
}