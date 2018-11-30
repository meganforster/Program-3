public class Edge {
    public int a,b;
    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public Edge addEdge(int a, int b) {
    	return new Edge(a,b);
    }
    
    public Edge addAdjacentEdge(int a, int b) {
    	return new Edge(a,b);
    }
    
}
