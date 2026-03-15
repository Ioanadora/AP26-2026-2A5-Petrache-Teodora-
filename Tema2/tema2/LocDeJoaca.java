package com.tema2;

/**
 * Clasa care reprezinta un loc de joaca
 * Are parametrul pentru numarul de tobogane in plus pe langa cele de la Location
 */
public final class LocDeJoaca extends Location{
    private int tobogane;
    /**
     * Constructor pentru oras
     * @param numeLoc numele orasului
     * @param oX coordonata X
     * @param oY coordonata Y
     * @param tobogane numarul de tobogane
     */
    public LocDeJoaca(String numeLoc, int oX, int oY, int tobogane) {
        super(numeLoc, oX, oY);
        this.tobogane = tobogane;
    }
    /**
     * Returneaza numarul de tobogane
     * @return tobogane
     */
    public int getTobogane(){return tobogane;}
}
