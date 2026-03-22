package com.tema4.lab4;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphSolver {
    public static Graph<Intersection, DefaultWeightedEdge> buildGraph(City city){

        Graph<Intersection,DefaultWeightedEdge> graph=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        city.getListIntersections().forEach(graph::addVertex);
        city.getListStreets().forEach(s -> {

            DefaultWeightedEdge edge= graph.addEdge(s.getA(), s.getB());
            if (edge != null)
                graph.setEdgeWeight(edge, s.getLength());
        });
        return graph;}

    public static void kruskalSolution(Graph<Intersection, DefaultWeightedEdge> graph){
        KruskalMinimumSpanningTree<Intersection,DefaultWeightedEdge>mst=new KruskalMinimumSpanningTree<>(graph);
        System.out.println("Minimum cost=" + mst.getSpanningTree().getWeight());

        mst.getSpanningTree().getEdges().forEach(System.out::println);
    }
}
