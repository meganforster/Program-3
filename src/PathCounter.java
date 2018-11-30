import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {
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

		// Parse input file
		int numVertices = input.nextInt();
		int numEdges = input.nextInt();
		/* Graph is an array of LinkedLists. Each index represents a vertex, where each LinkedList in the array
		 * represents the edges coming off of the vertex, making a list of its children.
		 */
		LinkedList[] graph = new LinkedList[numVertices];
		while (input.hasNextInt()) {
			int a = input.nextInt();
			int b = input.nextInt();
			// Add the child b to the ath position of the array, so index a represents a list of all nodes a points to
			// i.e. add an adge from a to b
			if (graph[a] == null) graph[a] = new LinkedList();
			graph[a].add(b);
		}
		//TODO: test that this graph works
		//TODO: calculations
	}

}
