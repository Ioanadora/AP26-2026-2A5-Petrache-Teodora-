package com.exemplu.tema3;


public class Programator extends Person {
    private String limbaj;

    public Programator(String name, int ID, String limbaj, String dataNastere) {
        super(name, ID, dataNastere);
        this.limbaj = limbaj;
    }

    public String getLimbaj() {
        return limbaj;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + ((Programator) this).getLimbaj() + ")";
    }

}
