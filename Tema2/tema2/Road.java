package com.tema2;


/**
 * Clasa care reprezinta un drum intre doua locatii.
 * Contine tipul drumului, lungimea si limita de viteza.
 */
public class Road {
    private Location loc1;
    private Location loc2;
    private TipuriR tip;
    private double lungime;
    private int limitaViteza;

    /**
     * Constructor pentru drum.
     * @param loc1 prima locatie
     * @param loc2 a doua locatie
     * @param tip tipul drumului
     * @param lungime lungimea drumului
     * @param limitaViteza limita de viteza
     */
    public Road(Location loc1, Location loc2, TipuriR tip, double lungime, int limitaViteza) {


        this.loc1 = loc1;
        this.loc2 = loc2;
        this.tip = tip;
        this.lungime = lungime;
        this.limitaViteza = limitaViteza;
    }
    /**
     * Returneaza numele locatiei 1
     * @return numele locatiei 1
     */
    public Location getLocatia1() { return loc1; }
    /**
     * Returneaza numele locatiei 2
     * @return numele locatiei 2
     */
    public Location getLocatia2() { return loc2; }
    /**
     * Returneaza lungimea drumului
     * @return lungimea drumului
     */
    public double getLungime() { return lungime; }



    /**
     * Verifica daca doua drumuri sunt egale.
     * Doua drumuri sunt egale daca au aceleasi locatii, tip, lungime si limita de viteza.
     * @param object obiectul cu care se compara
     * @return true daca drumurile sunt egale
     */
    @Override
    public boolean equals(Object object)
    {
        if(this==object) return true;
        if(!(object instanceof Road))return false;
        Road drum= (Road)object;

        if(loc1.equals(drum.loc1)&&loc2.equals(drum.loc2)  && tip==drum.tip && lungime==drum.lungime&& limitaViteza==drum.limitaViteza  ) return true;
        else
            return false;

    }


}
