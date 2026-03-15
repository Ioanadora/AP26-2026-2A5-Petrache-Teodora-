package advanced1;
public class Matrice {
    private char[][] matrice;

    public Matrice(char[][] matrice){this.matrice=matrice;}

    public int Linii(){ return matrice.length;}

    public int Coloane(){return matrice[0].length;}

     public char Valoare(int lin, int col) { return matrice[lin][col]; }

}
