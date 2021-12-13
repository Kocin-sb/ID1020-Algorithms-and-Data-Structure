
/******************************************************************************
 *  Compilation:  java Queue1.java
 *  Execution:    java Queue1 < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic Queue1, implemented using a linked list.
 *
 *  % java Queue1 < tobe.txt 
 *  to be or not to be (2 left on Queue1)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code Queue1} class represents a first-in-first-out (FIFO) Queue1 of
 * generic items. It supports the usual <em>enQueue1</em> and <em>deQueue1</em>
 * operations, along with methods for peeking at the first item, testing if the
 * Queue1 is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes. See {@link LinkedQueue1} for the version from the textbook
 * that uses a non-static nested class. See {@link ResizingArrayQueue1} for a
 * version that uses a resizing array. The <em>enQueue1</em>, <em>deQueue1</em>,
 * <em>peek</em>, <em>size</em>, and <em>is-empty</em> operations all take
 * constant time in the worst case.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 *
 * @param <Item> the generic type of an item in this Queue1
 */
public class Queue1<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning of Queue1
	private Node<Item> last; // end of Queue1
	private int n; // number of elements on Queue1

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
	}

	/**
	 * Initializes an empty Queue1.
	 */
	public Queue1() {
		first = null;
		last = null;
		n = 0;
	}

	/**
	 * Returns true if this Queue1 is empty.
	 *
	 * @return {@code true} if this Queue1 is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in this Queue1.
	 *
	 * @return the number of items in this Queue1
	 */
	public int size() {
		return n;
	}

	/**
	 * Returns the item least recently added to this Queue1.
	 *
	 * @return the item least recently added to this Queue1
	 * @throws NoSuchElementException if this Queue1 is empty
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Queue1 underflow");
		return first.item;
	}

	/**
	 * Adds the item to this Queue1.
	 *
	 * @param item the item to add
	 */
	public void enQueue1(Item item) {//store last in a new node ref and create a new last with item given
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		if (isEmpty()) { // If queue empty then f = l and theier previous and next are itself
			first = last;
			first.prev = last;
			first.next = last;
		} else {// if not empty then add the lassst to the end and connect the paths by connect the next and prev
			oldlast.next = last;
			last.prev = oldlast;
			last.next = first;
			first.prev = last;
		}
		n++;
	}

	/**
	 * Removes and returns the item on this Queue1 that was least recently added.
	 *
	 * @return the item on this Queue1 that was least recently added
	 * @throws NoSuchElementException if this Queue1 is empty
	 */
	public Item deQueue1() {
		if (isEmpty())
			throw new NoSuchElementException("Queue1 underflow"); //check if queue is empty
		Item item = first.item;
		if (first == last) //check if it is the last elemnt in queue
			first = null;
		else {			// if not then change first to first.next and conct the prev and next with the last to finish the circle
			first = first.next;
			last.next = first;
			first.prev = last;
			n--;
			if (isEmpty())
				last = null; // to avoid loitering
		}
		return item;
	}

	/**
	 * Prints all the elements in the queue stating from last.next till all the
	 * elements are printed
	 */
	public void printall() {
		if (first == null)
			StdOut.println("empty");
		else {
			int m = n;
			Node<Item> curr = last;
			while (m != 0) {
				StdOut.print(curr.next.item + "  ");
				curr = curr.next;
				m--;
			}
			StdOut.println();
		}// a method made before i knew about tostring() but it works just by print for the size of queue to 0.
	}

	/**
	 * Returns a string representation of this Queue1.
	 *
	 * @return the sequence of items in FIFO order, separated by spaces
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

	/**
	 * Returns an iterator that iterates over the items in this Queue1 in FIFO
	 * order.
	 *
	 * @return an iterator that iterates over the items in this Queue1 in FIFO order
	 */
	public Iterator<Item> iterator() {
		return new LinkedIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<Item> {
		private Node<Item> current;

		public LinkedIterator(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * Unit tests the {@code Queue1} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		Queue1<Integer> que = new Queue1<Integer>();
		que.enQueue1(5);
		que.printall();
		que.enQueue1(10);
		que.printall();
		que.enQueue1(8);
		que.printall();
		que.enQueue1(23);
		que.printall();
		que.deQueue1();
		que.printall();
		que.deQueue1();
		que.printall();

		que.deQueue1();
		que.printall();
		que.deQueue1();
		que.printall();
		que.deQueue1();
		que.printall();
		que.deQueue1();
		que.printall();
		que.deQueue1();
		que.printall();

	}
}
