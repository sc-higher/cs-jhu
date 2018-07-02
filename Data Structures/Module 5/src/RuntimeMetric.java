/*
 *  $Id:  RuntimeMetric.java $
 *
 *  Copyright 2011, The Johns Hopkins University Whiting School of Engineering
 *      All rights reserved.
 *      This material may be used, modified and reproduced by faculty,
 *      staff, and students of The Johns Hopkins University for instruction, 
 *      evaluation, and grading purposes.  For any other permission, please 
 *      contact The Johns Hopkins University Whiting School of Engineering.
 */

/**
 *  Provides a matched set to determine Big-O runtime metrics.
 *  @version    1.0     2011-05-08
 *  @author     W.T. Door
 */
public class RuntimeMetric {

    private long size;
    private long runtime;
    
    /**
     *  A single constructor is used to create each metric.  The metric
     *  cannot be changed after creation.
     *  @param n The size of the problem.
     *  @param t The time it took to solve the problem.
     */
    public RuntimeMetric (long n, long t) {
    
        size = n;
        runtime = t;
    }

    /**
     *  Fetches the time it took to solve the problem.
     *  @return The time measured in nanoseconds.
     */
    public long getRuntime() {
        return runtime;
    }
    
    /**
     *  Fetches the size of the problem.
     *  @return A size that is determined by the way the problem is stated.
     */
    public long getSize() {
        return size;
    }

}
