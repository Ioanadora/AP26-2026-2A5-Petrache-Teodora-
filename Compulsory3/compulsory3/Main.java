package com.compulsory3;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Person om1 = new Programator("Ana", 7, "java");
        Person om2 = new Designer("Alex", 5, "colorat");
        Company com1 = new Company(15, "google");
        Company com2 = new Company(67, "samsung");

        om1.addRelatii(om2, "prieteni");
        om1.addJob(com1, "sef");
        om2.addJob(com2, "sef");

        List<Profile> profile = new ArrayList<>();
        profile.add(om1);
        profile.add(om2);
        profile.add(com1);
        profile.add(com2);

        Person.sortProfilesByName(profile);
        profile.forEach(System.out::println);


    }


}
