package assignments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Assignment1_2_3 {
	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		 scanarray(array,length);  // Test with the input [1, 2, 4, 3, 5, 0] 
		//random(array, length);      // extreme value test with many inputs and less inputs.
		inversion(array);
		insertionsort(array);
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
	 * Insertion sort : it chooses the first element of the array at the beginning and checks if all 
	 * the elements before it are sorted and puts the chosen element in the right order
	 * with respective to the other elements before it.
	 * 
	 * Also does the count for swaps during the sort.
	 * @param arr
	 * @return
	 */
	public static void insertionsort(int[] arr) { //---> Time complexity is O(N^2) becuase the loop runs n^2 times.
		System.out.println("\nInitial array to begin with " + Arrays.toString(arr));
		int rep, swap = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					rep = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = rep;
					swap++;
				}
			}
			System.out.println("Total number of swaps: " + (swap) + " and the Array is :" + Arrays.toString(arr));
		}
	}

	
	
	/**
	 * The inversion function does the similar sorting as insertion but the difference is 
	 * this function starts with the first element and moves it to the end as in decending order 
	 * with respect to last being the highest number.
	 * @param array - input array to be inversion
	 */
	public static void inversion(int[] array) {
		System.out.println("\nInitial array to begin with " + Arrays.toString(array));
		int count = 0;
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] > array[j]) {
					System.out.println("(" + i + ",\"" + array[i] + "\")(" + j + ",\"" + array[j] + "\")");
					count++;
				}
			}
		}
		System.out.println(" \n Number of inversions: " + count);
	}
}
