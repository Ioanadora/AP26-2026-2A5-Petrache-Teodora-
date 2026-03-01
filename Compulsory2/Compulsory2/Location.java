public class Location {
    private String nume;
    private String tip;
    private double x;
    private double y;

    public Location(String nume, String tip, double x, double y) {


        this.nume = nume;
        this.tip = tip;
        this.x = x;
        this.y = y;
    }

    public String getNume() { return nume; }
    public String getTip() { return tip; }
    public double getX() { return x; }
    public double getY() { return y; }



    public void setNume(String nume) {this.nume = nume; }
    public void setTip(String tip) { this.tip = tip; }
     public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    @Override
    public String toString() {
        return "Locatia " + nume + " (" + tip + ") [" + x + ", " + y + "]";
    }
}