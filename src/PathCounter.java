import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {

	static int numVertices;
	static int numEdges;
	static int s;
	static int t;

	public static void main(String[] args) {
		// Read input file
		File inputFile;
		Scanner input = null;
		try {
			//cycle2, cycle7 give numerical answers instead of infinite
			inputFile = new File("cycle2.input");
			input = new Scanner(inputFile);
		} catch (NullPointerException | FileNotFoundException e) {
			System.err.println("Error: file not found");
			System.exit(1);
		}

		// Start parsing input file
		numVertices = input.nextInt();
		numEdges = input.nextInt();

		/* Create the graph:
		 * 'graph' is an adjacency list represented by an array of LinkedLists.
		 * Each index of the array represents a vertex, where each LinkedList 
		 * contains the edges adjacent to it.
		 */
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] graph = new LinkedList[numVertices];

		// Parse file for all edges and add to graph
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < numEdges; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			// Add an edge from a to b
			if (graph[a] == null) graph[a] = new LinkedList<Integer>();
			graph[a].add(b);
		}
        
		// Parse file for start and end points
		s = input.nextInt();
		t = input.nextInt();

		// Run a test
		try {
		System.out.println("There are " + findNumOfPaths(s, t, graph) + " paths from " + s + " to " + t + ".");
		}catch (StackOverflowError e) {
			System.out.println("There are infinite paths from " + s + " to " + t + ".");
		}
		System.out.println("How long this took: " + (System.currentTimeMillis()-startTime));
	}

	// Find the paths from point a to b
	// Initially, b is the final destination but it will recursively move up
	public static long findNumOfPaths(int a, int b, LinkedList<Integer>[] graph) {
		int numOfPaths = 0;
		if (a == b) {
			// If start point is the same as end point, we have found a complete path
			numOfPaths++;
		} else {
			// For every node
			for (int i = 0; i < graph.length; i++) {
				// If this node exists and is adjacent to end point
				if (graph[i] != null && graph[i].contains(b)) {
					// Find the number of paths from beginning to that node
					numOfPaths += findNumOfPaths(a, i, graph);
				}
			}
		}
		return numOfPaths;
	}

}
