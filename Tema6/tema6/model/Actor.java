package com.lab6.tema6.model;

public class Actor {
    private int id;
    private String nameActor;

    public Actor() {}

    public Actor(int id, String name) {
        this.id = id;
        this.nameActor = name;
    }

    public int getId() { return id; }
    public String getNameActor() { return nameActor; }

    public void setId(int id) { this.id = id; }
    public void setNameActor(String nameActor) { this.nameActor = nameActor; }
}