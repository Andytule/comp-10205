/*
Statement of Authorship - I, Andy Le, student number 000805099, certify
that this material is my original work. No other person's work has been used
without due acknowledgment and I have not made my work available to anyone else.
 */
public class Distance {
    // Peak 1
    private Peaks a;
    // Peak 2
    private Peaks b;
    // Distance between peak
    private double d;

    // Distance constructor
    public Distance (Peaks a, Peaks b, double d) {
        this.a = a;
        this.b = b;
        this.d = d;
    }

    // Peak 1 get method
    public Peaks getA() {
        return this.a;
    }
    // Peak 2 get method
    public Peaks getB() {
        return this.b;
    }
    // Peak distance get method
    public double getD() {
        return this.d;
    }
    // String output method
    public String toString() {
        return "The distance between two peaks = " + String.format("%.2f", this.d) + " m\npeaks are [" + this.a.getC() + "," + this.a.getR() + " elevation = " + a.getH() + "] and\n[" + this.b.getC() + "," + this.b.getR() + " elevation = " + b.getH() + "]";
    }
}
