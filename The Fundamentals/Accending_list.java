
/******************************************************************************
 *  Compilation:  java Kqueue.java
 *  Execution:    java queue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/13stacks/tobe.txt  
 *
 *  A generic queue, implemented using a linked list.
 *
 *  % java queue < tobe.txt 
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code queue} class represents a first-in-first-out (FIFO) queue of
 * generic items. It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item, testing if the
 * queue is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes. See {@link LinkedKqueue} for the version from the textbook
 * that uses a non-static nested class. See {@link ResizingArrayKqueue} for a
 * version that uses a resizing array. The <em>enqueue</em>, <em>dequeue</em>,
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
 * @param <Item> the generic type of an item in this queue
 */
public class Accending_list<Item> implements Iterable<Item> {
	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private Node<Item> curr;
	private int n; // number of elements on queue

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private int index; // index number of the node in queue.
		private Node<Item> next;

	}

	/**
	 * Initializes an empty queue.
	 */
	public Accending_list() {
		first = null;
		last = null;
		n = 0;
	}

	/**
	 * Returns true if this queue is empty.
	 *
	 * @return {@code true} if this queue is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in this queue.
	 *
	 * @return the number of items in this queue
	 */
	public int size() {
		return n;
	}

	/**
	 * Returns the item least recently added to this queue.
	 *
	 * @return the item least recently added to this queue
	 * @throws NoSuchElementException if this queue is empty
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Kqueue underflow");
		return first.item;
	}

	/**
	 * Adds the item to this queue.
	 *
	 * @param item the item to add
	 */
	public void enqueue(Item item) {
//        Node<Item> oldlast = last;
//        last = new Node<Item>();
//        last.item = item;
//        last.next = null;
//        if (isEmpty()) first = last;
//        else           oldlast.next = last;
//        last.index = ++n;
//        StdOut.println(this);

		Node<Item> puds = new Node<Item>();
		puds.item = item;
		if (isEmpty()) {
			first = last = puds;
			
		} else if (((int) first.item >= (int) item)) { 
				puds.next = first;
				first = puds;
		} else if (((int) first.item < (int) item) && n==1) { 
			first.next = puds;
		}
			else {
				curr = first;
				while (curr.next != null) {
					if ((int) item < (int) curr.next.item || curr.next == null) {
						puds.next = curr.next;
						curr.next = puds;
						break;
					}
					curr = curr.next;
					if (curr.next == null && (int) item > (int) curr.item  )
						curr.next = puds;
				}
			}
		n++;
		StdOut.println(this);
	}

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 *
	 * @return the item on this queue that was least recently added
	 * @throws NoSuchElementException if this queue is empty
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Accending_list underflow");
		Item item = first.item;
		first = first.next;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		StdOut.println(this);
		return item;
	}

	private void reassemble() {
		Node<Item> current = first;
		for (int i = 1; i <= n; i++) {
			current.index = i;
			current = current.next;
		}
	}

	/**
	 * Returns a string representation of this queue.
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
	 * Returns an iterator that iterates over the items in this queue in FIFO order.
	 *
	 * @return an iterator that iterates over the items in this queue in FIFO order
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
	 * Unit tests the {@code queue} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		Accending_list<Integer> queue = new Accending_list<>();
		queue.enqueue(12);
		queue.enqueue(32);
		queue.enqueue(11);
		queue.enqueue(4);
		queue.enqueue(121);
		queue.enqueue(5);

		queue.dequeue();
		queue.dequeue();
	}
}

/******************************************************************************
 * Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
