package assign;

import assign.BinarySearch.BinarySearchST;

public class FrequencyCounter {
	public static void main(String[] args) {
		int minlen = 1; // key-length cutoff
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(100);
		while (!StdIn.isEmpty()) { // Build symbol table and count frequencies.
			String word = StdIn.readString();
			if (word.length() < minlen)
				continue; // Ignore short keys.
			if (!st.contains(word))
				st.put(word, 1);
			else
				st.put(word, st.get(word) + 1);
		}
// Find a key with the highest frequency count.
		String max = "";
		st.put(max, 0);
		for (String word : st.keys())
			if (st.get(word) > st.get(max))
				max = word;
		System.out.println(max + " " + st.get(max));
	}
}
