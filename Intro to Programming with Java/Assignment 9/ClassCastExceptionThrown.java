public class ClassCastExceptionThrown
{
    public static void main(String[] args)
    {
        try
        {
            throw new ClassCastException();
        }
        catch(ClassCastException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}