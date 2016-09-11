package Lab1;

public class MethodChooser {
    public static Sorter GetAppropriateMethod(int arrayLength) {
        return new BubbleSorter();
    }
}
