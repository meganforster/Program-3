import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class PathCounter {
	public static void main(String[] args) {
		// Read input file
		File inputFile = null;
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
		ArrayList<Edge> edges = new ArrayList<Edge>();

		while (input.hasNextInt()) {
			edges.add(new Edge(input.nextInt(), input.nextInt()));
		}

		//TODO: calculations 
	}

}
