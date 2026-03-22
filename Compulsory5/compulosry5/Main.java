package com.compulosry5;

public class Main {

    public static void main(String[] args) {

        ResourceRepository repo = new ResourceRepository();

        try {
            repo.addResource(new Resource("knuth67", "The Art of Computer Programming",
                    "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth"));

            repo.addResource(new Resource("jvm25", "The Java Virtual Machine Specification",
                    "https://docs.oracle.com/javase/specs/jvms/se25/html/index.html", 2025, "Tim Lindholm & others"));

            repo.addResource(new Resource("java25", "The Java Language Specification",
                    "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", 2025, "James Gosling & others"));

           /* repo.addResource(new Resource("knuth67", "The Art of Computer Programming",
                    "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth"));*/

            repo.printAll();
            repo.openResource("jvm25");


        } catch (DuplicateResourceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
