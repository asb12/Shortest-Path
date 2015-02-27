
import java.util.Scanner;

public class GraphTest1 {
    public static void main(String[] args) {
        IGraph<String,Integer> airlineGraph = new Graph<String,Integer>();

        String line;
        Scanner in = new Scanner(System.in);
        do {
        	line = in.nextLine();
        	if(line.equals("QUIT")) {
        		break;
        	}
        	String[] ar = line.split(" ");
        	if(ar[0].equals("+")) {
        		airlineGraph.addVertex(ar[1]);
        		System.out.println(airlineGraph.getVertex(ar[1]).toString() + " has been added");
        		airlineGraph.addVertex(ar[2]);
        		System.out.println(airlineGraph.getVertex(ar[2]).toString() + " has been added");
        		airlineGraph.addEdge(ar[1], ar[2], Integer.parseInt(ar[3]), ar[4]);
        		System.out.println("Edge between "+ ar[1] +" and " + ar[2] + " has been added");
        		System.out.println(airlineGraph.getEdge(ar[1], ar[2]));
        	}
        	
        	else if(ar[0].equals("-")) {
        		if(ar.length == 2) {
            		airlineGraph.removeVertex(ar[1]);
            		System.out.println(ar[1] + " has been removed");
            	}
        		
        		else {
        			airlineGraph.removeEdge(ar[1], ar[2]/*, Integer.parseInt(ar[3]), ar[4]*/);
        			System.out.println(airlineGraph.getEdge(ar[1], ar[2]) + " has been removed");
        		}
        	}
        	
        	else if(ar[0].equals("?")) {
        			if(ar.length == 3) {
        				airlineGraph.shortestPath(ar[1], ar[2]);
        			}
        			if(ar.length == 2) {
        				System.out.println(airlineGraph.getList(ar[1]));
        			}
        			
        			if(ar.length == 1) {
        				System.out.println(airlineGraph.toString());
        			}
        	}
        	
        }while(true);
    }
}
