package com.tema4.lab4;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Main {

    public static void main(String[] args) {

        City city = NameGenerator.generateCity(10, 20);

        System.out.println("Query result:");
        city.streetsLongerThan(200);

        Graph<Intersection, DefaultWeightedEdge> graph = GraphSolver.buildGraph(city);

        System.out.println("\nMinimum spanning tree:");
        GraphSolver.kruskalSolution(graph);
    }
}