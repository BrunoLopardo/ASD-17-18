package graph;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import priorityqueue.*;

/**
 * Class that ovverrides addEdge() and getWeight() methods of the superclass 
 * Graph in order to represent an undirected graph. The class provides
 * also the Prim's algorithm.
 * This class uses the priorityqeueue package for the Prim's algorithm.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class UndirectedGraph <E> extends Graph <E>{

  /**
   * Add an undirected edge in the graph between the vertex 
   * whose element is elem1 and the vertex whose element is elem2.
   * @param  elem1 the element of the first vertex
   * @param  elem2 the element of the second vertex
   * @param  weight weight of the edge
   * @throws graph.GraphException if elem1 or elem2 are null
   * @throws graph.GraphException if elem1 or elem2 are not in the graph
   * @throws graph.GraphException if elem1 and elem2 are the same element
   * @throws graph.GraphException if the edge already exist in the graph
   */
  @Override
  public void addEdge(E elem1, E elem2, Double weight) throws GraphException {
    if (elem1.equals(elem2)) throw new GraphException("No loops allowed in undirected graph");
    if (edgeExists(elem1, elem2)) throw new GraphException("Edge already in the graph");
    if (weight == null) weighed = false;

    Edge<E> edge = new Edge<>(getVertex(elem1), getVertex(elem2), weight);
    Edge<E> edge2 = new Edge<>(getVertex(elem2), getVertex(elem1), weight);
    addAdj(getVertex(elem1), edge);
    addAdj(getVertex(elem2), edge2);
    numEdges++;
  }

  /**
   * Compute a minimum spanning tree (or forest) of an undirected edge-weighted graph.
   * @param startElem the element of the vertex from which start the algorithm
   * @return a Minimum Spanning Tree for the graph
   */
  public UndirectedGraph<E> prim(E startElem) throws PriorityQueueException, GraphException {
    UndirectedGraph<E> forest = new UndirectedGraph<>();
    PriorityQueue<E, Double> queue = new PriorityQueue<>(new DoubleComparator());

    for (Vertex<E> v : vertices.values()) {
      v.setKey(Double.MAX_VALUE);
      v.setParent(null);
    }

    getVertex(startElem).setKey(0);

    for (Vertex<E> v : vertices.values()) {
      queue.insert(v.getElem(), v.getKey());
    }

    while (!queue.isEmpty()) {
      E uElem = queue.extractMax();
      forest.addVertex(uElem);
      Vertex<E> u = getVertex(uElem);
      if (u.getParent() != null) { 
        forest.addEdge(u.getParent(), uElem, getEdgeWeight(u.getParent(), uElem));
      }
      for (Vertex<E> v : getAdj(uElem)) {
        if (queue.contains(v.getElem())) {
          if (v.getKey() > getEdgeWeight(uElem, v.getElem())) {
            v.setKey(getEdgeWeight(uElem, v.getElem()));
            v.setParent(uElem);
            queue.updatePriority(v.getElem(), getEdgeWeight(uElem, v.getElem()));
          }
        }
      }
    }
    return forest;
  }

  /**
   * Returns the weight of the graph.
   * @return the weight of the graph
   */
  @Override
  public double getWeight() throws GraphException {
    return super.getWeight()/2;
  }
}