package assign;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Index {
	private static final int INIString_CAPACIStringY = 2;
	private Node[] keys;
	private int total_index;
	public int total_word=0;

	public class Node {
		private Comparable key;
		private List<Integer> index = new ArrayList<Integer>();;
		private int count;
		boolean collision = false;
		public Node next;

		Node(Comparable in_key, int index) {
			this.key = in_key;
			this.index.add(index);
			count = 1;
		}

		Node(Comparable in_key, int index, int count) {
			this.key = in_key;
			this.index.add(index);
			this.count = count;
		}

		public Comparable getkey() {
			return key;
		}

		public int getcount() {
			return count;
		}

		public void setcount(int x) {
			count = x;
		}

		public void addlist(int x) {
			index.add(x);
		}
	}

	public Index() {
		this(INIString_CAPACIStringY);
	}

	public Index(int capacity) {
		keys = new Node[capacity];
		total_index = 0;
	}

	private int getHashValue(String key, int i) {
		return (key.hashCode()& 0x7fffffff) % i; // the hash code gets a unique value for the string and % is the compressor .
	}

	public void add(String key) {
		int key_value = getHashValue(key, keys.length - 1);
		if (keys[key_value] == null) {
			keys[key_value] = new Node(key, total_index);
			total_word+=1;
		} else {
			keys[key_value].collision = true;
			Node head = keys[key_value];
			while (head != null) {
				if (head.getkey().compareTo(key) == 0) {
					head.addlist(total_index);
					head.count += 1;
					total_index += key.length();
					total_word+=1;
					return;
				}
				head = head.next;
			}
			head = new Node(key, total_index);
			total_word+=1;
		}
		total_index += key.length()+1;
	}

	public List getlist(String key) {
		int key_value = getHashValue(key, keys.length - 1);
		if (keys[key_value].key.compareTo(key) == 0) {
			return keys[key_value].index;

		} else {
			Node head = keys[key_value];
			while (head != null) {
				if (head.getkey().compareTo(key) == 0) {
					return head.index;
				}
				head = head.next;
			}
		}
		return null;
	}
	public int getcount(String key) {
		int key_value = getHashValue(key, keys.length - 1);
		if (keys[key_value].key.compareTo(key) == 0) {
			return keys[key_value].count;

		} else {
			Node head = keys[key_value];
			while (head != null) {
				if (head.getkey().compareTo(key) == 0) {
					return head.count;
				}
				head = head.next;
			}
		}
		return 0;
	}
	public Iterable keys() {
		Queue<Comparable> queue = new Queue<Comparable>();
		for(int i = 0;i<total_word;i++) {
			if (keys[i].collision == false) {
				queue.enqueue(keys[i].getkey());
			} else {
				Node head = keys[i];
				while (head != null) {
					queue.enqueue(head.getkey());
					head = head.next;
				}
			}
		}
		return queue;
}



	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Admin\\Desktop\\Studies\\Algorithms and data structure\\Projects\\Lab\\src\\assign\\thetext.txt"); 
		Scanner scan = new Scanner(file);
		List<Integer> countcount = new ArrayList<Integer>();
		String str = "There were a king with a large jaw and a queen with a plain face, on the " + 
				"throne of England; there were a king with a large jaw and a queen with " + 
				"a fair face, on the throne of France. In both countries it was clearer " + 
				"than crystal to the lords of the State preserves of loaves and fishes, " + 
				"that things in general were settled for ever." + 
				"It was the year of Our Lord one thousand seven hundred and seventy-five. " + 
				"Spiritual revelations were conceded to England at that favoured period, " + 
				"as at this. Mrs. Southcott had recently attained her five-and-twentieth " + 
				"blessed birthday, of whom a prophetic private in the Life Guards had " + 
				"heralded the sublime appearance by announcing that arrangements were " + 
				"made for the swallowing up of London and Westminster. Even the Cock-lane " + 
				"ghost had been laid only a round dozen of years, after rapping out its " + 
				"messages, as the spirits of this very year last past (supernaturally " + 
				"deficient in originality) rapped out theirs. Mere messages in the " + 
				"earthly order of events had lately come to the English Crown and People, " + 
				"from a congress of British subjects in America: which, strange " + 
				"to relate, have proved more important to the human race than any " + 
				"communications yet received through any of the chickens of the Cock-lane " + 
				"brood.";
		String [] arr = str.split(" ");
		long start = System.nanoTime();
		Index hash = new Index(arr.length);
		for (String sss : arr) {
			hash.add(sss);
		}
		StdOut.println(hash.total_index);
		StdOut.println(hash.getlist("the") + " ..." + hash.getcount("the")+"...."+hash.total_word);
	
	}
}
