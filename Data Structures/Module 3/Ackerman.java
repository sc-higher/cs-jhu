/* For Data Structures Assignment 3 */

public class Ackerman
{
    public static void main(String[] args)
    {
        System.out.println("Ackerman(2,2) = " + ack(2,2));
    }

    public static int ack(int m, int n)
    {
        if( m == 0 )
        {
            return n + 1;
        }
        else if( n == 0 )
        {
            return ack(m-1, 1);
        }
        else
        {
            return ack(m-1, ack(m, n-1));
        }
    }
}