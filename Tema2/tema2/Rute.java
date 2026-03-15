package com.tema2;

/**
 * Clasa care reprezinta o instanta a problemei Best Route.
 * Contine liste de locatii si drumuri, si metode pentru validare si determinarea conexiunii intre locatii.
 */
public class Rute
{
    private Location[] loc;
    private Road[] drum;
    private int nrLoc;
    private int nrDrum;

    /**
     * Constructor pentru clasa Rute.
     * Initializeaza array-urile de locatii si drumuri cu dimensiune maxima 50,
     * si contorii pentru numarul de locatii si drumuri.
     */
    public Rute()
    {
        loc=new Location[50];
        drum=new Road[50];
        nrLoc=0;
        nrDrum=0;
    }

    /**
     * Adauga o locatie in instanta problemei
     * Nu permite dubluri
     * @param locs locatia de adaugat
     */
    public void addLoc(Location locs)
    {
        for(int i=0;i<nrLoc;i++)
            if(loc[i].equals(locs)) return;
        loc[nrLoc++]=locs;

    }


    /**
     * Adauga un drum in instanta problemei
     * Nu permite dubluri
     * @param drums drumul de adaugat
     */
    public void addDrum(Road drums)
    {
        for(int i=0;i<nrDrum;i++)
            if(drum[i].equals(drums)) return;
        drum[nrDrum++]=drums;

    }



    /**
     * Verifica daca instanta problemei este valida
     * Un drum este invalid daca lungimea sa este mai mica decat distanta euclidiana intre locatii
     * @return true daca toate drumurile sunt valide
     */
    public boolean valid()
    {
        for(int i=0;i<nrDrum;i++) {
            Road dr = drum[i];
            double dX=dr.getLocatia1().getX()-dr.getLocatia2().getX();
            double dY=dr.getLocatia1().getY()-dr.getLocatia2().getY();
            double disEu=Math.sqrt(dX*dX + dY*dY);
            if(dr.getLungime()<disEu)return false;
        }
        return true;


    }

    /**
     * Returneaza indexul unei locatii in array-ul loc
     * @param x locatia cautata
     * @return indexul locatiei daca exista, -1 daca nu exista
     */
    private int index(Location x)
    {
        for(int i=0; i<nrLoc; i++)
            if(loc[i].equals(x))return i;
        return -1;
    }
    /**
     * Verifica recursiv daca exista un drum de la locatia start la locatia sf
     * Foloseste o cautare DFS si un array pentru a marca locatiile vizitate
     * @param start locatia de plecare
     * @param sf locatia destinatie
     * @param vazut array decare marcheaza locatiile deja vizitate
     * @return true daca exista un drum, false daca nu exista
     */

    private boolean recursie(Location start, Location sf, boolean[] vazut)
    {
        if(start.equals(sf))return true;

        int idx= index(start);
        if(idx==-1 || vazut[idx])return false;
        vazut[idx]=true;

        for (int i = 0; i < nrDrum; i++)
            if (drum[i].getLocatia1().equals(start))
                if (recursie(drum[i].getLocatia2(), sf, vazut))
                    return true;

        return false;


    }
    /**
     * Determina daca se poate ajunge de la o locatie la alta folosind drumurile existente.
     * @param start locatia de plecare
     * @param sf locatia destinatie
     * @return true daca exista un drum posibil
     */
    public boolean sePoateAjunge(Location start, Location sf)
    {
        boolean[] vazut = new boolean[nrLoc];
        return recursie(start, sf, vazut);
    }


}
