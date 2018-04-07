public class ClassCastExceptionCatch
{
    public static void main(String[] args)
    {
        try
        {
            Object s = "5";
            Integer p = (Integer)s * 5;
        }
        catch(ClassCastException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}