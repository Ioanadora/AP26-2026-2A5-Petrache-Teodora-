package com.tema2;

public class Main {
    public static void main(String[] args) {

        Rute rute = new Rute();

        Oras oras1 = new Oras("Bucuresti", 0, 0, 2000000);
        Oras oras2 = new Oras("Cluj", 100, 200, 300000);
        LocDeJoaca parc = new LocDeJoaca("ParcCentral", 50, 50, 5);
        Benzinarie benzinarie = new Benzinarie("Petrom", 20, 30, 8.5);
        rute.addLoc(oras1);
        rute.addLoc(oras2);
        rute.addLoc(parc);
        rute.addLoc(benzinarie);


        Road drum1 = new Road(oras1, oras2, TipuriR.AUTOSTRADA, 250, 130);
        Road drum2 = new Road(oras1, parc, TipuriR.DRUM_NATIONAL, 80, 90);
        Road drum3 = new Road(parc, benzinarie, TipuriR.DRUM_JUDETEAN, 30, 50);
        rute.addDrum(drum1);
        rute.addDrum(drum2);
        rute.addDrum(drum3);

        if (rute.valid()) {
            System.out.println("Toate drumurile sunt valide.");
        } else {System.out.println("Exista drumuri invalide ");
        }

        if (rute.sePoateAjunge(oras1, benzinarie)) {
            System.out.println("Se poate ajunge de la " + oras1.getNume() + " la " + benzinarie.getNume() + ".");
        } else {System.out.println("Nu se poate ajunge de la " + oras1.getNume() + " la " + benzinarie.getNume() + ".");
        }

        if (rute.sePoateAjunge(oras2, parc)) {
            System.out.println("Se poate ajunge de la " + oras2.getNume() + " la " + parc.getNume() + ".");
        } else {System.out.println("Nu se poate ajunge de la " + oras2.getNume() + " la " + parc.getNume() + ".");
        }

        Road drumInvalid = new Road(oras2, benzinarie, TipuriR.DRUM_JUDETEAN, 10, 50);
        rute.addDrum(drumInvalid);

        if (rute.valid()) {
            System.out.println("Toate drumurile sunt valide dupa adaugarea drumului ");
        } else {System.out.println("Exista drumuri invalide dupa adaugarea drumului ");
        }
        System.out.println(oras1.getNume() + " are populatie de " + oras1.getPopulatie());
        System.out.println(parc.getNume() + " are " + parc.getTobogane() + " tobogane");
        System.out.println(benzinarie.getNume() + " are pretul benzinei " + benzinarie.getPret());


    }
}