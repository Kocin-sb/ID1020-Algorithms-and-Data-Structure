package assign;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import assign.BinarySearch.BinarySearchST;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;      // root of BST

    private class Node {
        private Key key;            // key
        private Value val;          // associated value
        private Node left, right;   // links to subtrees
        private int N;              // # nodes in subtree rooted here

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public BST() {
	}

	public int size() { return size(root); }

    private int size(Node x) {
        if(x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val) { root = put(root, key, val); }

    private Node put(Node x, Key key, Value val) {
        if(x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left, key, val);
        else if(cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key) { return contains(root, key); }

    private boolean contains(Node x, Key key) {
        if(x == null) return false;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return contains(x.left, key);
        else if(cmp > 0) return contains(x.right, key);
        else return true;
    }

    public Key min() { return min(root).key; }

    private Node min(Node x) {
        if(x.left == null) return x;
        return min(x.left);
    }

    public Key max() { return max(root).key; }

    private Node max(Node x) {
        if(x.right == null) return x;
        return max(x.right);
    }
    public Key select(int k) { return select(root, k).key; }

    // return Node containing key of rank k
    private Node select(Node x, int k) {
        if(x == null) return null;
        int t = size(x.left);
        if(t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k-t-1);
        else return x;
    }

    public int rank(Key key) { return rank(key, root); }

    // return number of keys less than x.key in the subtree rooted at x
    private int rank(Key key, Node x) {
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(key, x.left);
        else if(cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }

    public void deleteMin() { root = deleteMin(root); }

    private Node deleteMin(Node x) {
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) { root = delete(root, key); }

    private Node delete(Node x, Key key) {
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = delete(x.left, key);
        else if(cmp > 0) x.right = delete(x.right, key);
        else {
            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() { return keys(min(), max()); }

    private Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();
        keys(root, q, lo, hi);
        return q;
    }

    private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if(x == null) return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);

        if(cmpLo < 0) keys(x.left, q, lo, hi);
        if(cmpLo <= 0 && cmpHi >= 0 ) q.enqueue(x.key);
        if(cmpHi > 0) keys(x.right, q, lo, hi);
    }

    
    
    // main for testing, counting the first N*100 words
    public static void main(String[] args) {
    	String[] str = {"hi","there","i","am", "kocin","how","you" ,"doin"};
    	BST<String, Integer> st = new BST<String, Integer>();
    	for(int i = 0; i<str.length;i++) {
    		String key = str[i];
    		st.put(key, i);
    	}
    	for(String s: st.keys())
    		StdOut.println(s+" "+st.get(s));
    }
  	long timr = System.nanoTime();
  	st.get("the");
	long timl = System.nanoTime();
	System.out.println(timl-timr);
}
