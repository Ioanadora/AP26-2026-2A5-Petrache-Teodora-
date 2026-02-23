

package com.teo.lab1;



 class suma {

    public static int sum(int nr){

        while(nr>=10)
        {
            int sum1=0;
            int x=nr;
            while(x>0)
            {
                sum1=sum1+x%10;
                x=x/10;
            }
            //System.out.println(sum1);
            nr=sum1;
        }

        return nr;
    }
}




public class Main {
    public static void main(String[] args) 
    {

        System.out.println("Hello World!");

        String[] a= {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};


        int n = (int) (Math.random() * 1_000_000);
        System.out.println(n);

        int r=n*3;
        r=r+0b10101;
        r=r+0xFF;
        r=r*6;

        System.out.println(r);

        int finalr=suma.sum(r);
        System.out.println(suma.sum(r));
        
        System.out.println("Willy-nilly, this semester I will learn " + a[finalr]);

    }
}
