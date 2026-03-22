package com.compulosry5;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resource {
    private String id;
    private String title;
    private String location;
    private int year;
    private String author;
}