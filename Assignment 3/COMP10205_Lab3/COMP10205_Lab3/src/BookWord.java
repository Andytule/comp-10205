/*
 * Statement of Authorship - I, Andy Le, student number 000805099, certify
 * that this material is my original work. No other person's work has been used
 * without due acknowledgment and I have not made my work available to anyone else.
 *
 * A program that will count the words and occurrences of words in the complete Lord of the Rings Trilogy by J.R.R Tolkien
 * @author  Andy Le
 */
public class BookWord {
    private final String text;
    private int count;

    /**
     * Method to create class object
     * @param wordText
     */
    public BookWord(String wordText) {
        this.text = wordText;
        this.count = 1;
    }

    /**
     * Method to return text
     * @return
     */
    public String getText() { return this.text; }

    /**
     * Method to return count
     * @return
     */
    public int getCount() { return this.count; }

    /**
     * Method to increment count
     */
    public void incrementCount() {
        this.count++;
    }

    /**
     * Method to compare two BookWord Objects
     * @param obj The BookWord Object
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && this.getClass() == obj.getClass())
        {
            BookWord otherWord = (BookWord)obj;
            if (this.text == otherWord.text) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to calculate hashcode
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        for (int i = 0; i < text.length(); i++) {
            hash = hash * 31 + text.charAt(i);
        }
        return hash;
    }

    /**
     * To string method
     * @return
     */
    @Override
    public String toString() {
        return String.format(this.text + " " + this.count);
    }
}
