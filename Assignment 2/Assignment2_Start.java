import java.util.Arrays;

/* Comp10205 - Sorting Assignment

 Some of the sort code from courseWare textbook - Copyright, All rights reserved.
 Additional code added by C. Mark Yendt in May 2014 August 2019
 ADD Your Authorship and answers to Questions here :


 _________________________________________________

*/

public class Assignment2_Start {

    static int sortaCompares = 0;  // Global for counting if recursion is not implemented

    /**
     * The swap method swaps the contents of two elements in an int array.
     *
     * @param array containing the two elements.
     * @param a     The subscript of the first element.
     * @param b     The subscript of the second element.
     */
    private static void swap(int[] array, int a, int b) {
        int temp;

        temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * The ______________ sort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static void aSort(int array[]) {
        sortaCompares = 0;
        doASort(array, 0, array.length - 1);
    }

    /**
     * The doASort method uses the ____________________ algorithm to sort
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end   The ending subscript of the list to sort
     */
    private static void doASort(int array[], int start, int end) {
        int pivotPoint;

        if (start < end) {
            // Get the pivot point.
            pivotPoint = part(array, start, end);
            // Note - only one +/=
            // Sort the first sub list.
            doASort(array, start, pivotPoint - 1);

            // Sort the second sub list.
            doASort(array, pivotPoint + 1, end);
        }
    }

    /**
     * The partition method selects a pivot value in an array and arranges the
     * array into two sub lists. All the values less than the pivot will be
     * stored in the left sub list and all the values greater than or equal to
     * the pivot will be stored in the right sub list.
     *
     * @param array The array to partition.
     * @param start The starting subscript of the area to partition.
     * @param end   The ending subscript of the area to partition.
     * @return The subscript of the pivot value.
     */
    private static int part(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript

        // see http://www.cs.cmu.edu/~fp/courses/15122-s11/lectures/08-qsort.pdf
        // for discussion of middle point
        // Find the subscript of the middle element.
        // This will be our pivot value.
        mid = (start + end) / 2;

        // Swap the middle element with the first element.
        // This moves the pivot value to the start of
        // the list.
        swap(array, start, mid);

        // Save the pivot value for comparisons.
        pivotValue = array[start];

        // For now, the end of the left sub list is
        // the first element.
        endOfLeftList = start;

        // Scan the entire list and move any values that
        // are less than the pivot value to the left
        // sub list.
        for (int scan = start + 1; scan <= end; scan++) {
            sortaCompares++;
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }

        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);

        // Return the subscript of the pivot value.
        return endOfLeftList;
    }

    /**
     * An implementation of the ______________________ Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */

    public static void bSort(int[] array) {
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan

        // The outer loop iterates once for each element in the
        // array. The startScan variable marks the position where
        // the scan should begin.
        for (startScan = 0; startScan < (array.length - 1); startScan++) {
            // Assume the first element in the scannable area
            // is the smallest value.
            minIndex = startScan;
            minValue = array[startScan];

            // Scan the array, starting at the 2nd element in
            // the scannable area. We are looking for the smallest
            // value in the scannable area.
            for (index = startScan + 1; index < array.length; index++) {
                if (array[index] < minValue) {
                    minValue = array[index];
                    minIndex = index;
                }
            }

            // Swap the element with the smallest value
            // with the first element in the scannable area.
            array[minIndex] = array[startScan];
            array[startScan] = minValue;
        }
    }

    /**
     * An implementation of the ______________________ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static void cSort(int[] array) {
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array

        // The outer loop steps the index variable through
        // each subscript in the array, starting at 1. The portion of
        // the array containing element 0  by itself is already sorted.
        for (int index = 1; index < array.length; index++) {
            // The first element outside the sorted portion is
            // array[index]. Store the value of this element
            // in unsortedValue.
            unsortedValue = array[index];

            // Start scan at the subscript of the first element
            // outside the sorted part.
            scan = index;

            // Move the first element in the still unsorted part
            // into its proper position within the sorted part.
            while (scan > 0 && array[scan - 1] > unsortedValue) {
                array[scan] = array[scan - 1];
                scan--;
            }

            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
    }


    /**
     * completes a ___________ sort on an array
     *
     * @param array the unsorted elements - will be sorted on completion
     */
    public static void dSort(int array[]) {
        int length = array.length;
        doDSort(array, 0, length - 1);
    }

    /**
     * private recursive method that splits array until we start at array sizes of 1
     *
     * @param array       starting array
     * @param lowerIndex  lower bound of array to sort
     * @param higherIndex upper bound of array to sort
     */

    private static void doDSort(int[] array, int lowerIndex, int higherIndex) {
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            doDSort(array, lowerIndex, middle);
            // Below step sorts the right side of the array
            doDSort(array, middle + 1, higherIndex);
            // Now put both parts together
            part1(array, lowerIndex, middle, higherIndex);
        }
    }

    /**
     * Puts two smaller sorted arrays into one sorted array
     *
     * @param array       provided in two sorted parts (1,4,9,2,3,11)
     * @param lowerIndex  the location of the first index
     * @param middle      - the middle element
     * @param higherIndex - the upper bound of the sorted elements
     */
    private static void part1(int[] array, int lowerIndex, int middle, int higherIndex) {

        int[] mArray = new int[higherIndex - lowerIndex + 1];
        for (int i = lowerIndex; i <= higherIndex; i++) {
            mArray[i - lowerIndex] = array[i];
        }
        // Part A - from lowerIndex to middle
        // Part B - from middle + 1 to higherIndex
        int partAIndex = lowerIndex;
        int partBIndex = middle + 1;
        int m = lowerIndex;
        while (partAIndex <= middle && partBIndex <= higherIndex) {
            // Place items back into Array in order
            // Select the lowestest of the two elements
            if (mArray[partAIndex - lowerIndex] <= mArray[partBIndex - lowerIndex]) {
                array[m] = mArray[partAIndex - lowerIndex];
                partAIndex++;
            } else {
                array[m] = mArray[partBIndex - lowerIndex];
                partBIndex++;
            }
            m++;
        }
        // Copy the remainder of PartA array (if required)
        while (partAIndex <= middle) {
            array[m] = mArray[partAIndex - lowerIndex];
            m++;
            partAIndex++;
        }
    }

    /**
     * Sorting using the _________ Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static void eSort(int[] array) {
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare

        // The outer loop positions lastPos at the last element
        // to compare during each pass through the array. Initially
        // lastPos is the index of the last element in the array.
        // During each iteration, it is decreased by one.
        for (lastPos = array.length - 1; lastPos >= 0; lastPos--) {
            // The inner loop steps through the array, comparing
            // each element with its neighbor. All of the elements
            // from index 0 thrugh lastPos are involved in the
            // comparison. If two elements are out of order, they
            // are swapped.
            for (index = 0; index <= lastPos - 1; index++) {
                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.

                    swap(array, index, index + 1);
                }
            }
        }
    }

    /**
     * Print an array to the Console
     *
     * @param A array to be printed
     */
    public static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.printf("%5d ", A[i]);
        }
        System.out.println();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] B;
        System.out.printf("Assignment#2 Sorting and Performance Analysis\n");
        System.out.printf("Completed by ________________________________\n");

        int size = 10;
        int[] A = new int[size];

        // Create random array with elements in the range of 0 to SIZE - 1;
        for (int i = 0; i < size; i++) {
            A[i] = (int) (Math.random() * size);
        }

        System.out.printf("\nComparison for array size of %d\n\n", size);

        B = Arrays.copyOf(A, A.length);      // Make sure you do this before each call do a sort method
        long startTime = System.nanoTime();
        aSort(B);
        long elapsedTime = System.nanoTime() - startTime;

        System.out.printf("Number of compares for aSort     = %10d\n", sortaCompares);
        System.out.printf("Time required for aSort          = %10d ns\n", elapsedTime);
        System.out.printf("Basic Step = %6.1f ns\n", (double) elapsedTime / sortaCompares);
    }
}

