import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {

	static int numVertices;
	static int numEdges;
	static int s;
	static int t;

	// Array for every node that holds the value, every element orginally stores -1,
	// so once its computed put it in indicy

	public static void main(String[] args) {
		// Read input file
		File inputFile;
		Scanner input = null;
		try {
			// cycle2, cycle7 give numerical answers instead of infinite
			inputFile = new File("simple8.input");
			input = new Scanner(inputFile);
		} catch (NullPointerException | FileNotFoundException e) {
			System.err.println("Error: file not found");
			System.exit(1);
		}

		// Start parsing input file
		numVertices = input.nextInt();
		numEdges = input.nextInt();

		/*
		 * Create the graph: 'graph' is an adjacency list represented by an array of
		 * LinkedLists. Each index of the array represents a vertex, where each
		 * LinkedList contains the edges adjacent to it.
		 */
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] graph = new LinkedList[numVertices];

		long[] paths = new long[numVertices]; // should have been called memo bc memoizing
		for (int i = 0; i < paths.length; i++) { // -1 tells us the paths from a to the current node have not been
													// calculated yet
			paths[i] = -1;
		}

		// Parse file for all edges and add to graph
		
		for (int i = 0; i < numEdges; i++) {
			int a = input.nextInt();
			int b = input.nextInt();
			// Add an edge from a to b
			if (graph[a] == null)
				graph[a] = new LinkedList<Integer>();
			graph[a].add(b);
		}

		// Parse file for start and end points
		s = input.nextInt();
		t = input.nextInt();

		// Run a test

		long finalTotal = findNumOfPaths(s, t, graph, paths);
		if (finalTotal == -3) {
			System.out.println("INFINITE");

		} else {

			System.out
					.println(finalTotal);
		}

		
	}

	// Find the paths from point a to b
	// Initially, b is the final destination but it will recursively move up
	public static long findNumOfPaths(int a, int b, LinkedList<Integer>[] graph, long[] paths) {
		long total = 0;

		// -4 means cycle detectected but maybe isnt infinite
		if (paths[b] == -2) {
			paths[b] = -4;
			return 0;
		} else if (paths[b] != -1) {
			return paths[b];
		} else {
			paths[b] = -2;
			if (a == b) {
				total = 1;
			}
			// For every node
			for (int i = 0; i < graph.length; i++) {
				// If this node exists and is adjacent to end point
				if (graph[i] != null && graph[i].contains(b)) {
					// Find the number of paths from beginning to that node
					long result = findNumOfPaths(a, i, graph, paths);
					if (result == -3) {
						paths[b] = -3;
						return -3;
					}
					total = total + result;
				}
			}
		}
		if (paths[b] == -4) {
			if (total > 0) {
				paths[b] = -3;
				return -3;
			} else if (total == 0) {
				paths[b] = 0;
				return 0;
			}

		} else {
			paths[b] = total; // memoize the result
		}
		return total;
	}

}
