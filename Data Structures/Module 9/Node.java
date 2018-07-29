/**
 * This program is part of my response to Project 3 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 28 July 2018
 */

public class Node<T> {

    private T data;
    private Node<T> next;
    private Node<T> previous;

    /**
     * Constructor. Create a Node object with data of type T.
     *
     * @param data   The data to be contained by the Node.
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Get method for Node data.
     *
     * @return
     */
    public T getData() {
        return data;
    }

    /**
     * Get method for next Node.
     *
     * @return
     */
    public Node getNext() {
        return next;
    }

    /**
     * Get method for previous Node.
     *
     * @return
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     *  Set method for next Node.
     *
     * @param next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Set method for previous Node.
     *
     * @param previous
     */
    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    /**
     * Prints the data content of Node to terminal
     */
    public void printData() {
        System.out.print(data);
    }

}