package com.exemplu.tema3;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SocialNetwork {
    private List<Profile> profile = new ArrayList<>();

    public void addProfile(Profile profil) {
        profile.add(profil);
    }

    public void printNetwork() {
        profile.sort(new Comparator<Profile>() {
            @Override
            public int compare(Profile profl1, Profile profl2) {

                int importanta1 = 0;
                int importanta2 = 0;
                if (profl1 instanceof Person) importanta1 = ((Person) profl1).getImportanta();
                if (profl1 instanceof Company) importanta1 = ((Company) profl1).getImportanta();
                if (profl2 instanceof Person) importanta2 = ((Person) profl2).getImportanta();
                if (profl2 instanceof Company) importanta2 = ((Company) profl2).getImportanta();

                return Integer.compare(importanta1, importanta2);
            }
        });

        for (Profile profil : profile) {
            System.out.println(profil);
        }
    }

}
