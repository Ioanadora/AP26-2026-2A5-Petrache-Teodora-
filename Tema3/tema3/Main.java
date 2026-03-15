package com.exemplu.tema3;


public class Main {

    public static void main(String[] args) {

        Programator om1 = new Programator("Ana", 7, "2000-05-10", "Java");
        Designer om2 = new Designer("Alex", 5, "1999-02-20", "colorat");

        Company com1 = new Company(15, "google");
        Company com2 = new Company(67, "samsung");

        om1.addRelatii(om2, "prieteni");

        om1.addJob(com1, "developer");
        om2.addJob(com2, "designer");


        com1.addAngajati(om1, "developer");
        com2.addAngajati(om2, "designer");

        SocialNetwork network = new SocialNetwork();

        network.addProfile(om1);
        network.addProfile(om2);
        network.addProfile(com1);
        network.addProfile(com2);

        network.printNetwork();
    }
}