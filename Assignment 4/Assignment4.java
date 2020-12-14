/**
 * COMP10205 - Lab#4 Starting Code
 */
package Comp10205_lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Assignment4
{
  public static void main(String[] args)
  {
    final int NUMBER_OF_NAMES = 18756;
    final String filename = "resources/bnames.txt";
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
    for (int i=0; i<NUMBER_OF_NAMES;i++)
        linkedList_String.add(names[i]);
    System.out.printf("The time required to add %d elements to a Linked List = %d us\n", NUMBER_OF_NAMES, (System.nanoTime() - start) / 1000);
    System.out.println(linkedList_String);
    
    // 2. Remove half the items and time the code.

    // 3. Create a SortedLinkedList of another data type and demonstrate 
    
    // 4. Build a standard ArrayList of String - Remember to sort list after each element is added
    //    Time this code.
    //    Use this timing data to compare add against SortedLinkedList in discussion
    //    Remove the half the elements and time again.  
    //    Use this timing data to compare remove against SortedLinkedList in discussion

    ArrayList<String> arrayList_String = new ArrayList<>();
    
  }   
}
