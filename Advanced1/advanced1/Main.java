package advanced1;

public class Main {
    public static void main(String[] args) {
        int linii = 5;
        int coloane = 5;

        MatriceGenerator generator = new MatriceGenerator(linii, coloane);
        char[][] x = generator.genereaza();

        Matrice mat = new Matrice(x);
        Box box = new Box(mat);
        box.Afis();
    }
}