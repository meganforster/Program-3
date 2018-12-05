import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {

	// Test-specific variables
	static int numVertices;
	static int numEdges;
	static int s;
	static int t;
	
	// Constants
	static final int INFINITE_NOT_IN_PATH = -4; // Infinite loop not in the path from s to t (doesn't affect answer)
	static final int INFINITE_IN_PATH = -3;     // Infinite loop in the path (infinite paths from s to t)
	static final int VISITED = -2;              // Node has already been visited
	static final int NOT_VISITED = -1;          // Number of paths has not yet been calculated 
 
	public static void main(String[] args) {
		// Read input file
		File inputFile;
		Scanner input = null;
		try {
			inputFile = new File("simple8.input");
			input = new Scanner(inputFile);
		} catch (NullPointerException | FileNotFoundException e) {
			System.err.println("Error: file not found");
			System.exit(1);
		}

		// Start parsing input file
		numVertices = input.nextInt();
		numEdges = input.nextInt();

		/* Create the graph: 'graph' is an adjacency list represented by an array of
		 * LinkedLists. Each index of the array represents a vertex, where each
		 * LinkedList contains the edges adjacent to it.
		 */
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] graph = new LinkedList[numVertices];

		/* Create an array that represents the number of paths from each node.
		 * This array helps remove the need for recalculating the number of paths.
		 */
		long[] paths = new long[numVertices];
		
		// Initialize all indices to NOT_VISITED to indicate the number of paths from this node hasn't been calculated yet
		for (int i = 0; i < paths.length; i++) { 
			paths[i] = NOT_VISITED;
		}

		// Parse file for all edges and add to graph
		for (int i = 0; i < numEdges; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			// Add an edge from a to b
			if (graph[a] == null) {
				graph[a] = new LinkedList<Integer>();
			}
			graph[a].add(b);
		}

		// Parse file for start and end points
		s = input.nextInt();
		t = input.nextInt();

		// Run a test
		long finalTotal = findNumOfPaths(s, t, graph, paths);
		if (finalTotal == INFINITE_IN_PATH) {
			System.out.println("INFINITE");
		} else {
			System.out.println(finalTotal);
		}
	}

	/* Find the paths from point a to b
	 * Initially, b is the final destination but it will recursively move up the graph
	 */
	public static long findNumOfPaths(int a, int b, LinkedList<Integer>[] graph, long[] paths) {
		long total = 0;

		// If this node has already been visited, mark it as a loop
		if (paths[b] == VISITED) {
			paths[b] = INFINITE_NOT_IN_PATH;
			return 0;
		} 
		//TODO 
		else if (paths[b] != NOT_VISITED) {
			return paths[b];
		} 
		//TODO
		else {
			paths[b] = VISITED;
			// If you have reached the top, mark as complete path (base case)
			if (a == b) {
				total = 1;
			}
			// For every node
			for (int i = 0; i < graph.length; i++) {
				// If this node exists and is adjacent to end point
				if (graph[i] != null && graph[i].contains(b)) {
					// Find the number of paths from beginning to that node
					long result = findNumOfPaths(a, i, graph, paths);
					// If there is an infinite loop in the path
					if (result == INFINITE_IN_PATH) {
						paths[b] = INFINITE_IN_PATH;
						return INFINITE_IN_PATH;
					}
					total += result;
				}
			}
		}
		// If the current node is part of an infinite loop, find out if it's in our path
		if (paths[b] == INFINITE_NOT_IN_PATH) {
			if (total > 0) {
				paths[b] = INFINITE_IN_PATH;
				return INFINITE_IN_PATH;
			} else if (total == 0) {
				paths[b] = 0;
				return 0;
			}
		} else {
			// Save the result
			paths[b] = total;
		}
		return total;
	}
}
