package Lab1.Sorters;

import java.util.Arrays;

public class DefaultSorter implements Sorter{
    //Simply is the best
    public int[] Sort(int[] array) {
        Arrays.sort(array);
        return  array;
    }

    public int Complexity(int length) {
        return length;
    }
}
