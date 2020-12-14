/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * A program that will attempt to optimize the time it takes to process customers in check-out lines inside a grocery
 * store
 * @author  Andy Le
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //PART A
        ArrayList<LinkedQueue> lanesA = new ArrayList<>();
        ArrayList<Integer> timesA = new ArrayList<>();

        final String filenameA = "src/CustomerData_Example.txt";
        try {
            Scanner file = new Scanner(new File(filenameA)); //Read in the text file
            int expressNum = Integer.parseInt(file.next());
            int normalNum = Integer.parseInt(file.next());
            int x = Integer.parseInt(file.next());
            int numberCustomer = Integer.parseInt(file.next());
            int xTime = 45 + (5 * x);
            for (int i = 0; i < expressNum; i++) { //Create the express lines
                LinkedQueue<Customer> temp = new LinkedQueue<>();
                lanesA.add(temp);
                timesA.add(0);
            }
            for (int i = 0; i < normalNum; i++) { //Create the normal lines
                LinkedQueue<Customer> temp = new LinkedQueue<>();
                lanesA.add(temp);
                timesA.add(0);
            }
            while (file.hasNext()) { //Read in the rest of the text file
                Customer temp = new Customer(Integer.parseInt(file.next()));
                if (temp.time() <= xTime) { //Organize the customers into the optimized lines
                    int minIndex = 0;
                    for (int i = 0; i < timesA.size(); i++) {
                         if (timesA.get(i) < timesA.get(minIndex)) {
                             minIndex = i;
                         }
                    }
                    temp.setStartTime(timesA.get(minIndex));
                    lanesA.get(minIndex).enqueue(temp);
                    timesA.set(minIndex, timesA.get(minIndex) + temp.time());
                } else {
                    int minIndex = expressNum;
                    for (int i = expressNum; i < timesA.size(); i++) {
                        if (timesA.get(i) < timesA.get(minIndex)) {
                            minIndex = i;
                        }
                    }
                    temp.setStartTime(timesA.get(minIndex));
                    lanesA.get(minIndex).enqueue(temp);
                    timesA.set(minIndex, timesA.get(minIndex) + temp.time());
                }
            }

            int maxIndex = 0; //Identify the longest time
            for (int i = 0; i < timesA.size(); i++) {
                if (timesA.get(maxIndex) < timesA.get(i)) {
                    maxIndex = i;
                }
            }

            System.out.println("PART A - Checkout lines and time estimates for each line\n"); //Print the output

            for (int i = 0; i < expressNum; i++) {
                System.out.println("CheckOut(Express) # 1 (Est Time = " + timesA.get(i) + " s) = " + lanesA.get(i).toString());
            }

            for (int i = expressNum; i < expressNum + normalNum; i++) {
                System.out.println("CheckOut(Normal) # 1 (Est Time = " + timesA.get(i) + " s) = " + lanesA.get(i).toString());
            }

            System.out.println("Time to clear store of all customers = " + timesA.get(maxIndex) + " s\n");
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        //PART B

        ArrayList<LinkedQueue> lanesB = new ArrayList<>();
        ArrayList<Integer> timesB = new ArrayList<>();

        final String filenameB = "src/CustomerData.txt";
        try {
            Scanner file = new Scanner(new File(filenameB)); //Read in the text file
            int expressNum = Integer.parseInt(file.next());
            int normalNum = Integer.parseInt(file.next());
            int x = Integer.parseInt(file.next());
            int numberCustomer = Integer.parseInt(file.next());
            int xTime = 45 + (5 * x);
            for (int i = 0; i < expressNum; i++) { //Create the express lines
                LinkedQueue<Customer> temp = new LinkedQueue<>();
                lanesB.add(temp);
                timesB.add(0);
            }
            for (int i = 0; i < normalNum; i++) { //Create the normal lines
                LinkedQueue<Customer> temp = new LinkedQueue<>();
                lanesB.add(temp);
                timesB.add(0);
            }
            while (file.hasNext()) { //Read in the rest of the text file
                Customer temp = new Customer(Integer.parseInt(file.next()));
                if (temp.time() <= xTime) { //Organize the customers into the optimized lines
                    int minIndex = 0;
                    for (int i = 0; i < timesB.size(); i++) {
                        if (timesB.get(i) < timesB.get(minIndex)) {
                            minIndex = i;
                        }
                    }
                    temp.setStartTime(timesB.get(minIndex));
                    lanesB.get(minIndex).enqueue(temp);
                    timesB.set(minIndex, timesB.get(minIndex) + temp.time());
                } else {
                    int minIndex = expressNum;
                    for (int i = expressNum; i < timesB.size(); i++) {
                        if (timesB.get(i) < timesB.get(minIndex)) {
                            minIndex = i;
                        }
                    }
                    temp.setStartTime(timesB.get(minIndex));
                    lanesB.get(minIndex).enqueue(temp);
                    timesB.set(minIndex, timesB.get(minIndex) + temp.time());
                }
            }

            int maxIndex = 0; //Identify the longest time
            for (int i = 0; i < timesB.size(); i++) {
                if (timesB.get(maxIndex) < timesB.get(i)) {
                    maxIndex = i;
                }
            }

            System.out.println("PART B - Number of customers in line after each minute (60s)\n"); //Print output

            String header = "t(s)";
            for (int i = 1; i <= timesB.size(); i++) {
                header += "   Line " + i;
            }
            System.out.println(header);

            for (int i = 0; i < timesB.get(maxIndex) + 30; i++) { //Run simulation with additional 30 seconds to show empty lines
                if ((i % 30) == 0) { //Display per 30 seconds
                    String update = String.format("%3d", i);
                    for (LinkedQueue j : lanesB) {
                        update += "        " + j.size();
                    }
                    System.out.println(update);
                }
                for (int j = 0; j < lanesB.size(); j++) { //Dequeue checker
                    if (!lanesB.get(j).isEmpty()) {
                        Customer current = (Customer) lanesB.get(j).peek();

                        int endTime = current.getStartTime() + current.time();

                        if (endTime == i) {
                            lanesB.get(j).dequeue();
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
