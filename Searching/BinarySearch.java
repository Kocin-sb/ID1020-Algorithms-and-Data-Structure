package assign;

import java.io.File;
import java.io.IOException;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static assign.StdIn.*;

public class BinarySearch {
public static class BinarySearchST<Key extends Comparable<Key>, Value> {
	private static final int INIT_CAPACITY = 2;
	private Key[] keys;
    private Value[] vals;
    private int N;
    
    /**
     * Initializes an empty symbol table.
     */
    public BinarySearchST() {
        this(INIT_CAPACITY);
    }
    
    /**
     * Initializes an empty symbol table with the specified initial capacity.
     * @param capacity the maximum capacity
     */
    public BinarySearchST(int capacity) { 
        keys = (Key[]) new Comparable[capacity]; 
        vals = (Value[]) new Object[capacity]; 
    }   

    public int size() { return N; }

    public boolean isEmpty() { return N == 0; }


    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null"); 
        if (isEmpty()) return null;
        int i = rank(key); 
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    } 

    /**
     * Returns the number of keys in this symbol table strictly less than {@code key}.
     *
     * @param  key the key
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to rank() is null"); 

        int lo = 0, hi = N-1; 
        while (lo <= hi) { 
            int mid = lo + (hi - lo) / 2; 
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1; 
            else if (cmp > 0) lo = mid + 1; 
            else return mid; 
        } 
        return lo;
    } 


    public boolean contains(Key key) {
        if(isEmpty()) return false;
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return true;
        else return false;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        for(int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public void delete(Key key) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) {
            for(int j = i + 1; j < N; j++) {
                keys[j - 1] = keys[j];
                vals[j - 1] = vals[j];
            }
        }
    }
    /**
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return keys[0]; 
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if this symbol table is empty
     */
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return keys[N-1];
    }
    
    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * Returns all keys in this symbol table in the given range,
     * as an {@code Iterable}.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between {@code lo} 
     *         (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi}
     *         is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 

        Queue<Key> queue = new Queue<Key>(); 
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(keys[i]);
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue; 
    }

    // main for testing, counting the first N*100 words
    public static void main(String[] args) {
    	String text = "adfadvadd d vd af va  asdf va efv a v aefv a efb aefb a fv aef ba f ae f" ;
    	String[] str = text.split(" ");
//    	for(String s: str)
//    		System.out.print(s+"-");
    	BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(100);
    	for(int i = 0; i<str.length;i++) {
    		String key = str[i];
    		st.put(key, i);
    	}
    	long timr = System.nanoTime();
    	st.get("the");
    	long timl = System.nanoTime();
    	System.out.println(timl-timr);
//    	for(String s: st.keys())
//    		StdOut.println(s+" "+st.get(s));
//    		
    	
    	
    }
}
}