public class NullPointerExceptionCatch
{
    public static void main(String[] args)
    {
        try
        {
            Integer num = null;
            int sum = num + 1;
        }
        catch(NullPointerException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}