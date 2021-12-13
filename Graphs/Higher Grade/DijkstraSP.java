package assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DijkstraSP {
	private double[] distTo; // distTo[v], holds the distance of shortest s to v path
	private DirectedEdge[] edgeTo; // edgeTo[v], holds the last edge on shortest s to v path
	private boolean[] visited;

	/**
	 * Computes a shortest-paths tree from the source vertex s to every other vertex
	 * in the edge-weighted graph.
	 *
	 * @param graph the edge-weighted graph.
	 * @param s     the source vertex.
	 * @throws IllegalArgumentException if the weight of an edge is negative.
	 * @throws IllegalArgumentException if s is out of bounds.
	 */
	public DijkstraSP(EdgeWeightedDigraph graph, int s) {
		for (DirectedEdge e : graph.edges()) {
			if (e.weight() < 0)
				throw new IllegalArgumentException("The weight of the edge " + e + " is negative.");
		}

		distTo = new double[graph.V()];
		edgeTo = new DirectedEdge[graph.V()];
		visited = new boolean[graph.V()];

		validateVertex(s);

		for (int v = 0; v < graph.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY; // Init
		}
		distTo[s] = 0.0;

		for (int i = 0; i < graph.V(); i++) {
			int minvert = minDistance(distTo, visited);
			visited[minvert] = true;
			for (DirectedEdge ed : graph.adj(minvert)) {
				if (!visited[ed.to()] && distTo[minvert] != Double.POSITIVE_INFINITY) {
					double newdist = distTo[minvert] + ed.weight();
					// System.out.println(ed+" "+ed.from()+" "+distTo[ed.from()]);
					if (newdist < distTo[ed.to()]) {
						distTo[ed.to()] = newdist;
						edgeTo[ed.to()] = ed;
					}
				}
			}

		}
//		for (int j = 0; j < graph.V(); j++)
//			System.out.println(j + " " + distTo[j] + " " + edgeTo[j]);
	}

	private int minDistance(double[] distTo2, boolean[] visited) {
		int minvertex = -1;
		for (int i = 0; i < distTo2.length; i++) {
			if (!visited[i] && (minvertex == -1 || distTo2[i] < distTo2[minvertex])) {
				minvertex = i;
			}
		}
		return minvertex;
	}

	/**
	 * Returns the length of a shortest path between the source vertex and vertex v.
	 *
	 * @param v the destination vertex.
	 * @return the length of a shortest path between the source vertex and the
	 *         vertex v. Returns <code>Double.POSITIVE_INFINITY</code> if there is
	 *         no path.
	 * @throws IllegalArgumentException if v is out of bounds.
	 */
	public double distTo(int v) {
		validateVertex(v);
		return distTo[v];
	}

	/**
	 * Checks if there is a path from the source vertex to the given vertex v.
	 *
	 * @param v the destination vertex.
	 * @return <code>true</code> if there is such path, <code>false</code>
	 *         otherwise.
	 * @throws IllegalArgumentException if v is out of bounds.
	 */
	public boolean hasPathTo(int v) {
		validateVertex(v);
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/**
	 * Returns a shortest path between the source vertex and vertex v as an
	 * iterable.
	 *
	 * @param v the destination vertex
	 * @return a shortest iterable path between the source vertex and vertex v.
	 *         Returns <code>null</code> if there is no path.
	 * @throws IllegalArgumentException if v is out of bounds.
	 */
	public Iterable<DirectedEdge> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		int edge = v;
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[edge]) {
			path.push(e);
			edge = e.from();
		}
		return path;
	}

	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("The given vertex is not in valid range!");
	}

	/**
	 * Simple unit test that searches for the shortest weighted path in a graph
	 * using Dijsktra's algorithm for finding shortest undirected weighted paths.
	 *
	 * @param args Not used here.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(5);
//		DirectedEdge[] edge = { new DirectedEdge(0, 1, 4), new DirectedEdge(0, 3, 1), new DirectedEdge(3, 4, 1),
//				new DirectedEdge(4, 2, 5), new DirectedEdge(3, 1, 2), new DirectedEdge(1, 4, 2),
//				new DirectedEdge(1, 2, 5),new DirectedEdge(1,0, 4), new DirectedEdge(3,0, 1), new DirectedEdge( 4,3, 1),
//				new DirectedEdge( 2,4, 5), new DirectedEdge( 1,3, 2), new DirectedEdge( 4,1, 2),
//				new DirectedEdge( 2,1, 5),
//				};
//		for (DirectedEdge e : edge)
//			graph.addEdge(e);
//		
//		
//		System.out.println(graph);
//		DijkstraSP sp = new DijkstraSP(graph, 0);
//		System.out.println("\n"+sp.pathTo(2));

		Queue<Integer> storage = new Queue<Integer>();

		Scanner user = new Scanner(System.in);
		System.out.println("From vertex A: ");
		int A = user.nextInt();

		System.out.println("To vertex B:");
		int B = user.nextInt();

		System.out.println("Via vertex C:");
		int C = user.nextInt();

		System.out.println("done asking");
		 Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Labpm4\\src\\NYC.txt"));
		//Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Labpm4\\src\\NYC_TEST.txt"));
		File file = new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Labpm4\\src\\NYC.txt");
		 EdgeWeightedDigraph G = new EdgeWeightedDigraph(file);
		System.out.println("done scanner");
//		while (scanner.hasNextInt()) {
//			storage.enqueue(scanner.nextInt());
//		}
//		System.out.println("done queueing sysout ");
//		while (scanner.hasNext() == true) {
//			DirectedEdge edge = new DirectedEdge(storage.dequeue(), storage.dequeue(), storage.dequeue());
//			G.addEdge(edge);
//		}
//		
		
		System.out.println(G);
		
		System.out.println("done init");
		long start = System.currentTimeMillis();
		DijkstraSP D1 = new DijkstraSP(G, A);
		long mid = System.currentTimeMillis();
		System.out.println("Done first part algo "+ (mid - start));
		DijkstraSP D2 = new DijkstraSP(G, C);
		// System.out.println(G);
		long end = System.currentTimeMillis();
		System.out.println("Done second part algo "+ (end-mid));
		System.out.println("Time for the total algo " + (end - start));

		if (D1.hasPathTo(C) && D2.hasPathTo(B)) {
			System.out.print(A + " to " + B + " via " + C + " (" + (D1.distTo(C) + D2.distTo(B)) + ")" + " : \n");
			System.out.println(D1.pathTo(C) + "\n" + D2.pathTo(B));
		} else {
			System.out.println("There is no path from " + A + " to " + B + " via "+C+".");
	}
	}
}
