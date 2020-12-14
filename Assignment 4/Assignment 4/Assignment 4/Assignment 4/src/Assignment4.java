/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * Implementation of linked list (sorted)
 * @author  Andy Le
 */
import jdk.swing.interop.SwingInterOpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Assignment4
{
    /*
        There is a significance difference between adding items to a linked list than an array.
        Adding to an array list is much quicker than adding to a linked list

        There is a somewhat significance difference when removing items. I was only able to test for a small number of
        elements being removed but it was always removing from an arraylist that was quicker than the linked list removal

        Based on the results of the results of this assignment, I would choose linked list over arraylist when the dataset
        is relatively small as it appears that traversing a linked list with larger datasets will take longer.
     */
    public static void main(String[] args)
    {
        final int NUMBER_OF_NAMES = 1000;
        final String filename = "src/test.txt";
        final String[] names = new String[NUMBER_OF_NAMES];

        // May be useful for selecting random words to remove
        Random random = new Random();

        // Read in all of the names
        try {
            Scanner fin = new Scanner(new File(filename));
            for (int i=0; i<NUMBER_OF_NAMES; i++)
                names[i] = fin.next();
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.exit(-1);
        }

        // Use the system to build the linked List

        // 1. Create the linkedList and add all items in Array
        SortedLinkedList<String> linkedList_String = new SortedLinkedList<>();
        long start = System.nanoTime();
        for (int i=0; i<NUMBER_OF_NAMES;i++) {
            linkedList_String.add(names[i]);
        }

        System.out.println(linkedList_String);
        System.out.printf("The time required to add %d elements (strings) to a Linked List = %d us\n", NUMBER_OF_NAMES, (System.nanoTime() - start) / 1000);
        System.out.println("");

/////////////////////////////////////////////////////////



        // 2. Remove half the items and time the code.
        long startRemove1 = System.nanoTime();
        linkedList_String.remove("Madi");
        linkedList_String.remove("Hayzel");
        linkedList_String.remove("Stellarose");
        linkedList_String.remove("Karis");
        linkedList_String.remove("Karis");
        linkedList_String.remove("Yuliett");
        linkedList_String.remove("Lianna");
        linkedList_String.remove("Vickie");
        linkedList_String.remove("Rukia");
        linkedList_String.remove("Jhoana");
        linkedList_String.remove("Aditri");
        linkedList_String.remove("Avey");
        linkedList_String.remove("Graycen");
        linkedList_String.remove("Bryleigh");
        linkedList_String.remove("Defne");
        linkedList_String.remove("Zalena");
        linkedList_String.remove("Savannahrose");
        linkedList_String.remove("Kirin");
        linkedList_String.remove("Oaklynn");
        linkedList_String.remove("Sinai");
        linkedList_String.remove("Tyla");
        long endRemoveLinked = System.nanoTime() - startRemove1;
        System.out.println(linkedList_String);
        System.out.println("Removed some elements");
        System.out.println("");

        // 3. Create a SortedLinkedList of another data type and demonstrate
        final int NUMBER_OF_NUMBERS = 1000;
        final String filename2 = "src/test2.txt";
        final int[] names2 = new int[NUMBER_OF_NUMBERS];

        // Read in all of the names
        try {
            Scanner fin = new Scanner(new File(filename2));
            for (int i=0; i<NUMBER_OF_NUMBERS; i++)
                names2[i] = Integer.parseInt(fin.next());
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.exit(-1);
        }

        // Use the system to build the linked List

        SortedLinkedList<Integer> linkedList_Int = new SortedLinkedList<>();
        long start2 = System.nanoTime();
        for (int i=0; i<NUMBER_OF_NUMBERS;i++)
            linkedList_Int.add(names2[i]);
        System.out.println(linkedList_Int);
        System.out.printf("The time required to add %d elements (integers) to a Linked List = %d us\n", NUMBER_OF_NUMBERS, (System.nanoTime() - start2) / 1000);
        System.out.println("");

        // 4. Build a standard ArrayList of String - Remember to sort list after each element is added
        //    Time this code.
        //    Use this timing data to compare add against SortedLinkedList in discussion
        //    Remove the half the elements and time again.
        //    Use this timing data to compare remove against SortedLinkedList in discussion
        //////////////////////

        ArrayList<String> arrayList_String = new ArrayList<>();

        // Read in all of the names
        try {
            Scanner fin = new Scanner(new File(filename));
            for (int i=0; i<NUMBER_OF_NAMES; i++)
                names[i] = fin.next();
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
            System.exit(-1);
        }

        // Use the system to build the linked List

        // 1. Create the linkedList and add all items in Array
        long start3 = System.nanoTime();
        for (int i=0; i<NUMBER_OF_NAMES;i++) {
            arrayList_String.add(names[i]);
        }

        System.out.println(linkedList_String);
        System.out.printf("The time required to add %d elements to a arraylist = %d us\n", NUMBER_OF_NAMES, (System.nanoTime() - start3) / 1000);
        System.out.println("");

        long startRemove2 = System.nanoTime();
        arrayList_String.remove("Madi");
        arrayList_String.remove("Hayzel");
        arrayList_String.remove("Stellarose");
        arrayList_String.remove("Karis");
        arrayList_String.remove("Karis");
        arrayList_String.remove("Yuliett");
        arrayList_String.remove("Lianna");
        arrayList_String.remove("Vickie");
        arrayList_String.remove("Rukia");
        arrayList_String.remove("Jhoana");
        arrayList_String.remove("Aditri");
        arrayList_String.remove("Avey");
        arrayList_String.remove("Graycen");
        arrayList_String.remove("Bryleigh");
        arrayList_String.remove("Defne");
        arrayList_String.remove("Zalena");
        arrayList_String.remove("Savannahrose");
        arrayList_String.remove("Kirin");
        arrayList_String.remove("Oaklynn");
        arrayList_String.remove("Sinai");
        arrayList_String.remove("Tyla");
        long endRemoveArray = System.nanoTime() - startRemove2;

        System.out.printf("The time required to remove 20 elements from a linked list = %d us\n", endRemoveLinked / 1000);
        System.out.printf("The time required to remove 20 elements from an arraylist = %d us\n", endRemoveArray / 1000);
    }
}
