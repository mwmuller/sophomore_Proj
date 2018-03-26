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

    public PriorityQueue<nodes> queue;
    public Comparator<nodes> compare = new nodes();
    nodes[] pathArr;
    Graph gr;
    int start;
    boolean distancePrinted = false;
    int distance = 0;

    public class nodes implements Comparator<nodes> {

        Integer nodeID;
        Integer distance;
        Integer prevNode;

        public nodes() {
        }

        public nodes(int nodeID, int dist, int prev) {
            this.nodeID = nodeID;
            this.distance = dist;
            this.prevNode = prev;
        }

        @Override
        public int compare(nodes n1, nodes n2) {

            if (n1.distance < n2.distance) {
                return -1;
            }
            if (n1.distance > n2.distance) {
                return 1;
            }
            return 0;
        }
    }
    public Path(Graph graph, int start) {
        this.gr = graph;
        pathArr = new nodes[gr.mVertexCount + 1];
        for (int vert = 1; vert <= gr.mVertexCount; vert++) {
            nodes n1 = new nodes(vert, Integer.MAX_VALUE, 0);
            if(vert == start){
                n1.distance = 0;
            }
                pathArr[vert] = n1;
        }
            queue = new PriorityQueue<nodes>(compare);
            queue.add(pathArr[start]);
        /* while(!queue.isEmpty()){ // This will go inside graph I think
            currentNode = getNextVertex();
            settled.add(currentNode);
            applyRelaxation(currentNode);
        }*/
    }



    public Integer getNextVertex() {
        nodes node = queue.poll();
        if(node == null){
            return null;
        }else{
            return node.nodeID;
        }
    }

    public void applyRelaxation(Integer currNode) {
        LinkedList<Graph.Vertex> adj = gr.mGraph.get(currNode);
        for (Iterator<Graph.Vertex> vertEnum = adj.iterator(); vertEnum.hasNext(); ) {
            Graph.Vertex toVert = vertEnum.next();
            Integer test = pathArr[currNode].distance;
            test = toVert.mDistance;
            test = pathArr[toVert.mVertId].distance;
            if(toVert.mDistance + pathArr[currNode].distance < pathArr[toVert.mVertId].distance){
                pathArr[toVert.mVertId].distance = toVert.mDistance + pathArr[currNode].distance;
                pathArr[toVert.mVertId].prevNode = currNode;
                queue.add(pathArr[toVert.mVertId]);
            }
        }
    }

    public void printShortestPath(int vert) {
        if(vert == 0){
            return;
        }
        if(!distancePrinted)
        {
            System.out.println("Shortest Distance to " + vert + " is: " + pathArr[vert].distance);
            distancePrinted = true;
        }
        printShortestPath(pathArr[vert].prevNode);
        if(pathArr[vert].prevNode == 0){
            System.out.print(pathArr[vert].nodeID);
        }else{
            System.out.print(" ---> " + pathArr[vert].nodeID);
        }
    }
}
