package com.tema4.lab4;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Street {

    private String name;
    private Intersection a;
    private Intersection b;
    private double length;

}