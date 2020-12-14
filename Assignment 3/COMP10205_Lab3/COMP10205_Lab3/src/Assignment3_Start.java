/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * A program that will count the words and occurrences of words in the complete Lord of the Rings Trilogy by J.R.R Tolkien
 * @author  Andy Le
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment3_Start {

    /**
     * The main method to read in files and cound the number of occurrences
     * @param args
     */
    public static void main(String[] args)
    {
        SimpleHashSet<BookWord> words = new SimpleHashSet<>();
        ArrayList<BookWord> thewords = new ArrayList<>();
        // File is stored in a resources folder in the project
        final String filename = "src/TheLOrdOfTheRings.txt";
        int count = 0;
        try {
            Scanner fin = new Scanner(new File(filename));
            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
            while (fin.hasNext()) {
                String fileWord = fin.next().toLowerCase();
                if (fileWord.length() > 0)
                {
                    // Just print to the screen for now - REMOVE once you have completed code
                    BookWord temp = new BookWord(fileWord);
                    thewords.add(temp);
                    count++;
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        for (BookWord temp : thewords) {
            BookWord test = words.get(temp);
            if (test != null) {
                System.out.println("yea");
            } else {
                words.insert(temp);
            }
        }

        System.out.println("There are " + thewords.size() + " words in thew file ");
        // ADD other code after here

    }
}


