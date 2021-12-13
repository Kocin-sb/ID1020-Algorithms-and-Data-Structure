package assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import assignments.Assignment1_2_3; 
public class Assingment9 {

	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		// scanarray(array,length);
		random(array, length);
		int[] array1 = Arrays.copyOf(array, array.length);
		int[] array2 = Arrays.copyOf(array, array.length);
		int[] array3 = Arrays.copyOf(array, array.length);
		
	//	System.out.println(Arrays.toString(array));
		var startime = System.nanoTime();
		quicksort_first(array, 0, array.length - 1);
		var endtime = System.nanoTime();
		System.out.println("Time taken for the quicksort_first : " + (-startime + endtime));
	//	System.out.println(Arrays.toString(array));
		
		startime = System.nanoTime();
		quicksort_mid(array1, 0, array1.length - 1);
		endtime = System.nanoTime();
		System.out.println("Time taken for the quicksort_mid   : " + (-startime + endtime));
	//	System.out.println(Arrays.toString(array1));
		
		startime = System.nanoTime();
		int [] finisharr = mergesort(array2);
		endtime = System.nanoTime();
		System.out.println("Time taken for the mergesort       : " + (-startime + endtime));
	//	System.out.println(Arrays.toString(finisharr));
		
		startime = System.nanoTime();
		insertionsort(array3);
		endtime = System.nanoTime();
		System.out.println("Time taken for the insertionsort   : " + (-startime + endtime));
	//	System.out.println(Arrays.toString(array3));
		
	}
	
	
	/**
	 * For the given input size this function creates random integer values into the array.
	 * @param array - array for which random values are assigned.
	 * @param length - length of array
	 */
	private static void random(int[] array, int length) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int c = 0; c < length; c++)
			list.add(c);

		Collections.shuffle(list);
		for (int c = 0; c < length; c++)
			array[c] = list.get(c);

	}
	
	
	/**
	 * Gets input from the user to input into the array.
	 * @param array
	 * @param length
	 * @return
	 */
	private static int scanarray(int[] array, int length) {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the elements in the array thats to be sorted : ");
		for (int i = 0; i < length; i++)
			array[i] = scan.nextInt();
		return 0;
	}

	
	/**
	 * 
	 * 
	 * Pick an element, called a pivot, from the list.
	 * "Partition"-->Reorder the list so that all elements which are less than the pivot come before the pivot 
	 * and so that all elements greater than the pivot come after it (equal values can go either way). 
	 * After this partitioning, the pivot is in its final position. This is called the partition operation.
	 *	Recursively sort the sub-list of lesser elements and the sub-list of greater elements.
	 * @param arr is the array being sorted
	 * @param low the index of the lowest 
	 * @param high
	 */
	public static void quicksort_first(int[] arr, int low, int high) {
		if (low >= high) return;
		
		int index = partition_first(arr, low, high);
	//	System.out.println(Arrays.toString(arr));
		quicksort_first(arr, low, index - 1);
		quicksort_first(arr, index + 1, high);
	}

	public static int partition_first(int[] arr, int lo, int hi) {
		int pivot = arr[lo];
		int j = lo+1;

		for (int i = lo+1; i <= hi; i++) {
			if (arr[i] < pivot) {
				swap(arr,j,i);
				j++;
			}
		}
		arr[lo]= arr[j-1];
		arr[j-1]= pivot;
		return j-1 ;
	}
	  public static final void swap( int [ ] a, int index1, int index2 ) {
	        int tmp = a[ index1 ];
	        a[ index1 ] = a[ index2 ];
	        a[ index2 ] = tmp;
	    }

		public static void quicksort_mid(int[] arr, int low, int high) {
			if (low >= high) return;
			
			int index = partition_first(arr, low, high);
		//	System.out.println(Arrays.toString(arr));
			quicksort_first(arr, low, index - 1);
			quicksort_first(arr, index + 1, high);
		}
		
		public static int partition_mid(int[] arr, int lo, int hi) {
			/**
			 * For the medianof three partitioning,the pivot item is selected as the 
			 * median between the first element, the last element, and the middle element 
			 * (decided using integer division of n/2).
			 * In the cases of already sorted lists this should take the middle element as
			 *  the pivot thereby reducing the inefficency found in normal quicksort_first.
			 */
			int mid = (hi-lo)/2;
			int [] median = {arr[lo],arr[mid],arr[lo]};
			insertionsort(median);
			arr[lo]=median[1];  // THE MEIAN VALUE HAS TO BE THE PIVOT
			arr[mid]=median[0];
			arr[hi]=median[2];
			
			
			int pivot = arr[lo];
			int j = lo+1;

			for (int i = lo+1; i <= hi; i++) {
				if (arr[i] < pivot) {
					swap(arr,j,i);
					j++;
				}
			}
			arr[lo]= arr[j-1];
			arr[j-1]= pivot;
			return j-1 ;
		}
	// ***********************************MERGESORT*************************//
		/**
	 * Function is divided into 2 parts : mergesort and sort mergesort : seperates
	 * the array into individual parts in recursive and calls the sort fucntion.
	 * the seperation starts from the left end where each array is stored in stack
	 * and it is repeated until first set of lefts and rights are seperated and the to sort
	 * and return with sorted first set.
	 * recursive with more other elements.
	 * 
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] mergesort(int[] arr) {
		if (arr.length < 2)
			return arr;

		int mid = arr.length / 2;

		// Declaring the new left side and right side arrays.
		int[] leftarr = new int[mid];
		int[] rightarr = new int[arr.length - mid];

		for (int i = 0; i < mid; i++)
			leftarr[i] = arr[i];

		for (int j = 0; j < rightarr.length; j++)
			rightarr[j] = arr[mid + j];

		// Recursive call for left and right arrays.
		leftarr = mergesort(leftarr);
		rightarr = mergesort(rightarr);

		int[] fin_arr;
		fin_arr = sort(leftarr, rightarr);
		return fin_arr;

	}
	
	
	/**
	 * Sort: checks the elements from the left and right array and put minimum from 
	 * both into the new array. the lowest gets the first spot. and counters to check 
	 * from which elemnt the programm should check from the array to avoid ineffciency
	 * 
	 * @param leftarr
	 * @param rightarr
	 * @return a new array with sorted elements.
	 */
	private static int[] sort(int[] leftarr, int[] rightarr) {

		int[] new_array = new int[leftarr.length + rightarr.length];

		// New pointers declared to indicate the end of array.
		int leftPointer = 0;
		int rightPointer = 0;
		int resultPointer = 0;

		while (leftPointer < leftarr.length || rightPointer < rightarr.length) {
			// Checking if the left and right arrays have elements in them
			if (leftPointer < leftarr.length && rightPointer < rightarr.length) {
				// comparing elements from different arrays to put in new array.
				if (leftarr[leftPointer] < rightarr[rightPointer])
					new_array[resultPointer++] = leftarr[leftPointer++];
				else
					new_array[resultPointer++] = rightarr[rightPointer++];
			}
			// to fill in the new array if one of the left or right array is empty
			else if (leftPointer < leftarr.length)
				new_array[resultPointer++] = leftarr[leftPointer++];

			else if (rightPointer < rightarr.length)
				new_array[resultPointer++] = rightarr[rightPointer++];
		}

		return new_array;
	}

	
	public static int[] insertionsort(int[] arr) {
		int rep;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					rep = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = rep;
				}
			}
		}
		return arr;
	}

}