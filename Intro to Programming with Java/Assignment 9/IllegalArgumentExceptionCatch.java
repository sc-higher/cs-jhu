public class IllegalArgumentExceptionCatch
{
    public static void main(String[] args)
    {
        try
        {
            Character.toChars(-1);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}