package advanced1;
public class Box {


    private int minLin,maxLin,minCol,maxCol;
    private boolean eOk;

    public Box(Matrice mat){ Gasire(mat); }

    private void Gasire(Matrice mat){
        int lin=mat.Linii();
        int col=mat.Coloane();


        minLin=lin;
        maxLin=-1;
        minCol=col;
        maxCol=-1;

        for (int i = 0; i < lin; i++) {
            for (int j = 0; j < col; j++){
                if(mat.Valoare(i,j)=='$'){
                    if(i<minLin) minLin=i;
                    if(i>maxLin) maxLin=i;
                    if(j<minCol) minCol=j;
                    if(j>maxCol) maxCol=j;

                }


            }}
            if(maxLin!=-1) eOk=true;
            else eOk=false;



    }
    private boolean exista(){return eOk;}

    public void Afis()
{
if(exista()==false) System.out.print("nu este forma");
else{
    System.out.println("Bounding Box:");
    System.out.println("incepe la: "+minLin+", "+minCol);
    System.out.print("se tremina la: "+maxLin+", "+maxCol);

}

}

    

}
