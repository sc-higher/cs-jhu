/**
 * This program is part of my response to Project 3 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * @author Sean Connor
 * @date 28 July 2018
 */


public class LinkedList<T> {

    private Node<T> header;
    private Node<T> first;
    private Node<T> last;


    /**
     * Constructor. Create a LinkedList object with initialized values.
     */
    public LinkedList() {
        header = null;
        first = null;
        last = null;
    }

    /**
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
     *
     * @param data
     */
    public void insert(T data) {
        Node<T> node = new Node<T>(data);

        if ( this.isEmpty() ) {
            first = node;
            last = node;
            node.setNext(null);
            node.setPrevious(header);
        }
        else {
            last.setNext(node);
            node.setNext(null);
            node.setPrevious(last);
            last = node;
        }
    }


    /**
     *
     * @param row
     * @param col
     * @return
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
     *
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
    public Node getHeader() {
        return header;
    }

    /**
     *
     * @param header
     */
    public void setHeader(Node<T> header) {
        this.header = header;
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