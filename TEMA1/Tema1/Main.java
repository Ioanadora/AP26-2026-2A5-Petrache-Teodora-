
package teo1;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        if (args.length < 2) {
            System.out.println("baga un nr si forma");
            return;
        }
        int n=Integer.parseInt(args[0]);
        String forma=args[1];

        Scanner sc= new Scanner(System.in);

        System.out.print("caracter neggru:");
        char chNegru=sc.next().charAt(0);
         System.out.print("caracter alb:");
        char chAlb=sc.next().charAt(0);

        long start=System.nanoTime();
        int[][] imagine;

        if(forma.equals("dreptunghi")){imagine=imagini.dreptunghi(n);}
        else if(forma.equals("cerc")){imagine=imagini.cerc(n);}
        else {System.out.println("nu exista aceasta forma:(");
        return;
        }


        long sf=System.nanoTime();

        if(n<=50){printimg.printi(imagine, chNegru, chAlb);}
        else{
            System.out.println("timpul de executie:" + (sf-start)+" nanosecunde");
        }
        
        sc.close();



    }
}
