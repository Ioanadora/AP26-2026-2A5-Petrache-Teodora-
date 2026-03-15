package com.lab4.compulsory4;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Intersectie[] intersectii = IntStream.rangeClosed(1, 10).mapToObj(i -> new Intersectie("v" + i)).toArray(Intersectie[]::new);


        Set<Intersectie> interSet = new HashSet<>(Arrays.asList(intersectii));
        interSet.add(new Intersectie("v0"));
        System.out.println("Intersectii: " + interSet);


        List<Strada> listaStr = new LinkedList<>();
        listaStr.add(new Strada("s0", intersectii[0], intersectii[1], 5));
        listaStr.add(new Strada("s1", intersectii[0], intersectii[2], 5.4));
        listaStr.add(new Strada("s2", intersectii[1], intersectii[2], 5.3));
        listaStr.add(new Strada("s3", intersectii[3], intersectii[4], 5.5));
        listaStr.add(new Strada("s4", intersectii[4], intersectii[5], 5.6));
        listaStr.add(new Strada("s5", intersectii[5], intersectii[6], 5.7));
        listaStr.add(new Strada("s6", intersectii[6], intersectii[7], 5.98));
        listaStr.add(new Strada("s7", intersectii[7], intersectii[8], 5.67));
        listaStr.add(new Strada("s8", intersectii[8], intersectii[9], 5.79));
        listaStr.add(new Strada("s9", intersectii[9], intersectii[0], 5.2));
        listaStr.add(new Strada("s10", intersectii[1], intersectii[3], 5.2));

        listaStr.sort(Comparator.comparingDouble(Strada::getLungime));

        System.out.println("Strazi sortate dupa lungime:");
        listaStr.forEach(System.out::println);
    }
}