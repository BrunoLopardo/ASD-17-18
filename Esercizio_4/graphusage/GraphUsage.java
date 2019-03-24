package graphusage;

import priorityqueue.*;
import graph.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class GraphUsage {
  
  private static final Charset ENCODING = StandardCharsets.UTF_8;
  private static String startElem = null;
  private static void loadGraph(String filepath, UndirectedGraph<String> graph) throws IOException {

    System.out.print("\nLoading data from file...");
    Path inputFilePath = Paths.get(filepath);

    try(BufferedReader fileInputReader = Files.newBufferedReader(inputFilePath, ENCODING)) {
      String line = null;
      while((line = fileInputReader.readLine()) != null) {
      
        String[] tmp = line.split(",");

        String v1 = tmp[0];
        String v2 = tmp[1];
        double weight = Double.parseDouble(tmp[2]);
        startElem = v1;
        try {
          graph.addVertex(v1);
        } catch (Exception e) {}

        try {
          graph.addVertex(v2);
        } catch (Exception e) {}

        try {
          graph.addEdge(v1, v2, weight);
        } catch (Exception e) {}
      }
    }
    System.out.println(" Done.\n");

  }

  private static void testWithComparisonFunction(String filepath) throws IOException, GraphException, PriorityQueueException {
    UndirectedGraph<String> graph = new UndirectedGraph<>();
    loadGraph(filepath, graph);
    UndirectedGraph<String> afterPrim = graph.prim(startElem);

    System.out.println("Vertices number: " + afterPrim.getNumVertices() +" (expected: 18640)");
    System.out.println("Edges number: " + afterPrim.getNumEdges() +" (expected: 18637)");
    System.out.printf("Total Weight: %.2f (expected: 89939913)\n", afterPrim.getWeight());
  }

  /**
   * @param args: command line arguments. It should only contain one argument
   * specifying the filepath of the data file
   */
  public static void main(String[] args) throws IOException, Exception {
    if(args.length < 1)
      throw new Exception("Usage: GraphUsage <file_path_italian_dist_graph>");
    testWithComparisonFunction(args[0]);
  }

}