import java.util.Iterator;
import java.util.NoSuchElementException;

import java.util.Iterator;

public class Linkdlst<Item2> implements Iterable<Item2> {
	private Node<Item2> first;
	private Node<Item2> last;
	int n;

	public class Node<Item2> {
		private Item2 itm;
		private Node<Item2> next;
	}

	public Linkdlst() {
		first = null;
		last = null;
		n = 0;
	}

	/**
	 * Returns true if this linkedlist is empty.
	 *
	 * @return {@code true} if this linkedlist is empty; {@code false} otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the item least recently added to this linkedlist.
	 *
	 * @return the item least recently added to this linkedlist
	 * @throws NoSuchElementException if this linkedlist is empty
	 */
	public Item2 peek() {
		if (isEmpty())
			throw new NoSuchElementException("linkedlist underflow");
		return first.itm;
	}

	/**
	 * Returns the number of items in this linkedlist.
	 *
	 * @return the number of items in this linkedlist
	 */
	public int size() {
		return n;
	}

	public void addfirst(Item2 data) { // create a neew node and check if it empty...if not the it is the first node last node and last.next or first.next
		Node<Item2> newfirst = new Node<Item2>();
		newfirst.itm = data;
		if (isEmpty()) {
			first = newfirst;
			last = first;
			first.next = first;
		} else { // if not empty then change the ref so the next is new node is first and next of last is new node.
			Node<Item2> temp = first;
			newfirst.next = temp;
			first = newfirst;
			last.next = first;
		}
		n++;
		printall();
	}

	public void addlast(Item2 data) {
		Node<Item2> newlast = new Node<Item2>();
		newlast.itm = data;
		if (isEmpty()) {
			last = newlast;
			first = last;
			last.next = first;
		} else {
			last.next = newlast;
			last = newlast;
			last.next = first;

		}
		n++;
		printall();
	}

	public void delfirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("linkedlist underflow");
		}
		if (first == last) {
			first = null;
			last = first;
			return;
		}
		first = first.next;
		last.next = first;
		n--;
		if (isEmpty())
			last = null; // to avoid loitering
		printall();
	}

	public void dellast() { // print cmd run in cmdprompt.
		if (isEmpty())
			throw new NoSuchElementException("linkedlist underflow");
		Node<Item2> current = first;
		if (current == last)
			first = null;
		else {
			while (current.next != last) {
				current = current.next;
			}
			current.next = first;
			last = current;
			n--;
		}
		printall();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item2 item : this) {
			s.append(item);
			s.append(' ');
		}
		return s.toString();
	}

	public Iterator<Item2> iterator() {
		return new LinkedIterator(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class LinkedIterator implements Iterator<Item2> {
		private Node<Item2> current;

		public LinkedIterator(Node<Item2> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item2 next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item2 itm = current.itm;
			current = current.next;
			return itm;
		}
	}

	public void printall() {
		if (first == null)
			StdOut.println("empty");
		else {
			int m = n;
			Node<Item2> curr = last;
			while (m != 0) {
				StdOut.print(curr.next.itm + "  ");
				curr = curr.next;
				m--;
			}
			StdOut.println();
		}
	}

	public static void main(String arg[]) {
		Linkdlst<Integer> linkd = new Linkdlst<Integer>();
		
		linkd.addfirst(4);

		linkd.addfirst(5);
		
		linkd.addlast(1);

		linkd.addlast(2);

		linkd.addfirst(3);

		linkd.addfirst(4);

		linkd.addfirst(5);

		linkd.addfirst(6);

		linkd.addlast(8);

		linkd.addlast(9);

		linkd.dellast();

		linkd.dellast();

		linkd.dellast();

		linkd.dellast();

		linkd.dellast();

		linkd.dellast();

		linkd.dellast();

		linkd.delfirst();

		linkd.delfirst();

	}
}
