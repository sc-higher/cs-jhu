/**
 * This program is part of my response to Project 1 for the class 605.202
 * Data Structures at the JHU EPP CS program.
 *
 * This class is used to create an array-based stack. This stack holds String
 * type values in a String[].
 *
 * @author Sean Connor
 * @date 30 June 2018
 */


/**
 * This class is used to create an array-based stack. This stack holds String
 * type values in a String[].
 *
 * Requirement - You need to read the characters in and parse them as you read
 * them. The stack is not intended to hold the input, it is to be used to
 * facilitate the parsing.
 *
 * @author Sean Connor
 * @date 30 June 2018
 */

public class Stack{
    private String[] arr;
    private int size;
    private int top = 0;

    public Stack(int size){
        this.size = size;
        arr = new String[size];
    }

    /**
     *
     * @param element
     */
    public void push(String element) throws StackOverflowException{
        if(top == size) {
            throw new StackOverflowException("Stack Overflow");
        }
        else {
            arr[top] = element;
            top++;
        }
    }

    /**
     *
     * @return
     */
    public String pop(){
        if (isEmpty()){
            return null;
        }
        else{
            top--;
            return arr[top];
        }
    }

    /**
     *
     * @return
     */
    public String peek(){
        if (isEmpty()){
            return null;
        }
        return arr[top-1];
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        if (top == 0) {
            return true;
        }
        return false;
    }
}