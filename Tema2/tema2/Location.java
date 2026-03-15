package com.tema2;



/**
 * Clasa abstracta care reprezinta o locatie din problema
 * Fiecare locatie are nume si coordonate
 * Superclasa pentru Oras,Benzinarie, LocDeJoaca
 * */
public abstract sealed class Location permits Oras,LocDeJoaca,Benzinarie  {
    private String numeLoc;
    private int oX;
    private int oY;



    /**
     * Constructor pentru o locatie
     * @param numeLoc numele locatiei
     * @param oX coordonata X a locatiei
     * @param oY coordonata Y a locatiei
     */
    public Location(String numeLoc, int oX, int oY)
    {
        this.numeLoc=numeLoc;
        this.oX=oX;
        this.oY=oY;

    }

    /**
     * Returneaza numele locatiei
     * @return numele locatiei
     */
    public String getNume(){ return numeLoc;}

    /**
     * Returneaza coordonata X
     * @return coordonata X
     */
    public int getX() { return oX; }
    /**
     * Returneaza coordonata Y
     * @return coordonata Y
     */
    public int getY() { return oY; }



    /**
     * Verifica daca doua locatii sunt egale.
     * Doua locatii sunt egale daca au acelasi nume si aceleasi coordonate.
     * @param object -> obiectul cu care se compara
     * @return true -> daca locatiile sunt egale
     */
    @Override
    public boolean equals(Object object)
    {
        if(this==object) return true;
        if(!(object instanceof Location))return false;
        Location loc= (Location)object;

        if(numeLoc.equals(loc.numeLoc)&& oX==loc.oX && oY==loc.oY) return true;
        else
            return false;

    }


}
