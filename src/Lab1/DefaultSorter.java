package Lab1;

import java.util.Arrays;

public class DefaultSorter implements Sorter{
    public int[] Sort(int[] array) {
        Arrays.sort(array);
        return  array;
    }
}
