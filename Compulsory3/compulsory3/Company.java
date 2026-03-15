package com.compulsory3;

public class Company implements Profile {
    private int ID;
    private String name;

    public Company(int ID, String name) {
        this.ID = ID;
        this.name = name;
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
        return "Company{" + "id='" + ID + '\'' + ", name='" + name + '\'' + '}';
    }
}