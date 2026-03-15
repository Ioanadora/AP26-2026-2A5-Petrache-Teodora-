package com.tema2;

/**
 * Clasa care reprezinta o benzinarie
 * Are parametrul pentru pretul benzinei in plus pe langa cele de la Location
 */
public final class Benzinarie extends Location {
    private double benzPret;
    /**
     * Constructor pentru oras
     * @param numeLoc numele orasului
     * @param oX coordonata X
     * @param oY coordonata Y
     * @param benzPret pret benzina
     */
    public Benzinarie(String numeLoc, int oX, int oY, double benzPret)
    {

        super(numeLoc,oX,oY);
        this.benzPret=benzPret;
    }
    /**
     * Returneaza pretul benzinei
     * @return benzPret
     */
    public double getPret(){return benzPret;}
}
