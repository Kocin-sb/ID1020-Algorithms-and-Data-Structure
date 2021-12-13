package assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Assignment5 {
	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		// scanarray(array,length);
		random(array, length);

		int[] array1 = Arrays.copyOf(array, length);

		// Time taken for the insertion sort
		long startime = System.nanoTime();
		insertionsort(array);
		long endtime = System.nanoTime();
		System.out.println("Time taken for the insertionsort : " + (-startime + endtime));
		System.out.println(Arrays.toString(array));
		
		// Time taken for mergesort
		startime = System.nanoTime();
		int[] finisharr = mergesort(array1);
		endtime = System.nanoTime();
		System.out.println("Time taken for the mergesort : " + (-startime + endtime));
		System.out.println(Arrays.toString(finisharr));

	}

	/**
	 * For the given input size this function creates random integer values into the
	 * array.
	 * 
	 * @param array  - array for which random values are assigned.
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
	 * 
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

	
	/**
	 * Insertion sort : it chooses the first element of the array at the beginning and checks if all 
	 * the elements before it are sorted and puts the chosen element in the right order
	 * with respective to the other elements before it.
	 * 
	 * Also does the count for swaps during the sort.
	 * @param arr
	 * @return
	 */
	public static int[] insertionsort(int[] arr) {
		int rep, swap = 0;
		System.out.println("\nInitial array to begin with " + Arrays.toString(arr));
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					rep = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = rep;
					swap++;
				}
			}
		}
		return arr;
	}
}
