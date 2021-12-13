package assign;

import assign.BinarySearch.BinarySearchST;

public class Extra1 {
	public static void main(String[] args) {
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
				"brood. the the the ";
		String [] arr = str.split(" ");
    	BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(10000);
    	for(int i = 0;i<arr.length;i++) {
    		st.put(arr[i],i);
    	}
    	
    	BinarySearchST< Integer,String> st1= new BinarySearchST< Integer,String>(10000);
		for(int i = 0;i<arr.length;i++) {
    		st1.put(i,frqcount(st,i));
    	}
	 	for(Integer s: st1.keys())
    		StdOut.println(s+" "+st1.get(s));
    		
    	
	}
	public static String frqcount(BinarySearchST<String, Integer> st,int i) {
		String max = "";
		st.put(max, i);
		for (String word : st.keys())
			if (st.get(word) > st.get(max))
				max = word;
		return max;
	}
}
