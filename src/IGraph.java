import java.util.*;


public interface IGraph<V,E> {
    public void addVertex(V v);
    
    public void removeVertex(V v) throws IllegalArgumentException;
    
    public void addEdge(V v1, V v2, E element, String name);
    
    public void removeEdge(V v1, V v2);
    
    public boolean hasEdge(V v1, V v2);
    
    public Edge<E> getEdge(V v1, V v2);
    
    public Vertex<V> getVertex(V v);
    
    public boolean hasPath(V v1, V v2);

    public List<V> getDFSPath(V v1, V v2);
    
    public String drq(V v1, V v2);
    
    public Queue<V> shortestPath(V v1, V v2);
    
    public List<V> getShortestPathTo(V target);
    
    public String getList(V v);
      	      
    public String toString();
}
