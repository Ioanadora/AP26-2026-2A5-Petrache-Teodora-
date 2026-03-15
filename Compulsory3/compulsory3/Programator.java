package com.compulsory3;

public class Programator extends Person {
    private String limbaj;

    public Programator(String name, int ID, String limbaj) {
        super(name, ID);
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
