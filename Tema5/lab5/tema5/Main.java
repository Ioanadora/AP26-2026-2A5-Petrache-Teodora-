package com.lab5.tema5;

import com.lab5.tema5.commands.*;
import com.lab5.tema5.model.*;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        Catalog catalog = new Catalog();

        Resource r1 = new Resource(
                "knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", Map.of("author", "Donald E. Knuth", "year", "1967"));

        Resource r2 = new Resource("java25", "Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", Map.of("author", "James Gosling", "year", "2025"));

        new AddCommand(catalog, r1).execute();
        new AddCommand(catalog, r2).execute();

        new ListCommand(catalog).execute();

        new ViewCommand(catalog, "java25").execute();

        new ReportCommand(catalog).execute();
    }
}
