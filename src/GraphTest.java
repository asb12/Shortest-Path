import java.util.List;


public class GraphTest {
    public static void main(String[] args) {
        IGraph<String,Integer> airlineGraph = new Graph<String,Integer>();
        
        buildGraph(airlineGraph);
        System.out.println(airlineGraph.toString());
        
        testEdges(airlineGraph);
        System.out.println();
        
        //testDFS(airlineGraph);
    }
    
    public static void testEdges(IGraph<String,Integer> g) {
 //       System.out.println("Edge between JFK and SFO? " + g.hasEdge("JFK", "SFO"));
 //       System.out.println("Edge between JFK and SFO: " + g.getEdge("JFK", "SFO"));
        
        System.out.println("Edge between MIA and LAX? " + g.hasEdge("MIA", "LAX"));
        System.out.println("Edge between MIA and LAX: " + g.getEdge("MIA", "LAX"));      
        
        System.out.println("Edge between MIA and ORD? " + g.hasEdge("MIA", "ORD"));
        System.out.println("Edge between MIA and ORD: " + g.getEdge("MIA", "ORD"));
        
        System.out.println("Edge between SFO and DFW? " + g.hasEdge("SFO", "DFW"));
        System.out.println("Edge between SFO and DFW: " + g.getEdge("SFO", "DFW"));  
        
        System.out.println("Shortest path between MIA to ORD: " + g.shortestPath("DFW", "ORD"));
        
        System.out.println("List of LAX: "+g.getList("LAX"));
    }
    
    public static void buildGraph(IGraph<String,Integer> g) {
        g.addVertex("LAX");
        g.addVertex("SFO");
        g.addVertex("DFW");
        g.addVertex("ORD");
        g.addVertex("MIA");
        g.addVertex("JFK");
        g.addVertex("BOS");
        
//        g.removeVertex("BOS");
//        g.removeVertex("LAX");
//        g.removeVertex("SFO");
//        g.removeVertex("DFM");
//        g.removeVertex("ORD");
//        g.removeVertex("JFK");
        
        g.addEdge("LAX", "ORD", 1500, "plane");
        g.addEdge("DFW", "LAX", 800, "plane" );
        g.addEdge("DFW", "ORD", 600, "plane");        
        g.addEdge("DFW", "SFO", 1100, "plane");
        g.addEdge("DFW", "JFK", 950, "plane");        
        g.addEdge("ORD", "DFW", 600, "plane");
        g.addEdge("MIA", "DFW", 1200, "plane");
        g.addEdge("MIA", "LAX", 2800, "plane");  
        g.addEdge("JFK", "MIA", 1300, "plane");
        g.addEdge("JFK", "SFO", 3600, "plane");
        g.addEdge("JFK", "BOS", 300, "plane");
        g.addEdge("BOS", "JFK", 300, "plane");
        g.addEdge("BOS", "MIA", 1500, "plane");
        
//        g.removeVertex("BOS");
        g.removeVertex("JFK");
//        g.removeEdge("LAX", "ORD");
//        g.removeEdge("DFW", "LAX");
//        g.removeEdge("DFW", "ORD");        
//        g.removeEdge("DFW", "SFO");
//        g.removeEdge("DFW", "JFK");        
//        g.removeEdge("ORD", "DFW");
//        g.removeEdge("MIA", "DFW");
//        g.removeEdge("MIA", "LAX");  
//        g.removeEdge("JFK", "MIA");
//        g.removeEdge("JFK", "SFO");
//        g.removeEdge("JFK", "BOS");
//        g.removeEdge("BOS", "JFK");
//        g.removeEdge("BOS", "MIA"); 
    }
}
