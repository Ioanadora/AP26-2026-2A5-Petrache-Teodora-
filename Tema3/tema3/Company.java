package com.exemplu.tema3;


import java.util.HashMap;
import java.util.Map;

public class Company implements Profile {
    private int ID;
    private String name;
    private Map<Person, String> angajati = new HashMap<>();

    public Company(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public void addAngajati(Person om, String pozitie) {
        angajati.put(om, pozitie);
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

    @Override
    public int getImportanta() {
        return angajati.size();
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Company)) return false;
        Company com = (Company) object;
        if (this.ID == com.ID) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "Company{" + "id='" + ID + '\'' + ", name='" + name + '\'' + ", importanta=" + getImportanta() + '}';
    }
}
