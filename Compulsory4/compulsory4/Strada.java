package com.lab4.compulsory4;


public class Strada implements Comparable<Strada> {
    private String nume;
    private Intersectie intersectie1;
    private Intersectie intersectie2;
    private double lungime;

    public Strada(String name, Intersectie inter1, Intersectie inter2, double length) {
        this.nume = name;
        this.intersectie1 = inter1;
        this.intersectie2 = inter2;
        this.lungime = length;
    }

    public String getName() {
        return nume;
    }

    public Intersectie getIntersectie1() {
        return intersectie1;
    }

    public Intersectie getIntersectie2() {
        return intersectie2;
    }

    public double getLungime() {
        return lungime;
    }

    @Override
    public int compareTo(Strada o) {
        return Double.compare(this.lungime, o.lungime);
    }

    @Override
    public String toString() {
        return nume + " (" + intersectie1 + " - " + intersectie2 + " : " + lungime + ")";
    }
}