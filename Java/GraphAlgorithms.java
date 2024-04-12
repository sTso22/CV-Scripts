import java.util.*;

public class GraphAlgorithms {
/*----------------Graph used for Dijkstra's Algorithm
     ┌──────┐                             ┌──────┐
	 │      │                      3      │      │     2     
	 │   A  │     4            ┌──────────┤   D  ├──────────┐
	 │      ├───────────┐      │          │      │          │
	 └───┬──┘           │      │          └──────┘          │
	     │              │      │                            │
	     │              ├──────┤                         ┌──┴───┐
	   4 │              │      │             6           │      │
	     │              │   C  ├─────────────────────────┤   F  │
	     │              │      │                         │      │
	     │              ├──────┤                         └───┬──┘
	  ┌──┴───┐          │      │          ┌──────┐           │
	  │      │          │      │          │      │           │
	  │   B  ├──────────┘      └──────────┤   E  ├───────────┘
	  │      │        2              1    │      │        3
	  └──────┘                            └──────┘
	
------------------Graph used for Ford-Fulkerson's Algorithm 
               ┌──────┐         ┌───────┐
               │      │   0/9   │       │      0/2
       0/8     │  A   ├────────►│   B   ├─────────┐
       ┌───────►      │         │       │         │
       │       └──────┘         └▲──────┘         │
       │                         │                │
┌──────┤                         │             ┌──▼────┐
│      │               ┌─────────┘             │       │
│  S   │               │   0/7                 │   T   │
│      │               │                       │       │
└──────┤               │                       └──▲────┘
       │       ┌───────┤         ┌──────┐         │
       │       │       │         │      │         │
       └──────►│   D   ├─────────►  C   ├─────────┘
        0/3    │       │   0/4   │      │    0/5
               └───────┘         └──────┘
 */
	void Djikstra(int[][] graph, int node) {
		// FUNCTION:
		// Calculates the minimum distance from one node
		// Arguments: int[][] graph (the given weighted graph), int node (the selected node)
		
		int nodes = graph.length; 	// number of nodes in the graph
		int[] visited = new int[nodes];	// array to store which nodes we hava visited (0 for not visited, 1 for visited)
		int[] distance = new int[nodes]; 	// array for storing the distance of the node to the rest of the nodes of the graph
		for(int i=0;i<nodes;i++) {
			visited[i] = 0;
			distance[i] = Integer.MAX_VALUE;	// used maximum value of an integer as infinity (could use inf but I would have to change the int arrays to double)	
		}
		distance[node] = 0;	// distance from self is 0
		for(int i=0;i<nodes;i++) {
			int min_distance = Integer.MAX_VALUE;	// initialize the minimum distance to ~inf
			int min = -1;		// initialize the minimum distance node to -1
			for(int j=0;j<distance.length;j++) {
				if(visited[j] == 0 && distance[j] < min_distance) {	// find the node with the minimum distance
					min_distance = distance[j];
					min = j;
				}
			}
			visited[min] = 1;
			for(int k=0;k<nodes;k++) {	// update all distances
				if(visited[k] == 0 && graph[min][k]!=0 && distance[min]!=Integer.MAX_VALUE && (distance[min] + graph[min][k]) < distance[k]){
					distance[k] = distance[min] + graph[min][k];				
				}
			}
		}
		char[] node_map = {'A','B','C','D','E','F'};
		for(int i=0;i<nodes;i++) {
			System.out.println("The distance from node "+node_map[node]+" to node "+node_map[i]+" is "+distance[i]);
		}
	}
	
	void FordFulkerson(int[][] graph, int source, int sink) {
		// FUNCTION:
		// Calculates the maximum flow from source S to sink T in the weighted graph
		// Arguments: int[][] graph (the given weighted graph), int source the graphs source, int sink the graphs sink
		
		int u;
	    int Graph[][] = new int[graph.length][graph[0].length];	// create a copy of the weighted graph
	    Graph = graph;
	    int nodes = graph.length;
	    int p[] = new int[nodes];

	    int max_flow = 0;
	    
	    while (bfs(Graph, source, sink, p)) {	// Search for paths from source to sink
	      int path_flow = Integer.MAX_VALUE;
	      for (int i = sink; i != source; i = p[i]) {
	        u = p[i];
	        path_flow = Math.min(path_flow, Graph[u][i]);
	      }

	      for (int i = sink; i != source; i = p[i]) {
	        u = p[i];
	        Graph[u][i] -= path_flow;
	        Graph[i][u] += path_flow;
	      }

	      max_flow = max_flow + path_flow;	// Updating the max flow
	    }

	    System.out.println("The maximum flow from source S to sink T is: "+max_flow);
	}
	
	 boolean bfs(int graph[][], int start, int target, int parent[]) {
		 // FUNCTION:
		 // Breadth-First_search for finding paths from source to sink
		 // Arguments: int[][] graph (the given weighted graph), int start the starting node, int target the destination node, int[] parent the array that contains the path
		 
		 int nodes = graph.length;
		 boolean visited[] = new boolean[nodes];
		 for (int i = 0; i < nodes; ++i) {
		    visited[i] = false;
		 }
		 LinkedList<Integer> queue = new LinkedList<Integer>();
		 queue.add(start);
		 visited[start] = true;
		 parent[start] = -1;

		 while (queue.size() != 0) {
		    int u = queue.poll();

		    for (int v = 0; v < nodes; v++) {
		      if (visited[v] == false && graph[u][v] > 0) {
		        queue.add(v);
		        parent[v] = u;
		        visited[v] = true;
		      }
		     }
		  }
		  return (visited[target] == true);
	 }
	
	void printGraph(int mode) {
		// FUNCTION:
		// Prints the graph to the console for the user
		// Arguments: int mode for choosing graphD or graphF
		
		if(mode == 1) {	// Graph for Dijkstra
			System.out.println("     	 ┌──────┐                             ┌──────┐\r\n"
					+ "	 │      │                      3      │      │     2     \r\n"
					+ "	 │   A  │     4            ┌──────────┤   D  ├──────────┐\r\n"
					+ "	 │      ├───────────┐      │          │      │          │\r\n"
					+ "	 └───┬──┘           │      │          └──────┘          │\r\n"
					+ "	     │              │      │                            │\r\n"
					+ "	     │              ├──────┤                         ┌──┴───┐\r\n"
					+ "	   4 │              │      │             6           │      │\r\n"
					+ "	     │              │   C  ├─────────────────────────┤   F  │\r\n"
					+ "	     │              │      │                         │      │\r\n"
					+ "	     │              ├──────┤                         └───┬──┘\r\n"
					+ "	  ┌──┴───┐          │      │          ┌──────┐           │\r\n"
					+ "	  │      │          │      │          │      │           │\r\n"
					+ "	  │   B  ├──────────┘      └──────────┤   E  ├───────────┘\r\n"
					+ "	  │      │        2              1    │      │        3\r\n"
					+ "	  └──────┘                            └──────┘");
		}
		else {			//Graph for Ford-Fulkerson's
			System.out.println("               ┌──────┐         ┌───────┐\r\n"
					+ "               │      │   0/9   │       │      0/2\r\n"
					+ "       0/8     │  A   ├────────►│   B   ├─────────┐\r\n"
					+ "       ┌───────►      │         │       │         │\r\n"
					+ "       │       └──────┘         └▲──────┘         │\r\n"
					+ "       │                         │                │\r\n"
					+ "┌──────┤                         │             ┌──▼────┐\r\n"
					+ "│      │               ┌─────────┘             │       │\r\n"
					+ "│  S   │               │   0/7                 │   T   │\r\n"
					+ "│      │               │                       │       │\r\n"
					+ "└──────┤               │                       └──▲────┘\r\n"
					+ "       │       ┌───────┤         ┌──────┐         │\r\n"
					+ "       │       │       │         │      │         │\r\n"
					+ "       └──────►│   D   ├─────────►  C   ├─────────┘\r\n"
					+ "        0/3    │       │   0/4   │      │    0/5\r\n"
					+ "               └───────┘         └──────┘");
		}
	}
	
	public static void main(String[] args) {
		// graph for Dijkstra's Algorithm (Adjacency Matrix)
		int graphD[][] = { {0,4,4,0,0,0},
						   {4,0,2,0,0,0},
						   {4,2,0,3,1,6},
						   {0,0,3,0,0,2},
						   {0,0,1,0,0,3},
						   {0,0,6,2,3,0} };
		// graph for Ford-Fulkerson's Algorithm
		int graphF[][] = { {0,8,0,0,3,0},
						   {0,0,9,0,0,0},
						   {0,0,0,0,7,2},
						   {0,0,0,0,0,5},
						   {0,0,7,4,0,0},
						   {0,0,0,0,0,0} };
		GraphAlgorithms ga = new GraphAlgorithms();
		int app_flag = 1;
		Scanner scan = new Scanner(System.in);
		while(app_flag == 1) {
			System.out.println("-----------User Menu-----------");
			System.out.println("Select an Algorithm: ");
			System.out.println("1. Dijkstra's minimum path Algorithm ");
			System.out.println("2. Ford-Fulkerson's maximum flow Algorithm ");	
			System.out.println("3. End Application");
			System.out.println("-------------------------------");
			int choice = scan.nextInt();
			if(choice == 1) {
				ga.printGraph(1);
				System.out.println("Select the starting node of the graph (A-F): ");
				char node = scan.next().charAt(0);
				char node_char_map[] = {'A','B','C','D','E','F'};
				int index = new String(node_char_map).indexOf(node);
				ga.Djikstra(graphD,index);
			}
			if(choice == 2) {
				ga.printGraph(2);
				ga.FordFulkerson(graphF, 0, 5);
			}
			if(choice == 3) {
				app_flag = 0;
				scan.close();
			}
		}
	}

}
