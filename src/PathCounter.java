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
			inputFile = new File("simple0.input");
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
		LinkedList<Integer>[] graph = new LinkedList[numVertices];

		// Finish parsing input file
		while (input.hasNextInt()) {
			int a = input.nextInt();
			int b = input.nextInt();
			
			// If a and b are the last 2 values in the list then they are our start and end points
			if(!input.hasNextInt()) {
				s = a;
				t = b;
				break;
			}
			
			// Add an edge from a to b
			if (graph[a] == null) graph[a] = new LinkedList<Integer>();
			graph[a].add(b);
		}

		// Run a test
		System.out.println("There are " + findNumOfPaths(s, t, graph) + " paths from " + s + " to " + t + ".");
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
