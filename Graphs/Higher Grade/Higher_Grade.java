package assignments;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Higher_Grade {
	public static void main(String[] args) throws IOException {
		Queue<Integer> storage = new Queue<Integer>();
		
		Scanner user = new Scanner(System.in);
		System.out.println("From vertex A: ");
		int A = user.nextInt();

		System.out.println("To vertex B:");
		int B = user.nextInt();

		System.out.println("Via vertex C:");
		int C = user.nextInt();

		System.out.println("done asking");
	//	Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Labpm4\\src\\NYC.txt"));
		Scanner scanner = new Scanner(new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Labpm4\\src\\NYC_TEST.txt"));
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(264346);
		System.out.println("done scanner");
		while(scanner.hasNextInt()) {
			storage.enqueue(scanner.nextInt());
		}
		System.out.println("done queueing sysout "+storage);
		while (scanner.hasNext() == true) {
			DirectedEdge edge = new DirectedEdge(storage.dequeue(), storage.dequeue(), storage.dequeue());
			G.addEdge(edge);
		}
		System.out.println("done init");
		long start = System.currentTimeMillis();
		DijkstraSP D1 = new DijkstraSP(G, A);
		DijkstraSP D2 = new DijkstraSP(G, C);
		//System.out.println(G);
		long end = System.currentTimeMillis();
		
		System.out.println("Time for the algo "+ (end-start));
		
		if (D1.hasPathTo(C) && D2.hasPathTo(B)) {
			System.out.print(A + " to " + B + " via " + C + " (" + (D1.distTo(C) + D2.distTo(B)) + ")" + " : \n");
			System.out.println(D1.pathTo(C) + "\n" + D2.pathTo(B));
		} else {
			System.out.println("There is no path from " + A + " to " + B + " via C.");
		}
	}
}
