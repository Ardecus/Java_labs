package Lab1;

import Lab1.Sorters.*;

public class MethodChooser {
    //from all sorters implementations searches the one that is the fastest one for given length
    //...
    //default sorting always wins.
    private static Sorter GetAppropriateMethod(int arrayLength) {
        Sorter[] sorters = Getters.GetSorters();
        int min = Integer.MIN_VALUE;
        Sorter res = new DefaultSorter();
        for (Sorter sorter : sorters) {
            int compl = sorter.Complexity(arrayLength);
            if (compl < min) {
                min = compl;
                res = sorter;
            }
        }
        return res;
    }

    //calling choosen sorter
    public static int[] Sort(int[] array)
    {
        return GetAppropriateMethod(array.length).Sort(array);
    }
}
