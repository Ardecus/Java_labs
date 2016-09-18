package Lab1.Sorters;

public interface Sorter {
    //Sorts given array and returns it
    int[] Sort(int[] array);

    //Returns estimated amount of operations needed depending on array length
    int Complexity(int length);
}