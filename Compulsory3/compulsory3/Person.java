package com.compulsory3;

import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.List;

public class Person implements Profile {
    protected String name;
    protected int ID;
    protected Map<Person, String> relatii = new HashMap<>();
    protected Map<Company, String> job = new HashMap<>();

    public Person(String name, int ID) {
        this.name = name;
        this.ID = ID;


    }


    public void addRelatii(Person om, String tip) {
        relatii.put(om, tip);
    }

    public void addJob(Company lucru, String pozitie) {
        job.put(lucru, pozitie);
    }

    public Map<Person, String> getRelatii() {
        return relatii;
    }

    public Map<Company, String> getJob() {
        return job;
    }


    @Override
    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void sortProfilesByName(List<Profile> profileList) {

        Comparator<Profile> compByName = new Comparator<Profile>() {
            @Override
            public int compare(Profile firstProfile, Profile secondProfile) {
                String fsName = firstProfile.getName();
                String sdName = secondProfile.getName();
                return fsName.compareTo(sdName);
            }
        };


        profileList.sort(compByName);
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Person)) return false;
        Person persoana = (Person) object;
        if (this.ID == persoana.ID) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        String tip = "";
        if (this instanceof Programator) tip = " (Programator)";
        else if (this instanceof Designer) tip = " (Designer)";


        return getName() + tip;
    }

}
