package com.tema2;

/**
 * Clasa care reprezinta un oras
 * Are parametrul pentru populatie in plus pe langa cele de la Location
 */
public final class Oras extends Location{
    private int populatie;

    /**
     * Constructor pentru oras.
     * @param numeLoc numele orasului
     * @param oX coordonata X
     * @param oY coordonata Y
     * @param populatie populatia orasului
     */
    public Oras(String numeLoc, int oX, int oY, int populatie)
    {

        super(numeLoc,oX,oY);
        this.populatie=populatie;
    }
    /**
     * Returneaza populatia orasului.
     * @return populatia
     */
    public int getPopulatie(){return populatie;}
}
