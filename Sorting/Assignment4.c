#include <stdlib.h>
#include <stdio.h>

/**
 * This function searches for a positive number first..
 * once found it goes into an inner loop to find the last negative number,once found it swaps places
 * happens til the end of the loop.
 * time complexity is O(N^2) as it is very similar to insertion sort.
 * 
 **/
int orderneg(int arr[], int length)
{
    int firstpos = -1;
    int swap;
    for (int i = 0; i < length; i++)
    {
        if (firstpos < 0 && arr[i] >= 0)
        {
            firstpos = i;
            for (int k = firstpos; k < length; k++)
            {
                if (arr[k] < 0)
                {
                    swap = arr[firstpos];
                    arr[firstpos] = arr[k];
                    arr[k] = swap;
                }
            }
            firstpos = -1;
        }
        for (int j = 0; j < length; j++){
            printf("%d  ", arr[j]);
        }
        printf("...... ");
      }   
    
}

void main()
{

    printf("Enter the number of elements to be ordered with negative numbers first :");
    int length;
    scanf("%d", &length);
    int array[length];
    printf("Enter the numbers to be added to the array : ");
    for (int i = 0; i < length; i++)
    {
        scanf("%d", &array[i]);
    }
    orderneg(array, length);
}