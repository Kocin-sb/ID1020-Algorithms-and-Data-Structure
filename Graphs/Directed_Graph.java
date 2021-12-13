package assignments;

import java.util.*;

import assignments.BinarySearch.BinarySearchST;

public class Directed_Graph {
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
	public Directed_Graph(int V) {
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
	public Directed_Graph(In in) {
		if (in == null)
			throw new IllegalArgumentException("argument is null");
		try {
			this.V = in.readInt();
			if (V < 0)
				throw new IllegalArgumentException("number of vertices in a Directed_Graph must be nonnegative");
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
			int E = in.readInt();
			if (E < 0)
				throw new IllegalArgumentException("number of edges in a Directed_Graph must be nonnegative");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				add_diredg(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Directed_Graph constructor", e);
		}
	}

	/**
	 * Initializes a new graph that is a deep copy of {@code G}.
	 *
	 * @param G the graph to copy
	 * @throws IllegalArgumentException if {@code G} is {@code null}
	 */
	@SuppressWarnings("unchecked")
	public Directed_Graph(Directed_Graph G) {
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
	public void add_diredg(int v, int w) {
		if (v < 0 || v >= V || w < 0 || w >= V)
			throw new IllegalArgumentException("vertex is not between 0 and " + (V - 1));
		E++;
		adj[v].add(w);
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
	Queue<Integer> DFS_helper(int v, boolean visited[], Queue<Integer> ret) {
		// current node is visited
		visited[v] = true;
		ret.enqueue(v);

		// process all adjacent vertices
		Iterator<Integer> i = adj[v].iterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFS_helper(n, visited, ret);
		}
		return ret;
	}

	Queue<Integer> DFS(int v) {
		// initially none of the vertices are visited
		boolean visited[] = new boolean[V];
		Queue<Integer> ret = new Queue<Integer>();

		// call recursive DFS_helper function for DFS technique
		return DFS_helper(v, visited, ret);
	}

	// BFS traversal from the root_node
	Queue<Integer> BFS(int root_node) {
		// initially all vertices are not visited
		boolean visited[] = new boolean[V];
		Queue<Integer> ret = new Queue<Integer>();
		// BFS queue
		Queue<Integer> queue = new Queue<Integer>();

		// current node = visited, insert into queue
		visited[root_node] = true;
		queue.enqueue(root_node);

		while (queue.size() != 0) {
			// deque an entry from queue and process it
			root_node = queue.dequeue();
			ret.enqueue(root_node);

			// get all adjacent nodes of current node and process
			Iterator<Integer> i = adj[root_node].iterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.enqueue(n);
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		{
//			// create a graph object and add edges to it
//			Directed_Graph g = new Directed_Graph(5);
//			g.add_diredg(0, 1);
//			g.add_diredg(0, 2);
//			g.add_diredg(0, 3);
//			g.add_diredg(1, 2);
//			g.add_diredg(4, 2);
//			System.out.println(g);
//			// print the DFS Traversal sequence
//			System.out.println("Depth First Traversal for given graph" + "(with 0 as starting vertex)");
//			g.DFS(0);
//			  //print BFS sequence
//	        System.out.println("\nBreadth-first traversal of graph with 0 as starting vertex:"); 
//	        g.BFS(4); 
			
			
			BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(1000);
			BinarySearchST<Integer, String> st2 = new BinarySearchST<Integer, String>(1000);
			int i = 0;
			int j = 0;
			Queue<String> queue = new Queue<String>();
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			while (sc.hasNext()) {
				String word = sc.next();
				queue.enqueue(word);
				if (!st.contains(word)) {
					st.put(word, i++);
					st2.put(j++, word);
				}
			}
			Directed_Graph G = new Directed_Graph(st.size());
			// Prints the elemtents in symbol table
			for (String s : st.keys())
				StdOut.print(s + " " + st.get(s) + ", ");
			for (int s : st2.keys())
				StdOut.print(s + " " + st2.get(s) + ", ");
			
			
			System.out.println("\nThe elements in queue - " + queue);
			while (queue.size() > 0)
				G.add_diredg(st.get(queue.dequeue()), st.get(queue.dequeue()));
			// Prints the Graph
			System.out.println(G);
			System.out.println("Depth First Traversal for given graph" + "(with 0 as starting vertex)");
			Queue<Integer> DFS = G.DFS(0);
			System.out.println(DFS);
			while (!DFS.isEmpty())
				System.out.print(st2.get(DFS.dequeue()) + " ");
			// print BFS sequence
			System.out.println("\nBreadth-first traversal of graph with 0 as starting vertex:");
			Queue<Integer> BFS = G.BFS(0);
			System.out.println(BFS);
			while (!BFS.isEmpty())
				System.out.print(st2.get(BFS.dequeue()) + " ");
		}
	}
}
