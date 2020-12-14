/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * Implementation of Mountain Peaks and Valleys program that will analyze a set of
 * elevation data collected in a text file
 * @author  Andy Le
 */
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    // Main method that reads in data and runs analyzing methods
    public static void main(String[] args) {
        final String fn = "src/ELEVATIONS.TXT";
        File f = new File(fn);
        int r;
        int c;
        int ex;
        int[][] data;
        int l;
        int h;
        try {
            Scanner fi = new Scanner(f);
            r = fi.nextInt();
            c = fi.nextInt();
            ex = fi.nextInt();
            data = new int[r][c];
            int count = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    data[i][j] = fi.nextInt();

                }
            }
            h = data[0][0];
            l = data[0][0];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if  (data[i][j] < l) {
                        l = data[i][j];
                    }
                    if (h < data[i][j]) {
                        h = data[i][j];
                    }
                }
            }
            // Timer to count speed of methods
            long start = System.nanoTime();
            int[] one = first(data);
            Peaks[] two = second(data, r, c, ex);
            Distance[] three = third(two);
            int[] four = fourth(data, l, h);
            long stop = System.nanoTime();

            // String output
            System.out.printf("Total Time to solve problem = %d us (about %d ms) \n\n", (stop - start) / 1000, Math.round((stop - start) / 1000000));
            // Print the lowest elevation value and the number of times it is found in the complete data set
            System.out.println("The lowest peak is " + one[0] + ". It occurs " + one[1] + " times in the map");
            // Print all the local peaks where the peak elevation is greater or equal to 98480 with a value for n (index radius)
            System.out.println("The total number of peaks is " + two.length);
            // Print the row and column of the two closest local peaks using the formula for distance presented in the assignment document
            System.out.println("There are " + three.length + " sets of closest peaks");
            for (Distance i: three) {
                System.out.println(i.toString());
            }
            // Print the most common elevation in the data set.
            System.out.println("The most common height in the terrain is " + four[0] + " it occurs " + four[1] + " times");

        } catch (IOException e) {
            System.out.println("Error processing file " + e.getMessage());
        }
    }

    // Method for the first question
    public static int[] first(int[][] d) {
        int[] l = new int[]{d[0][0], 0};
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                if (d[i][j] < l[0]) {
                    l[0] = d[i][j];
                    l[1] = 1;
                } else if (d[i][j] == l[0]) {
                    l[1]++;
                }
            }
        }
        return l;
    }

    // Method for the second question
    public static Peaks[] second(int[][] d, int r, int c, int ex) {
        Peaks[] o = new Peaks[r * c];
        int count = 0;
        for (int i = ex; i < r - ex; i++) {
            for (int j = ex; j < c - ex; j++) {
                if ((valid(d, i, j, ex)) && (98480 <= d[i][j])) {
                    o[count++] = new Peaks(i, j, d[i][j]);
                }
            }
        }
        return Arrays.copyOf(o, count);
    }

    // Helper method for the second question
    public static boolean valid(int[][] d, int i, int j, int ex) {
        for (int k = i - ex; k <= i + ex; k++) {
            for (int l = j - ex; l <= j + ex; l++) {
                if ((k != i) || (l != j)) {
                    if (d[i][j] <= d[k][l]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Method for the third question
    public static Distance[] third(Peaks[] p) {
        Distance[] d = new Distance[p.length * p.length];
        double l = Math.sqrt(Math.pow((p[0].getR() - p[1].getR()), 2) + Math.pow((p[0].getC() - p[1].getC()), 2));
        int count = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = i + 1; j < p.length; j++) {
                if (i != j) {
                    if (Math.sqrt(Math.pow((p[i].getR() - p[j].getR()), 2) + Math.pow((p[i].getC() - p[j].getC()), 2)) < l) {
                        l = Math.sqrt(Math.pow((p[i].getR() - p[j].getR()), 2) + Math.pow((p[i].getC() - p[j].getC()), 2));
                        d = new Distance[p.length * p.length];
                        count = 0;
                        d[count++] = new Distance(p[i], p[j], Math.sqrt(Math.pow((p[i].getR() - p[j].getR()), 2) + Math.pow((p[i].getC() - p[j].getC()), 2)));
                    } else if (Math.sqrt(Math.pow((p[i].getR() - p[j].getR()), 2) + Math.pow((p[i].getC() - p[j].getC()), 2)) == l) {
                        d[count++] = new Distance(p[i], p[j], Math.sqrt(Math.pow((p[i].getR() - p[j].getR()), 2) + Math.pow((p[i].getC() - p[j].getC()), 2)));
                    }
                }
            }
        }
        return  Arrays.copyOf(d, count);
    }

    // Method for the fourth question
    public static int[] fourth(int[][] d, int l, int h) {
        int[] c = new int[h - l + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = 0;
        }
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                c[d[i][j] - l]++;
            }
        }
        int mv = c[0];
        int mi = 0;
        for (int i = 0; i < c.length; i++) {
            if (mi < c[i]) {
                mv = i;
                mi = c[i];
            }
        }
        return new int[]{mv + l, mi};
    }
}
