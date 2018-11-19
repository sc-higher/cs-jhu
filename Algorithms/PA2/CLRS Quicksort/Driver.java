import java.util.Arrays;

public class Driver{

    public static void main(String[] args){

        // Integer[] array = {5,7,1,4,8,8,3};
        Integer[] array = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(array));

        Quicksort qs = new Quicksort();
        qs.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(qs.count);
        System.out.println(qs.ops);

    }

}