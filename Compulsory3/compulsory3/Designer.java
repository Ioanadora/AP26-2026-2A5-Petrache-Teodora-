package com.compulsory3;

public class Designer extends Person {
    private String stil;

    public Designer(String name, int ID, String stil) {
        super(name, ID);
        this.stil = stil;
    }

    public String getStil() {
        return stil;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + ((Designer) this).getStil() + ")";
    }


}
