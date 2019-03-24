package graph;

/**
 * A vertex in a graph, with methods to access the properties.
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class Vertex <E> {

	private E elem;
  private double key;
  private E parent;

	public Vertex(E elem) {
		this.elem = elem;
	}

  /**
   * Returns the value of the vertex.
   * @return the value of this vertex
   */
	public E getElem() {
		return this.elem;
	}
  
  /**
   * Returns the key of the vertex.
   * @return the key of this vertex
   */
  public double getKey() {
    return this.key;
  }

  /**
   * Sets the key of the vertex.
   * @param key the new key of this vertex
   */
  public void setKey(double key) {
    this.key = key;
  }

  /**
   * Returns the parent of the vertex.
   * @return the parent of this vertex
   */
  public E getParent() {
    return this.parent;
  }

  /**
   * Sets the parent of the vertex.
   * @param parent the new parent of this vertex
   */
  public void setParent(E parent) {
    this.parent = parent;
  }

  /**
   * Returns a string representation of the vertex.
   * @return a string representation of the vertex
   */
  public String toString() {
    return elem + "";
  }
}