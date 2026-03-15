package com.exemplu.tema3;


public class Designer extends Person {
    private String stil;

    public Designer(String name, int ID, String stil, String dataNastere) {
        super(name, ID, dataNastere);
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
