package com.tema4.lab4;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class NameGenerator {
    public static  City generateCity(int nrIntersections, int nrStreets){
        if (nrIntersections < 2) {
            throw new IllegalArgumentException("Needs 2 intersections");
        }
        Faker names=new Faker();
        Random length=new Random();
        City city=new City();

        for(int intersection=0;intersection<nrIntersections;intersection++){
            Intersection intersections=new Intersection(names.address().streetName());
            city.addInteresection(intersections);
        }

        for(int street=0;street<nrStreets;street++){
            int nrIntesect=city.getListIntersections().size();
            Intersection intersection1=city.getListIntersections().get(length.nextInt(nrIntesect));
            Intersection intersection2=city.getListIntersections().get(length.nextInt(nrIntesect));

            if(!intersection1.equals(intersection2)){
                Street streets=new Street(names.address().streetName(),intersection1,intersection2, length.nextInt(500)+1);
                city.addStreet(streets);
                System.out.println("Created street: " + streets.getName());
            }
        }
        return city;
    }
}
