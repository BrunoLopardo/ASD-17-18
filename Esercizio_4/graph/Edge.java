package graph;

/**
 * An Edge links two vertices. Each edge has both a source node and a target 
 * node. An edge can have a weight. This class provides methods for the management of edges in a graph.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class Edge <E> {

	private Vertex<E> vertex1;
	private Vertex<E> vertex2;
	private Double weight;

	public Edge(Vertex<E> vertex1, Vertex<E> vertex2, Double weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

  /**
   * Returns the first vertex of the edge.
   * @return the first vertex of this edge
   */
  public Vertex<E> getVertex1() {
    return vertex1;
  }

  /**
   * Returns the second vertex of the edge.
   * @return the second vertex of this edge
   */
  public Vertex<E> getVertex2() {
    return vertex2;
  }

  /**
   * Returns the weight of the edge.
   * @return the weight of this edge
   */
  public Double getWeight() {
    return weight;
  }

  /**
   * Returns a string representation of the edge.
   * @return a string representation of the edge
   */
  public String toString() {
    return vertex2 + "," + weight;
  }
}