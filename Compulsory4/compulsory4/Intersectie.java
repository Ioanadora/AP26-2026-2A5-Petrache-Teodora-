package com.lab4.compulsory4;


import java.util.Objects;

public class Intersectie {
    private String nume;

    public Intersectie(String name) {
        this.nume = name;
    }

    public String getNume() {
        return nume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Intersectie)) return false;
        Intersectie that = (Intersectie) o;
        return Objects.equals(nume, that.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }

    @Override
    public String toString() {
        return nume;
    }
}