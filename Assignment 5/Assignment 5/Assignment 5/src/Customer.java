public class Customer {
    private int ni = 0;
    private int startTime = -1;

    public Customer (int ni) {
        this.ni = ni;
    }

    public int time() {
        return 45 + (5 * this.ni);
    }

    public void setStartTime(int start) {
        this.startTime = start;
    }

    public int getStartTime() {
        return this.startTime;
    }

    @Override
    public String toString() {
        return "[" + this.ni + "(" + time() + " s)]";
    }
}
