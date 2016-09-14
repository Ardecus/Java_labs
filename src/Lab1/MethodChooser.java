package Lab1;

import Lab1.Sorters.*;

public class MethodChooser {
    public static Sorter GetAppropriateMethod(int arrayLength) {
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
}
