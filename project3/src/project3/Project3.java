/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author mike
 */
public class Project3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String grFile = args[0];
        Integer startVert = Integer.parseInt(args[1]);
        Integer endVert = Integer.parseInt(args[2]);

        // Create Graph Object
        Graph graph = new Graph(grFile);
        Path paths = new Path(graph, startVert);
        // Go through the relaxation process taking closest vertex from PQ
        Integer w;
        while ((w = paths.getNextVertex()) != null) {
            paths.applyRelaxation(w);
        }
        // Print the shortest path
        paths.printShortestPath(endVert);
    }
}
    
