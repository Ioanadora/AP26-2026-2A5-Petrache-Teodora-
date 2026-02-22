package teo1;

public class imagini {

    public static int[][] dreptunghi(int n){

        int[][] img=new int[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                img[i][j]=255;
        
        }

        int margine=n/4;

        for(int i=margine;i<n-margine;i++)
            for(int j=margine;j<n-margine;j++)
        {

            img[i][j]=0;


        }

        return img;

    }

    public static int[][] cerc(int n)
    {
        int[][] img=new int[n][n];

        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) {
                img[i][j] = 0;
            }
        int centru=n/2;
        int raza=n/3;
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) {
                

                int x=i-centru;
                int y=j-centru;
                if (x * x + y * y <= raza*raza) {
                    img[i][j] = 255; 
                }


            }
            return img;
        







    }



}
