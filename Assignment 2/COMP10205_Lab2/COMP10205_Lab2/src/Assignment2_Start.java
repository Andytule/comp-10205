import java.util.Arrays;
/*
 *
 *

 */

/* Comp10205 - Sorting Assignment
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * Some of the sort code from courseWare textbook - Copyright, All rights reserved.
 * Additional code added by C. Mark Yendt in May 2014 August 2019
 * ADD Your Authorship and answers to Questions here :
 * _________________________________________________
 *
 * The sorting algorithms identified as as shown below:
 * A - Quick Sort
 * B - Selection Sort
 * C - Insertion Sort
 * D - Merge Sort
 * E - Bubble Sort
 *
 * In order (fastest to slowest), sorting 30 integers were as follows:
 * C - Insertion Sort
 * B - Selection Sort
 * A - Quick Sort
 * D - Merge Sort
 * E - Bubble Sort
 *
 * In order (fastest to slowest), sorting 30000 integers were as follows:
 * A - Quick Sort
 * D - Merge Sort
 * C - Insertion Sort
 * B - Selection Sort
 * E - Bubble Sort
 *
 * Algorithm and their BIG O notation:
 * Quick Sort - O(nlog(n))
 * Merge Sort - O(nlog(n))
 * Insertion Sort - O(n^2)
 * Selection Sort - O(n^2)
 * Bubble Sort -  O(n^2)
 * The Big O notation does line up with the results for 30000 elements.
 *
 * The O(n^2) algorithms have the best performance of the basic step, especially selection and insertion sort
 * This does not impact my selection of the fastest algorithm when sorting an array of 30000 elements
 * The reason for this is because the algorithm still have a large amount of comparisons.
 *
 * The performance of the standard Arrays.sort method resembles many sorting algorithms depending on the size of array
 * For the smaller arrays, it resembles the O(n^2) algorithms and for the larger arrays, it resembles the O(nlog(n)) ones
 *
 * Evaluation of the Sort Algorithms that were presented in class for efficiency.
 * @author  Andy Le
*/

public class Assignment2_Start {

    //static int sortaCompares = 0;  // Global for counting if recursion is not implemented

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
     * The Quick Sort - manages first call
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int aSort(int array[]) {
        return doASort(array, 0, array.length - 1);
    }

    /**
     * The doASort method uses the Quick Sort Algorithm to sort
     *
     * @param array The array to sort.
     * @param start The starting subscript of the list to sort
     * @param end   The ending subscript of the list to sort
     */
    private static int doASort(int array[], int start, int end) {
        int pivotPoint;
        int sortACompares = 0;
        if (start < end) {
            int[] temp = part(array, start, end);
            // Get the pivot point.
            pivotPoint = temp[0];
            sortACompares = temp[1];
            // Note - only one +/=
            // Sort the first sub list.
            sortACompares += doASort(array, start, pivotPoint - 1);
            // Sort the second sub list.
            sortACompares += doASort(array, pivotPoint + 1, end);
        }
        return sortACompares;
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
    private static int[] part(int array[], int start, int end) {
        int pivotValue;    // To hold the pivot value
        int endOfLeftList; // Last element in the left sub list.
        int mid;           // To hold the mid-point subscript
        int sortACompares = 0;
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
            sortACompares++;
            if (array[scan] < pivotValue) {
                endOfLeftList++;
                swap(array, endOfLeftList, scan);
            }
        }
        // Move the pivot value to end of the
        // left sub list.
        swap(array, start, endOfLeftList);
        // Return the subscript of the pivot value.
        return new int[]{endOfLeftList, sortACompares};
    }

    /**
     * An implementation of the Selection Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     * @return
     */
    public static int bSort(int[] array) {
        int startScan;   // Starting position of the scan
        int index;       // To hold a subscript value
        int minIndex;    // Element with smallest value in the scan
        int minValue;    // The smallest value found in the scan
        int sortBCompares = 0;
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
                sortBCompares++;
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
        return sortBCompares;
    }

    /**
     * An implementation of the Insertion Sort algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static int cSort(int[] array) {
        int unsortedValue;  // The first unsorted value
        int scan;           // Used to scan the array
        int sortCCompares = 0;
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
                sortCCompares++;
                array[scan] = array[scan - 1];
                scan--;
            }
            // Insert the unsorted value in its proper position
            // within the sorted subset.
            array[scan] = unsortedValue;
        }
        return sortCCompares;
    }

    /**
     * completes a Merge Sort on an array
     *
     * @param array the unsorted elements - will be sorted on completion
     */
    public static int dSort(int array[]) {
        int length = array.length;
        return doDSort(array, 0, length - 1);
    }

    /**
     * private recursive method that splits array until we start at array sizes of 1
     *
     * @param array       starting array
     * @param lowerIndex  lower bound of array to sort
     * @param higherIndex upper bound of array to sort
     */
    private static int doDSort(int[] array, int lowerIndex, int higherIndex) {
        int sortDCompares = 0;
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Below step sorts the left side of the array
            sortDCompares += doDSort(array, lowerIndex, middle);
            // Below step sorts the right side of the array
            sortDCompares += doDSort(array, middle + 1, higherIndex);
            // Now put both parts together
            sortDCompares += part1(array, lowerIndex, middle, higherIndex);
        }
        return sortDCompares;
    }

    /**
     * Puts two smaller sorted arrays into one sorted array
     *
     * @param array       provided in two sorted parts (1,4,9,2,3,11)
     * @param lowerIndex  the location of the first index
     * @param middle      - the middle element
     * @param higherIndex - the upper bound of the sorted elements
     */
    private static int part1(int[] array, int lowerIndex, int middle, int higherIndex) {
        int[] mArray = new int[higherIndex - lowerIndex + 1];
        int sortDCompares = 0;
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
            sortDCompares++;
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
        return sortDCompares;
    }

    /**
     * Sorting using the Bubble Sort Algorithm
     *
     * @param array an unsorted array that will be sorted upon method completion
     */
    public static int eSort(int[] array) {
        int lastPos;     // Position of last element to compare
        int index;       // Index of an element to compare
        int sortECompares = 0;
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
                sortECompares++;
                // Compare an element with its neighbor.
                if (array[index] > array[index + 1]) {
                    // Swap the two elements.
                    swap(array, index, index + 1);
                }
            }
        }
        return sortECompares;
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
        System.out.printf("Lab#2 Sorting and Performance Analysis\n");
        System.out.printf("======================================\n");

        int arraySize1 = 30;
        int arraySize2 = 300;
        int arraySize3 = 30000;
        int[] array1 = new int[arraySize1];
        int[] array2 = new int[arraySize2];
        int[] array3 = new int[arraySize3];
        int[] newArray;
        // Create random array with elements in the range of 0 to SIZE - 1;
        for (int i = 0; i < arraySize1; i++) {
            array1[i] = (int) (Math.random() * arraySize1);
        }
        for (int i = 0; i < arraySize2; i++) {
            array2[i] = (int) (Math.random() * arraySize2);
        }
        for (int i = 0; i < arraySize3; i++) {
            array3[i] = (int) (Math.random() * arraySize3);
        }

        int aCompares1;
        int bCompares1;
        int cCompares1;
        int dCompares1;
        int eCompares1;

        System.out.printf("\nComparison for array size of %d\n\n", arraySize1);

        newArray = Arrays.copyOf(array1, array1.length);      // Make sure you do this before each call do a sort method

        long aStartTime1 = System.nanoTime();
        aCompares1 = aSort(newArray);
        long aElapsedTime1 = System.nanoTime() - aStartTime1;
        double aBasicStep1 = aElapsedTime1 / aCompares1;

        newArray = Arrays.copyOf(array1, array1.length);
        long bStartTime1 = System.nanoTime();
        bCompares1 = bSort(newArray);
        long bElapsedTime1 = System.nanoTime() - bStartTime1;
        double bBasicStep1 = bElapsedTime1 / bCompares1;

        newArray = Arrays.copyOf(array1, array1.length);
        long cStartTime1 = System.nanoTime();
        cCompares1 = cSort(newArray);
        long cElapsedTime1 = System.nanoTime() - cStartTime1;
        double cBasicStep1 = cElapsedTime1 / cCompares1;

        newArray = Arrays.copyOf(array1, array1.length);
        long dStartTime1 = System.nanoTime();
        dCompares1 = dSort(newArray);
        long dElapsedTime1 = System.nanoTime() - dStartTime1;
        double dBasicStep1 = dElapsedTime1 / dCompares1;

        newArray = Arrays.copyOf(array1, array1.length);
        long eStartTime1 = System.nanoTime();
        eCompares1 = eSort(newArray);
        long eElapsedTime1 = System.nanoTime() - eStartTime1;
        double eBasicStep1 = eElapsedTime1 / eCompares1;

        newArray = Arrays.copyOf(array1, array1.length);
        long fStartTime1 = System.nanoTime();
        Arrays.sort(newArray);
        long fElapsedTime1 = System.nanoTime() - fStartTime1;

        System.out.printf("Number of compares for sort a = %d, time = %d ns, Basic Step = %.1f ns", aCompares1, aElapsedTime1, aBasicStep1);
        System.out.println();
        System.out.printf("Number of compares for sort b = %d, time = %d ns, Basic Step = %.1f ns", bCompares1, bElapsedTime1, bBasicStep1);
        System.out.println();
        System.out.printf("Number of compares for sort c = %d, time = %d ns, Basic Step = %.1f ns", cCompares1, cElapsedTime1, cBasicStep1);
        System.out.println();
        System.out.printf("Number of compares for sort d = %d, time = %d ns, Basic Step = %.1f ns", dCompares1, dElapsedTime1, dBasicStep1);
        System.out.println();
        System.out.printf("Number of compares for sort e = %d, time = %d ns, Basic Step = %.1f ns", eCompares1, eElapsedTime1, eBasicStep1);
        System.out.println();
        System.out.printf("\nArray.sort method time = %d ns", fElapsedTime1);

        int aCompares2;
        int bCompares2;
        int cCompares2;
        int dCompares2;
        int eCompares2;

        System.out.printf("\n\nComparison for array size of %d\n\n", arraySize2);

        newArray = Arrays.copyOf(array2, array2.length);      // Make sure you do this before each call do a sort method

        long aStartTime2 = System.nanoTime();
        aCompares2 = aSort(newArray);
        long aElapsedTime2 = System.nanoTime() - aStartTime2;
        double aBasicStep2 = aElapsedTime2 / aCompares2;

        newArray = Arrays.copyOf(array2, array2.length);
        long bStartTime2 = System.nanoTime();
        bCompares2 = bSort(newArray);
        long bElapsedTime2 = System.nanoTime() - bStartTime2;
        double bBasicStep2 = bElapsedTime2 / bCompares2;

        newArray = Arrays.copyOf(array2, array2.length);
        long cStartTime2 = System.nanoTime();
        cCompares2 = cSort(newArray);
        long cElapsedTime2 = System.nanoTime() - cStartTime2;
        double cBasicStep2 = cElapsedTime2 / cCompares2;

        newArray = Arrays.copyOf(array2, array2.length);
        long dStartTime2 = System.nanoTime();
        dCompares2 = dSort(newArray);
        long dElapsedTime2 = System.nanoTime() - dStartTime2;
        double dBasicStep2 = dElapsedTime2 / dCompares2;

        newArray = Arrays.copyOf(array2, array2.length);
        long eStartTime2 = System.nanoTime();
        eCompares2 = eSort(newArray);
        long eElapsedTime2 = System.nanoTime() - eStartTime2;
        double eBasicStep2 = eElapsedTime2 / eCompares2;

        newArray = Arrays.copyOf(array2, array2.length);
        long fStartTime2 = System.nanoTime();
        Arrays.sort(newArray);
        long fElapsedTime2 = System.nanoTime() - fStartTime2;

        System.out.printf("Number of compares for sort a = %d, time = %d ns, Basic Step = %.1f ns", aCompares2, aElapsedTime2, aBasicStep2);
        System.out.println();
        System.out.printf("Number of compares for sort b = %d, time = %d ns, Basic Step = %.1f ns", bCompares2, bElapsedTime2, bBasicStep2);
        System.out.println();
        System.out.printf("Number of compares for sort c = %d, time = %d ns, Basic Step = %.1f ns", cCompares2, cElapsedTime2, cBasicStep2);
        System.out.println();
        System.out.printf("Number of compares for sort d = %d, time = %d ns, Basic Step = %.1f ns", dCompares2, dElapsedTime2, dBasicStep2);
        System.out.println();
        System.out.printf("Number of compares for sort e = %d, time = %d ns, Basic Step = %.1f ns", eCompares2, eElapsedTime2, eBasicStep2);
        System.out.println();
        System.out.printf("\nArray.sort method time = %d ns", fElapsedTime2);

        int aCompares3;
        int bCompares3;
        int cCompares3;
        int dCompares3;
        int eCompares3;

        System.out.printf("\n\nComparison for array size of %d\n\n", arraySize3);

        newArray = Arrays.copyOf(array3, array3.length);      // Make sure you do this before each call do a sort method

        long aStartTime3 = System.nanoTime();
        aCompares3 = aSort(newArray);
        long aElapsedTime3 = System.nanoTime() - aStartTime3;
        double aBasicStep3 = aElapsedTime3 / aCompares3;

        newArray = Arrays.copyOf(array3, array3.length);
        long bStartTime3 = System.nanoTime();
        bCompares3 = bSort(newArray);
        long bElapsedTime3 = System.nanoTime() - bStartTime3;
        double bBasicStep3 = bElapsedTime3 / bCompares3;

        newArray = Arrays.copyOf(array3, array3.length);
        long cStartTime3 = System.nanoTime();
        cCompares3 = cSort(newArray);
        long cElapsedTime3 = System.nanoTime() - cStartTime3;
        double cBasicStep3 = cElapsedTime3 / cCompares3;

        newArray = Arrays.copyOf(array3, array3.length);
        long dStartTime3 = System.nanoTime();
        dCompares3 = dSort(newArray);
        long dElapsedTime3 = System.nanoTime() - dStartTime3;
        double dBasicStep3 = dElapsedTime3 / dCompares3;

        newArray = Arrays.copyOf(array3, array3.length);
        long eStartTime3 = System.nanoTime();
        eCompares3 = eSort(newArray);
        long eElapsedTime3 = System.nanoTime() - eStartTime3;
        double eBasicStep3 = eElapsedTime3 / eCompares3;

        newArray = Arrays.copyOf(array3, array3.length);
        long fStartTime3 = System.nanoTime();
        Arrays.sort(newArray);
        long fElapsedTime3 = System.nanoTime() - fStartTime3;

        System.out.printf("Number of compares for sort a = %d, time = %d ns, Basic Step = %.1f ns", aCompares3, aElapsedTime3, aBasicStep3);
        System.out.println();
        System.out.printf("Number of compares for sort b = %d, time = %d ns, Basic Step = %.1f ns", bCompares3, bElapsedTime3, bBasicStep3);
        System.out.println();
        System.out.printf("Number of compares for sort c = %d, time = %d ns, Basic Step = %.1f ns", cCompares3, cElapsedTime3, cBasicStep3);
        System.out.println();
        System.out.printf("Number of compares for sort d = %d, time = %d ns, Basic Step = %.1f ns", dCompares3, dElapsedTime3, dBasicStep3);
        System.out.println();
        System.out.printf("Number of compares for sort e = %d, time = %d ns, Basic Step = %.1f ns", eCompares3, eElapsedTime3, eBasicStep3);
        System.out.println();
        System.out.printf("\nArray.sort method time = %d ns", fElapsedTime3);

        System.out.println();
    }
}

