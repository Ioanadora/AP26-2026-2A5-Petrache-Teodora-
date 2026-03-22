package com.tema4.lab4;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class City {
    private List<Intersection> listIntersections=new ArrayList<>();
    private List<Street> listStreets=new ArrayList<>();

    public void addInteresection(Intersection intersection){
        listIntersections.add(intersection);
    }
    public void addStreet(Street street){
        listStreets.add(street);
    }
    private long howManyStreets(Intersection intersection){
        return listStreets.stream().filter(street->street.getA().equals(intersection)||street.getB().equals(intersection)).count();
    }
    public void streetsLongerThan(double givenLength){
        listStreets.stream().filter(street -> street.getLength()>=givenLength).filter(street->howManyStreets(street.getA())>=3||howManyStreets(street.getB())>=3).forEach(System.out::println);
    }
}
