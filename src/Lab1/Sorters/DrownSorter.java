package Lab1.Sorters;

public class DrownSorter implements Sorter {
    public int[] Sort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean swapped = false;
            for (int j = array.length - 1; j > array.length - i - 1; j--) {
                if (array[j] < (array[j - 1])) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                    swapped = true;
                }
            }

            if(!swapped) {
                break;
            }
        }
        return array;
    }
}