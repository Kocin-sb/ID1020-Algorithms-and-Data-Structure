package assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Assignment6 {
	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		// scanarray(array,length);
		random(array, length);
		int[] array1 = Arrays.copyOf(array, length);

		long startime = System.nanoTime();
		insertionsort(array);
		long endtime = System.nanoTime();
		System.out.println("Time taken for the insertionsort : " + (-startime + endtime));
		System.out.println(Arrays.toString(array));

		startime = System.nanoTime();
		int[] finisharr = mergesort(array1);
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


	public static int[] mergesort(int[] arr) {
		if (arr.length < 2)
			return arr;

		int mid = arr.length / 2;
		int cutoff = 3; // cutoff point.

		int[] leftarr = new int[mid];
		int[] rightarr = new int[arr.length - mid];

		for (int i = 0; i < mid; i++)
			leftarr[i] = arr[i];

		for (int j = 0; j < rightarr.length; j++)
			rightarr[j] = arr[mid + j];

		if (rightarr.length <= cutoff || leftarr.length <= cutoff) { // use cutoff when mergesort is less efficient.
			insertionsort(rightarr);
			insertionsort(leftarr);
			System.out
					.println(" This statement is printed when the rightarr and left_Array is equal to my CutOfPoint ");
		}

		else {
			leftarr = mergesort(leftarr);
			rightarr = mergesort(rightarr);
			System.out.println(
					" This statement is printed when the rightarr and left_Array is not equal to my CutOfPoint ");
		}

		int[] fin_arr;
		fin_arr = sort(leftarr, rightarr);
		return fin_arr;

	}

	private static int[] sort(int[] leftarr, int[] rightarr) {

		int[] new_array = new int[leftarr.length + rightarr.length];

		// New pointers declared for all the arrays.
		int leftPointer = 0;
		int rightPointer = 0;
		int resultPointer = 0;

		while (leftPointer < leftarr.length || rightPointer < rightarr.length) {
			// while loop will ensure we have a solution combined^
			if (leftPointer < leftarr.length && rightPointer < rightarr.length) {

				if (leftarr[leftPointer] < rightarr[rightPointer])
					new_array[resultPointer++] = leftarr[leftPointer++];

				else
					new_array[resultPointer++] = rightarr[rightPointer++];
			}

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
