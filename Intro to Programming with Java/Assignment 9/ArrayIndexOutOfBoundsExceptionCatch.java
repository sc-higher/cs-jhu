public class ArrayIndexOutOfBoundsExceptionCatch
{
    public static void main(String[] args)
    {
        int[] array = new int[2];

        try
        {
            array[2] = 5;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println();
            e.printStackTrace();
            System.out.println();
        }
    }
}