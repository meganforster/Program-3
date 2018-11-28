import java.util.ArrayList;
import java.io.File;

public class PathCounter {
	public static void main(String[] args) {
		// Read input file
		try {
            File input = new File("simple0.input");
        } catch (NullPointerException e) {
            System.err.println("Error: file" + input.getName() + "not found");
            System.exit(1);
}
		// Parse input file
		int numVertices;
		int numEdges;
		ArrayList<Edge> edges = new ArrayList<Edge>();

		//TODO: make first line assign numVertices and numEdges
		//TODO: for each additional line, make new edge and add to ArrayList

		//TODO: calculations 
	}

	private class Edge {
		public int a,b;
		public Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
