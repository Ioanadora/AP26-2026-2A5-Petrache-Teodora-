package teo1;

public class imagini {

    public static int[][] dreptunghi(int n){

        int[][] img=new int[n][n];

        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                img[i][j]=255;
        
        }

        int margineV=n/4;
        int margineO=n/6;

        for(int i=margineV;i<n-margineV;i++)
            for(int j=margineO;j<n-margineO;j++)
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
