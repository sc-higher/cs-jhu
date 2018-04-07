public class IllegalArgumentExceptionThrown
{
    public static void main(String[] args)
    {
        try
        {
            throw new IllegalArgumentException();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}