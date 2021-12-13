package assignments;

import java.util.Arrays;
import java.util.Scanner;

public class Assignment7 {
	public static void main(String arg[]) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of elements to be sorted : ");
		int length = scan.nextInt();
		int[] array = new int[length];
		System.out.print("Enter the elements in the array thats to be sorted : ");
		for (int i = 0; i < length; i++) {
			array[i] = scan.nextInt();
		}
		insertionsort(array);
	}
	
	public static int[] insertionsort(int[] arr) {
		int rep, swap = 0;
		System.out.println("\nInitial array to begin with " + Arrays.toString(arr));
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] > arr[j - 1]) {  // changed the "<" symbol to ">" from assignment 1.
					rep = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = rep;
					swap++;
				}
			}
			System.out.println("Number of swaps: " + (swap) + " and the Array is :" + Arrays.toString(arr));
		}
		return arr;
	}
}
