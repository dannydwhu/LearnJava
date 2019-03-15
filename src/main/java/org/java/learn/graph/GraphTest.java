package org.java.learn.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;

import java.util.List;

public class GraphTest {

    public static void main(String[] args){
        Graph<String,DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("default");
//        graph.addVertex("D");
//
//        graph.addEdge("D", "B");

        graph.addEdge("C", "B");
        graph.addEdge("B", "A");
        graph.addEdge("default", "C");



        List<String> list = Graphs.predecessorListOf((DirectedGraph)graph, "default");
        for(String s : list){
            System.out.println(s + "-");
        }

        DepthFirstIterator<String, DefaultEdge> it =
                new DepthFirstIterator<String, DefaultEdge>(graph, "C");

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
