public class Road {
    private Location loc1;
    private Location loc2;
    private String tip;
    private double lungime;
    private int limitaViteza;

    public Road(Location loc1, Location loc2, String tip, double lungime, int limitaViteza) {

        double distantaEuclidiana = Math.sqrt(
                Math.pow(loc1.getX() - loc2.getX(), 2) +
                Math.pow(loc1.getY() - loc2.getY(), 2)
        );

        if (lungime < distantaEuclidiana) {
        System.out.println("Lungimea drumului este prea mica");
        return;
        }

        this.loc1 = loc1;
        this.loc2 = loc2;
        this.tip = tip;
        this.lungime = lungime;
        this.limitaViteza = limitaViteza;
    }

    public Location getLocatia1() { return loc1; }
    public Location getLocatia2() { return loc2; }
    public double getLungime() { return lungime; }

    @Override
    public String toString() {
        return "Drum " + tip + " de la " + loc1.getNume() +" la " + loc2.getNume() +", lungime " + lungime + ", limita viteza " + limitaViteza;
    }
}