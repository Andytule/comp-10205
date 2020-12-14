import jdk.swing.interop.SwingInterOpUtils;

/**
 * Generic Linked List class that always keeps the elements in order 
 * @author mark.yendt
 */
public class SortedLinkedList<T extends Comparable>
{
    /**
     * The Node class stores a list element and a reference to the next node.
     */
    private final class Node<T extends Comparable>
    {
        T value;
        Node<T> next;

        /**
         * Constructor.
         * @param val The element to store in the node.
         * @param n The reference to the successor node.
         */
        Node(T val, Node n)
        {
            value = val;
            next = n;
        }

        /**
         * Constructor.
         *
         * @param val The element to store in the node.
         */
        Node(T val)
        {
            // Call the other (sister) constructor.
            this(val, null);
        }
    }

    private Node<T> first;  // list head

    /**
     * Constructor.
     */
    public SortedLinkedList()
    {
        first = null;
    }

    /**
     * The isEmpty method checks to see if the list is empty.
     *
     * @return true if list is empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return first == null;
    }

    /**
     * The size method returns the length of the list.
     * @return The number of elements in the list.
     */
    public int size()
    {
        int count = 0;
        Node<T> p = first;
        while (p != null) {
            // There is an element at p
            count++;
            p = p.next;
        }
        return count;
    }

    /**
     * The add method adds an element at a position.
     * @param element The element to add to the list in sorted order.
     */
    public void add(T element)
    {
        Node<T> newNode = new Node(element);
        if (isEmpty() || 0 <= first.value.compareTo(element)) {
            newNode.next = first;
            first = newNode;
        } else {
            Node<T> current = first;
            Node<T> previous = new Node(first.value);
            while (current != null) {
                if (0 <= current.value.compareTo(element)) {
                    previous.next = newNode;
                    newNode.next = current;
                    break;
                }
                previous = current;
                current = current.next;
            }
            if (current == null) {
                previous.next = newNode;
            }

        }
    }

    /**
     * The toString method computes the string representation of the list.
     * @return The string form of the list.
     */
    public String toString()
    {
        String listOfItems = "[";

        // TODO: Iterate through the list and append items to end of listOfItems
        Node current = first;
        while (current != null) {
            listOfItems += current.value;
            current = current.next;
            if (current != null) listOfItems += ", ";
        }
        listOfItems += "]";
        return listOfItems;
    }

    /**
     * The remove method removes an element.
     * @param element The element to remove.
     * @return true if the remove succeeded, false otherwise.
     */
    public boolean remove(T element)
    {
        Boolean success = false;
        Node<T> current = first;
        Node<T> previous = null;
        while (current != null) {
            if (0 == current.value.compareTo(element)) {
                previous.next = current.next;
                success = true;
                break;
            }
            previous = current;
            current = current.next;
        }
        return success;
    }
}