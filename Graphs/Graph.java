package assignments;

import java.util.*;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Integer>[] adj;

	/**
	 * Initializes an empty graph with {@code V} vertices and 0 edges. param V the
	 * number of vertices
	 *
	 * @param V number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	/**
	 * Initializes a graph from the specified input stream. The format is the number
	 * of vertices <em>V</em>, followed by the number of edges <em>E</em>, followed
	 * by <em>E</em> pairs of vertices, with each entry separated by whitespace.
	 *
	 * @param in the input stream
	 * @throws IllegalArgumentException if {@code in} is {@code null}
	 * @throws IllegalArgumentException if the endpoints of any edge are not in
	 *                                  prescribed range
	 * @throws IllegalArgumentException if the number of vertices or edges is
	 *                                  negative
	 * @throws IllegalArgumentException if the input stream is in the wrong format
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in) {
		if (in == null)
			throw new IllegalArgumentException("argument is null");
		try {
			this.V = in.readInt();
			if (V < 0)
				throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if (E < 0)
				throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	/**
	 * Initializes a new graph that is a deep copy of {@code G}.
	 *
	 * @param G the graph to copy
	 * @throws IllegalArgumentException if {@code G} is {@code null}
	 */
	@SuppressWarnings("unchecked")
	public Graph(Graph G) {
		this.V = G.V();
		this.E = G.E();
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");

		// update adjacency lists
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}

		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 */
	public int E() {
		return E;
	}

	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param v one vertex in the edge
	 * @param w the other vertex in the edge
	 * @throws IllegalArgumentException unless both {@code 0 <= v < V} and
	 *                                  {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		if (v < 0 || v >= V || w < 0 || w >= V)
			throw new IllegalArgumentException("vertex is not between 0 and " + (V - 1));
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param v the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex is not between 0 and " + (V - 1));
		return adj[v].size();
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex is not between 0 and " + (V - 1));
		return adj[v];
	}

	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges
	 *         <em>E</em>, followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	// helper function for DFS technique
	void DFS_helper(int Root_Node, int End_Node, boolean visited[]) {
		// current node is visited
		visited[Root_Node] = true;
		System.out.print(Root_Node + " ");
		if(Root_Node==End_Node)return;
		// process all adjacent vertices
		Iterator<Integer> i = adj[Root_Node].iterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n]&& visited[End_Node]!=true) {
				DFS_helper(n, End_Node, visited);
			}		
		}
	}

	void DFS(int Root_Node, int End_Node) {
		// mark if the vertices have been reached or visited
		boolean visited[] = new boolean[V];
		// call recursive DFS_helper function for DFS technique
		DFS_helper(Root_Node, End_Node, visited);
		boolean found = visited[End_Node];
		if (found == true)
			System.out.println("Path Found");
		else
			System.out.println("Path Not FOund");

	}

	// BFS traversal from the v to w
	void BFS(int Root_Node, int End_Node) {
		// initially all vertices are not visited
		boolean visited[] = new boolean[V];

		Queue<Integer> queue = new Queue<Integer>();

		// current node = visited, insert into queue
		visited[Root_Node] = true;
		queue.enqueue(Root_Node);

		while (queue.size() != 0) {
			Root_Node = queue.dequeue();
			System.out.print(Root_Node + " ");
			if (Root_Node == End_Node) {
				System.out.println("!Found");
				return;
			}

			// get all adjacent nodes of current node to a queue so all the breadths are
			// searched first.
			Iterator<Integer> i = adj[Root_Node].iterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.enqueue(n);
				}
			}
		}
		System.out.println("Not found");
	}

	public static void main(String[] args) {
		{
			// create a graph object and add edges to it
			Graph g = new Graph(5);
			g.addEdge(0, 1);
			g.addEdge(0, 2);
			g.addEdge(0, 3);
			g.addEdge(1, 2);
			g.addEdge(2, 4);
			System.out.println(g);
			// print the DFS Traversal sequence
			System.out.println("Depth First Traversal for given graph" + "(with 0 as starting vertex)");
			g.DFS(0,4);
			  //print BFS sequence
	        System.out.println("\nBreadth-first traversal of graph with 0 as starting vertex:"); 
	        g.BFS(0,4); 
			Queue<Integer> queue = new Queue<Integer>();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			while (sc.hasNext()) {
				int word = sc.nextInt();
				queue.enqueue(word);
			}
			Graph G = new Graph(queue.size() / 2);
			while (queue.size() > 0)
				G.addEdge(queue.dequeue(), queue.dequeue());
			StdOut.println(G);
			// print the DFS Traversal sequence
			System.out.println("Depth First Traversal for given graph");
			G.DFS(1, 5);
			// print BFS sequence
			System.out.println("\nBreadth-first traversal of graph ");
			G.BFS(1, 5);

		}

	}
}
