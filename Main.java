

package com.teo.lab1;

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
