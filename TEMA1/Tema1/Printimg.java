package teo1;

public class printimg {

    public static void printi(int[][] img, char chNegru, char chAlb){

        int n = img.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
                if(img[i][j]==0){System.out.print(chNegru);}
                else if(img[i][j]==255){System.out.print(chAlb);}
                

            }

            System.out.println();
        }

    }

}
