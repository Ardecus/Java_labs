package Lab1;

import Lab1.Sorters.*;

public class MethodChooser {
    public static Sorter GetAppropriateMethod(int arrayLength) {
        return new DefaultSorter();
    }
}
