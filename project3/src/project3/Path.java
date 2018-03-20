/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

import java.util.*;
import java.io.*;

import java.lang.reflect.Array;
import javafx.util.Pair;

/**
 *
 * @author mike
 */
public class Path {

    PriorityQueue<nodes> queue = new PriorityQueue<nodes>();
    int[][] paths;
    int[] distances;
    Set<Integer> settled;

    public class nodes {

        int nodeID;
        int distance;
        int prevNode;
    }
    int start;
    int currentVert = 1;
    Graph gr;

    public Path(Graph graph, int start) {
        this.gr = graph;
        this.start = start;
        paths = new int[gr.mVertexCount][gr.mVertexCount];
        distances = new int[gr.mVertexCount];
        settled = new HashSet<Integer>();
        for (int vert = 1; vert <= gr.mGraph.size() - 1; vert++) {
            LinkedList<Graph.Vertex> adj = gr.mGraph.get(vert);
            for (Iterator<Graph.Vertex> vertEnum = adj.iterator();
                    vertEnum.hasNext();) {
                Graph.Vertex toVert = vertEnum.next();
                paths[vert - 1][toVert.mVertId - 1] = toVert.mDistance;
            }
        }
        queue = new PriorityQueue<nodes>(gr.mVertexCount, new Comparator<nodes>() {
            @Override
            public int compare(nodes o1, nodes o2) {
                if (o1.distance > o2.distance) {
                    return 1;
                } else if (o1.distance < o2.distance) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

    }

    void PrintGraph() {
        // Go through the Adjacency Matrix

        for (int vert = 1; vert <= gr.mGraph.size(); vert++) {
            LinkedList<Graph.Vertex> adj = gr.mGraph.get(vert);
            System.out.print("From Vertex: " + vert);
            for (Iterator<Graph.Vertex> vertEnum = adj.iterator();
                    vertEnum.hasNext();) {
                Graph.Vertex toVert = vertEnum.next();
                System.out.print(" " + toVert.mVertId + " (" + toVert.mDistance + ") ");
            }
            System.out.println();
        }
    }

    public int getNextVertex() {

        return 0;
    }

    public void applyRelaxation(int w) {
        // 
    }

    public void printShortestPath(int vert) {
        // poll values from priority queue.
    }
}
