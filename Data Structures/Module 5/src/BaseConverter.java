/*
 *  $Id:  BaseConverter.java $
 *
 *  Copyright 2011, The Johns Hopkins University Whiting School of Engineering
 *      All rights reserved.
 *      This material may be used, modified and reproduced by faculty,
 *      staff, and students of The Johns Hopkins University for instruction, 
 *      evaluation, and grading purposes.  For any other permission, please 
 *      contact The Johns Hopkins University Whiting School of Engineering.
 */

/**
 *  Converts numbers from one base to another.
 *  @version    1.0
 *  @author     W.T. Door
 */
public class BaseConverter {

    //  The maximum number of digits allowed for the conversion result.
    private int maxDigits;

    /**
     *  Basic constructor.
     *  @param numDigits Maximum digits in the conversion result.
     */
    public BaseConverter(int numDigits) {
        maxDigits = numDigits;
    }
    
    /**
     *  Convert decimal numbers to binary numbers.
     *  @param decNum The decimal number to convert.
     *  @return The bit pattern of the 32-bit binary number.
     */
    public String decToBin(long decNum) {
    
        long    nextNum;
        int     remainder;
        int     totalBits;
        
        IntegerStack    stack;
        StringBuilder   binaryBuffer;
        
        stack = new IntegerStack(maxDigits);
        
        nextNum = decNum;
        totalBits = 0;
        while (nextNum > 0) {
            remainder = (int)(nextNum % 2);
            stack.push(remainder);
            totalBits++;
            nextNum /= 2;
        }
        
        binaryBuffer = new StringBuilder(39);
        for (int i = totalBits; i > 0; i--)
            binaryBuffer.append(stack.pop());
        
        return binaryBuffer.toString();
    }

}
