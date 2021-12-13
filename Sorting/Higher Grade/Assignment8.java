package assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Assignment8 {
	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		// scanarray(array,length);
		random(array, length);
		int[] array1 = Arrays.copyOf(array, array.length);
		
		System.out.println(Arrays.toString(array));
		var startime = System.nanoTime();
		quicksort_first(array, 0, array.length - 1);
		var endtime = System.nanoTime();
		System.out.println("Time taken for the quicksort : " + (-startime + endtime));
		System.out.println(Arrays.toString(array));
		
		startime = System.nanoTime();
		int [] finisharr = mergesort(array1);
		endtime = System.nanoTime();
		System.out.println("Time taken for the mergesort : " + (-startime + endtime));
		System.out.println(Arrays.toString(finisharr));
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
	
	
	
	public static void quicksort_first(int[] arr, int low, int high) {
		if (low >= high) return;
		
		int index = partition_first(arr, low, high); //finding the pivot position in sorted array
		System.out.println(Arrays.toString(arr));
		quicksort_first(arr, low, index - 1);//recursive calling before pivot sub array
		quicksort_first(arr, index + 1, high);//recursive calling after pivot sub array
	}
	
	public static int partition_first(int[] arr, int lo, int hi) {
		int pivot = arr[lo];
		int j = lo+1;

        //swap values if a[j]<=a[r](i.e. pivot)
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

	// ***********************************MERGESORT*************************//
	public static int[] mergesort(int[] arr) {
		if (arr.length < 2) {
			return arr;
		}

		int mid = arr.length / 2;

		
		int[] leftarr = new int[mid];
		int[] rightarr = new int[arr.length - mid];

	
		for (int i = 0; i < mid; i++) 
			leftarr[i] = arr[i]; 

		for (int j = 0; j < rightarr.length; j++) 
			rightarr[j] = arr[mid + j]; 	

	
		leftarr = mergesort(leftarr);
		rightarr = mergesort(rightarr);

		int[] fin_arr;
		fin_arr = sort(leftarr, rightarr);
		return fin_arr;

	}

	private static int[] sort(int[] leftarr, int[] rightarr) {

		int[] new_array = new int[leftarr.length + rightarr.length];

		int leftPointer = 0;
		int rightPointer = 0;
		int resultPointer = 0;

		while (leftPointer < leftarr.length || rightPointer < rightarr.length) {
		
			if (leftPointer < leftarr.length && rightPointer < rightarr.length) {

		
				if (leftarr[leftPointer] < rightarr[rightPointer])
					new_array[resultPointer++] = leftarr[leftPointer++];

				else
					new_array[resultPointer++] = rightarr[rightPointer++];

			}

			else if (leftPointer < leftarr.length) {

				new_array[resultPointer++] = leftarr[leftPointer++];

			}

		
			else if (rightPointer < rightarr.length) {

				new_array[resultPointer++] = rightarr[rightPointer++];

			}

		}

		return new_array;
	}

}