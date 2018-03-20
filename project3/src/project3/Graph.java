package project3;
import  java.util.*;
import  java.io.*;

import java.lang.reflect.Array;

/**
 * Parser for .gr files. Creates an Adjacency List. 
 */

public class Graph {

    public class Vertex{
        Integer     mVertId;
        Integer     mDistance;
    }

    Vector<LinkedList<Vertex>>              mGraph;
    int                                     mVertexCount;
    int                                     mEdgeCount;

    // Constructor that uses an adjacency specified as a GR file

    public Graph (String fileName)
    {
        try{
            Scanner inFile = new Scanner(new FileReader(fileName));
            while (inFile.hasNextLine())
            {
                String  tok = inFile.next();
                tok.trim();

                //System.out.println ("Token found = '" + tok + "'");

                if (tok.equals("c")) {
                    inFile.nextLine();
                }

                else if (tok.equals("p"))
                {
                    String code = inFile.next();
                    mVertexCount = inFile.nextInt();
                    mEdgeCount = inFile.nextInt();
                    System.out.println ("Vertex Count " + mVertexCount);
                    mGraph = new Vector<LinkedList<Vertex>>(mVertexCount+1);
                    for (int i = 0; i <= mVertexCount; i++) {

                        mGraph.add(i, new LinkedList<Vertex>());
                    }
                    System.out.println ("Size of Vector = " + mGraph.size());
                    inFile.nextLine();
                }
                else if (tok.equals("a"))
                {
                    Integer fromVertex  = inFile.nextInt();
                    Integer toVertex    = inFile.nextInt();
                    Integer distance    = inFile.nextInt();
                    Vertex  v           = new Vertex();
                    v.mVertId           = toVertex;
                    v.mDistance         = distance;

                    System.out.println ("From -> " + fromVertex + " to " + toVertex + " Dist " + distance);
                    LinkedList<Vertex>      adj = mGraph.get(fromVertex);
                    adj.add(v);
                    inFile.nextLine();
                }
                else
                {
                    System.out.println ("Found an illegal code: " + tok);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println ("Caught Exception " + e.getMessage());
        }
    }

    // Process the line of text

    void PrintGraph ()
    {
        // Go through the Adjacency Matrix

        for (int vert = 1; vert <= mGraph.size(); vert++)
        {
           LinkedList<Vertex> adj = mGraph.get(vert);
           System.out.print ("From Vertex: " + vert);
           for (Iterator<Vertex> vertEnum = adj.iterator();
                   vertEnum.hasNext();)
            {
                Vertex     toVert = vertEnum.next();
                System.out.print (" " + toVert.mVertId + " (" + toVert.mDistance + ") ");
            }
            System.out.println();
        }
    }
}
