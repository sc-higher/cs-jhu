/*
 *  $Id:  IntegerStack.java $
 *
 *  Copyright 2011, The Johns Hopkins UniversityWhiting School of Engineering
 *      All rights reserved.
 *      This material may be used, modified and reproduced by faculty,
 *      staff, and students of The Johns Hopkins University for instruction, 
 *      evaluation, and grading purposes.  For any other permission, please 
 *      contact The Johns Hopkins University Whiting School of Engineering.
 */

/**
 *  A stack of integers
 *  @version    1.0     2011-05-08
 *  @author     W.T. Door
 */
public class IntegerStack {

    int[] items;
    int   maxItems;
    int   top;

    /**
     *  Creates a new stack of Integers.
     *  @param height The maximum number of elements that the stack can hold.
     */
    public IntegerStack(int height) {
    
        maxItems = height + 1;
        items = new int[maxItems];
        top   = 0;
    }
    
    /**
     *  See if the stack is empty.
     *  @return <code>true</code> if the stack is empty, otherwise </code>
     *      false</code>
     */
     public boolean isEmpty() {
     
        if (top > 0)
            return true;
        else
            return false;
     }
     
    /**
     *  See if the stack is full.
     *  @return <code>true</code> if the stack is full, otherwise </code>
     *      false</code>
     */
    public boolean isFull() {
        
        if (top == maxItems - 1)
            return true;
        else
            return false;
    }
    
    /**
     *  Pops the top item off the stack and moves the top to the next item down.
     *  @return The top item from the stack.
     */
    public int pop() {
    
        int intToReturn;
        
        intToReturn = items[top];
        top--;
        
        return intToReturn;
    }
    
    /**
     *  Pushes an integer onto the stack.
     *  @param number An integer to push onto the top of the stack.
     */
    public void push(int number) {
    
        top++;
        items[top] = number;
        return;
    }
    
}
