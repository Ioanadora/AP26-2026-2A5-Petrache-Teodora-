package advanced1;

import java.util.Random;

public class MatriceGenerator {

    private int linii;
    private int coloane;

    public MatriceGenerator(int linii, int coloane) {
        this.linii = linii;
        this.coloane = coloane;
    }

    public char[][] genereaza() {
        char[][] mat = new char[linii][coloane];
        Random rand = new Random();


        for (int i = 0; i < linii; i++) {
            for (int j = 0; j < coloane; j++) {
                mat[i][j] = ',';
            }
        }


        int randLin = rand.nextInt(linii);
        int randCol = rand.nextInt(coloane);
        mat[randLin][randCol] = '$';


        int extra = rand.nextInt(linii * coloane / 3);
        for (int k = 0; k < extra; k++) {
            int i = rand.nextInt(linii);
            int j = rand.nextInt(coloane);
            mat[i][j] = '$';
        }


        System.out.println("Matricea generata:");
        for (int i = 0; i < linii; i++) {
            for (int j = 0; j < coloane; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }

        return mat;
    }
}