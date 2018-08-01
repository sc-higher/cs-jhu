/**
 * This program is part of my response to Project 3 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 28 July 2018
 */


public class LinkedList<T> {

    private Node<T> first;
    private Node<T> last;


    /**
     * Constructor. Create a LinkedList object with initialized values.
     */
    public LinkedList() {
        first = null;
        last = null;
    }


    /**
     * If LinkedList<T> is empty, returns true. Otherwise returns false.
     *
     * @return
     */
    public boolean isEmpty() {
        if ( first == null ) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * Returns number of Nodes in LinkedList<T> as an integer value.
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public int size() {
        Node<T> current = first;
        int size = 0;

        while ( current != null ) {
            size++;
            current = current.getNext();
        }

        return size;
    }


    /**
     * Inserts a node with data of type T at the end of the LinkedList.
     *
     * @param data
     */
    public void insert(T data) {
        Node<T> node = new Node<T>(data);

        if ( this.isEmpty() ) {
            first = node;
            last = node;
            node.setNext(null);
            node.setPrevious(null);
        }
        else {
            last.setNext(node);
            node.setNext(null);
            node.setPrevious(last);
            last = node;
        }
    }


    /**
     * Converts a square matrix row and column to the index of the corresponding
     * Node in the LinkedList.
     *
     * @param row
     * @param col
     * @return   int value representing index in LinkedList of matrix(row,col)
     */
    public int matrixToListIndex(int row, int col) {
        int size = (int) Math.sqrt(this.size());
        int index;

        if ( row < size && col < size ) {
            index = size*row + col;
        }
        else {
            return 0;
        }

        return index;
    }


    /**
     * Sets the value of square matrix element matrix(row,col) to data of
     * type <T>.
     *
     * @param row
     * @param col
     * @param data
     */
    @SuppressWarnings("unchecked")
    public void setValue(int row, int col, T data) {
        int index = matrixToListIndex(row, col);
        Node<T> current = first;

        int i = 0;
        while ( i != index ) {
            current = current.getNext();
            i++;
        }

        current.setData(data);
    }


    /**
     * Gets the data of square matrix element matrix(row,col) Node.
     *
     * @param row
     * @param col
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getValue(int row, int col) {
        int index = matrixToListIndex(row, col);
        Node<T> current = first;

        int i = 0;
        while ( i != index ) {
            current = current.getNext();
            i++;
        }

        return current.getData();
    }


    /**
     * Swaps the data values of two square matrix rows in the storage
     * LinkedList
     *
     * @param row1
     * @param row2
     */
    public void swapRows(int row1, int row2) {
        int size = (int) Math.sqrt(this.size());
        T temp;

        for ( int i = 0; i < size; i++ ) {
            temp = this.getValue(row1, i);
            this.setValue(row1, i, this.getValue(row2, i));
            this.setValue(row2, i, temp);
        }
    }


    /**
     * Prints the data values of all Nodes in the Linked List.
     */
    @SuppressWarnings("unchecked")
    public void print() {
        Node<T> current = first;

        while ( current != null ) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }

        System.out.println();
    }


    /**
     *
     * @return
     */
    public Node getFirst() {
        return first;
    }


    /**
     *
     * @param first
     */
    public void setFirst(Node<T> first) {
        this.first = first;
    }


    /**
     *
     * @return
     */
    public Node getLast() {
        return last;
    }


    /**
     *
     * @param last
     */
    public void setLast(Node<T> last) {
        this.last = last;
    }
}