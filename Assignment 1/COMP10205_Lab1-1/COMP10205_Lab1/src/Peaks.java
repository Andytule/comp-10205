/*
Statement of Authorship - I, Andy Le, student number 000805099, certify
that this material is my original work. No other person's work has been used
without due acknowledgment and I have not made my work available to anyone else.
 */
public class Peaks {
    // Row of peak
    private int r;
    // Column of peak
    private int c;
    // Height of peak
    private int h;

    // Peak constructor
    public Peaks (int r, int c, int h) {
        this.r = r;
        this.c = c;
        this.h = h;
    }

    // Row get method
    public int getR() {
        return this.r;
    }
    // Column get method
    public int getC() {
        return this.c;
    }
    // Height get method
    public int getH() {
        return this.h;
    }
    // String output method
    public String toString() {
        return "Row: " + r + " Column: " + c + " Height: " + h;
    }
}
