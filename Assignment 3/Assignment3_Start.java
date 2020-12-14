
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment3_Start {

    /**
     * The starting point of the application
     */
    public static void main(String[] args)
    {
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
                    System.out.printf("%s\n", fileWord);
                    count++;
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        System.out.println("There are " + count + " words in thew file ");
        // ADD other code after here

    }
}

