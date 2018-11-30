import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {

	static int numVertices;

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
		numVertices = input.nextInt();
		int numEdges = input.nextInt();
		/*
		 * Graph is an array of LinkedLists. Each index represents a vertex, where each
		 * LinkedList in the array represents the edges coming off of the vertex, making
		 * a list of its children.
		 */
		LinkedList<Integer>[] graph = new LinkedList[numVertices];
		while (input.hasNextInt()) {
			int a = input.nextInt();
			int b = input.nextInt();
			// Add the child b to the ath position of the array, so index a represents a
			// list of all nodes a points to
			// i.e. add an adge from a to b
			if (graph[a] == null)
				graph[a] = new LinkedList<Integer>();
			graph[a].add(b);
		}
		// TODO: test that this graph works
		// TODO: calculations
		System.out.println(findNumOfPaths(1, 7, graph));

	}
	// a is starting point
	// b is finish point
	// x is current pointer

	public static long findNumOfPaths(int a, int b, LinkedList<Integer>[] graph) {
		int numOfPaths = 0;
		long check = 0;
		int x = b;

			if (x == a) {
				numOfPaths++;
				check = 0;
				return numOfPaths;
			} else {
				for (LinkedList<Integer> list : graph) {
					if (list != null && list.contains(x)) {
						
						for (Integer i : list) {

							findNumOfPaths(a, x, graph);

							if (check < numVertices) {
								if (i == x) {
									check++;
									pointer = false;
								} else {
									findNumOfPaths(a, x, graph);
								}
							} //
							System.out.println(i);
						}
					}
				}

		}
		System.out.println(numOfPaths);
		return numOfPaths;
	}

}
