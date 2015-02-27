

import java.util.*;
import java.util.Map.Entry;


public class Graph<V, E> implements IGraph<V, E> {
	private Map<V, List<InnerEdge<E>>> adjacencyList; // [vertices] -> [edge]
	private Map<V, VertexInfo<V>> vertexInfo; // [vertex] -> [info]
	Hashtable<V, V> hashTable = new Hashtable<V, V>();

	public Graph() {
		this.adjacencyList = new HashMap<V, List<InnerEdge<E>>>();
		this.vertexInfo = new HashMap<V, VertexInfo<V>>();
	}

	public void addVertex(V v) {
		if (v == null) {
			throw new IllegalArgumentException("null");
		}
		else if (!hashTable.containsValue(v)) {
		adjacencyList.put(v, new ArrayList<InnerEdge<E>>());
		vertexInfo.put(v, new VertexInfo<V>(v));
		hashTable.put(v, v);
		}
	}

	public void removeVertex(V v) throws IllegalArgumentException {
		VertexInfo<V> vert = vertexInfo.get(v);
		for (V v1 : vertexInfo.keySet())
			removeEdge(v1,v);
		for (Edge<E> e : vert.outgoing.values()) {
//			System.out.println("outgoing "+adjacencyList);
			adjacencyList.remove(v);
		}
		/*
		for (Edge<E> e : vert.incoming.values()) {
			System.out.println("incoming "+adjacencyList);
			adjacencyList.remove(v);
		}
		*/
		vert.outgoing.clear();
		vert.incoming.clear();
		if (v == null) {
			throw new IllegalArgumentException("null");
		}
		vertexInfo.remove(v);
	}

	public void addEdge(V from, V to, E element, String name) {
		List<InnerEdge<E>> edgeList = adjacencyList.get(from);
		if (edgeList == null) {
			throw new IllegalArgumentException("source vertex not in graph");
		}

		InnerEdge<E> newEdge = new InnerEdge<E>(from, to, element, name);
		edgeList.add(newEdge);
		VertexInfo<V> vert1 = vertexInfo.get(from);
		VertexInfo<V> vert2 = vertexInfo.get(to);
		vert1.outgoing.put(to, newEdge);
		vert2.incoming.put(from, newEdge);
	}

	public void removeEdge(V from, V to) {
		List<InnerEdge<E>> edgeList = adjacencyList.get(from);
		VertexInfo<V> vert1 = vertexInfo.get(from);
		VertexInfo<V> vert2 = vertexInfo.get(to);
		if (edgeList == null) {
			throw new IllegalArgumentException("source vertex not in graph");
		}
		for (Iterator<InnerEdge<E>> it = edgeList.iterator(); it.hasNext();) {
			InnerEdge<E> e = it.next();
			if (e.to.equals(to)) {
				it.remove();
				vert1.outgoing.remove(to);
				vert2.incoming.remove(from);
			}
		}
	}

	public boolean hasEdge(V from, V to) {
		return getEdge(from, to) != null;
	}

	public Edge<E> getEdge(V from, V to) {
		List<InnerEdge<E>> edgeList = adjacencyList.get(from);
		if (edgeList == null) {
			throw new IllegalArgumentException("source vertex not in graph");
		}
		for (Iterator<InnerEdge<E>> it = edgeList.iterator(); it.hasNext();) {
			InnerEdge<E> e = it.next();
			if (e.to.equals(to)) {
				return e;
			}
		}
		return null;
	}
	
	public Vertex<V> getVertex(V v) {
		return vertexInfo.get(v);
	}

	public boolean hasPath(V v1, V v2) {
		return getDFSPath(v1, v2) != null;
	}

	public List<V> getDFSPath(V v1, V v2) {
		clearVertexInfo();

		List<V> path = new ArrayList<V>();
		getDFSPath(v1, v2, path);

		if (path.isEmpty()) {
			return null;
		} else {
			return path;
		}
	}

	private List<V> getDFSPath(V v1, V v2, List<V> path) {
		path.add(v1);
		VertexInfo<V> vInfo = vertexInfo.get(v1);
		vInfo.visited = true;

		if (v1.equals(v2)) {
			return path;
		}

		List<InnerEdge<E>> edges = this.adjacencyList.get(v1);
		for (InnerEdge<E> e : edges) {
			VertexInfo<V> vInfo2 = vertexInfo.get(e.to);
			if (!vInfo2.visited) {
				getDFSPath(e.to, v2, path);
				if (path.get(path.size() - 1).equals(v2)) {
					return path;
				}
			}
		}

		path.remove(v1);
		return path;
	}

	public String drq(V v1, V v2) {
		VertexInfo<V> source = vertexInfo.get(v1);
		source.dist = 0;

		PriorityQueue<VertexInfo<V>> queue = new PriorityQueue<VertexInfo<V>>();
		queue.add(source);

		while (!queue.isEmpty()) {
			VertexInfo<V> u = queue.poll();
			List<InnerEdge<E>> edge_list = this.adjacencyList.get(v1);
			if (edge_list == null)
				continue;
			for (InnerEdge<E> e : edge_list) {
				int weight = (Integer) e.element;
				String name = (String) e.name;
				VertexInfo<V> x = (VertexInfo<V>) e.to;
				int distance = u.dist + weight;
				if (distance < x.dist) {
					queue.remove(x);
					x.dist = distance;
					x.previous = u;
					queue.add(x);
				}
			}
		}
		return queue.toString();
	}

	public Queue<V> shortestPath(V v1, V v2) {
		VertexInfo<V> source = vertexInfo.get(v1);
		source.dist = 0;

		PriorityQueue<V> queue = new PriorityQueue<V>();
		queue.add(v1);	
//		System.out.println(queue);
		while (!queue.isEmpty()) {
			VertexInfo<V> u = vertexInfo.get(queue.poll());
			System.out.println("vertex "+u.v);
			List<InnerEdge<E>> edge_list = adjacencyList.get(v1);
			System.out.println(adjacencyList.get(v1));
			if (edge_list == null) continue;
			for (InnerEdge<E> e : edge_list) {
				VertexInfo<V> x = vertexInfo.get(e.to);
//				System.out.println(e);
//				System.out.println("outgoing"+vertexInfo.get(e.to).outgoing);
				int weight = (Integer)e.element;
				String name = e.name;
				int distance = u.dist + weight;
//				System.out.println(" "+x.dist);
				if (distance < x.dist) {
					System.out.println("1"+queue);
					queue.remove(e.to);
					System.out.println("2"+queue);
					vertexInfo.get(e.to).dist = distance;
					vertexInfo.get(e.to).previous = u;
					queue.add(e.to);	
					System.out.println("3"+queue);
				}	
				System.out.println("4"+queue);
			}
//			System.out.println(queue);
		}
		return queue;	
	}
	/*
	public void output(V v1, V v2) {
		VertexInfo<V> source = vertexInfo.get(v1);
		for (Entry<V, VertexInfo<V>> v : vertexInfo.entrySet())
		{
		    System.out.println("Distance to " + v + ": " + v.getValue().dist);
		    List<VertexInfo<V>> path = (List<VertexInfo<V>>) getShortestPathTo((V) v);
		    System.out.println("Path: " + path);
		}
	}
	*/
    public List<V> getShortestPathTo(V target)
    {
        List<VertexInfo<V>> path = new ArrayList<VertexInfo<V>>();
        for (VertexInfo<V> vertex = (VertexInfo<V>) target; vertex != null; vertex = vertex.previous)
            path.add(vertex);
        Collections.reverse(path);
        return (List<V>)path;
    }
	
	public String toString() {
		Set<V> keys = adjacencyList.keySet();
		String str = "";

		for (V v : keys) {
			str += v + ": ";

			List<InnerEdge<E>> edgeList = adjacencyList.get(v);

			for (InnerEdge<E> edge : edgeList) {
				str += edge + "  ";
			}
			str += "\n";
		}
		return str;
	}

	protected final void clearVertexInfo() {
		for (VertexInfo<V> info : this.vertexInfo.values()) {
			info.clear();
		}
	}

	public class VertexInfo<V> implements Vertex<V> {
		/** The vertex itself. */
		public V v;
		protected Map<V, Edge<E>> outgoing, incoming;
		public int dist = 1000000000;
		public VertexInfo<V> previous;
		/**
		 * A mark for whether this vertex has been visited. Useful for path
		 * searching.
		 */
		public boolean visited;
		

		/** Constructs information for the given vertex. */
		public VertexInfo(V v) {
			this.v = v;
			this.clear();
			outgoing = new Hashtable<>();
			incoming = new Hashtable<>();
		}

		public int compareTo(VertexInfo<V> other) {
			return Double.compare(dist, other.dist);
		}

		/** Resets the visited field. */
		public void clear() {
			this.visited = false;
		}
		
		public String toString() {
			return (String)v;
		}
		
		private V key() {
			return v;
		}
	}

	private class InnerEdge<E> implements Edge<E> {
		private V from, to;
		private E element;
		private String name;

		private InnerEdge(V from, V to, E element, String name) {
			if (from == null || to == null) {
				throw new IllegalArgumentException("null");
			}
			this.from = from;
			this.to = to;
			this.element = element;
			this.name = name;
		}

		public String toString() {
			return "<" + from + ", " + to + ", " + element + ", " + name + ">";
		}
	}
	
	public String getList(V v) {
		VertexInfo<V> vert = vertexInfo.get(v);
		String result = "";
		if (!(vert.incoming.values().isEmpty()) && !(vert.outgoing.values().isEmpty())) {
		result = vert.incoming.values().toString() + vert.outgoing.values().toString();
		} else if (vert.incoming.values().isEmpty()) {
			result = vert.outgoing.values().toString();
		} else if (vert.outgoing.values().isEmpty()) {
			result = vert.incoming.values().toString();
		} 
		return result;
	}
}
