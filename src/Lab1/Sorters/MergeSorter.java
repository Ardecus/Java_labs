package Lab1.Sorters;

import java.util.Arrays;

public class MergeSorter implements Sorter {
    //recursively split an array into two halves and sort them
    public int[] Sort(int[] array)
    {
        if (array.length <= 1) {
            return array;
        }

        int mid = array.length / 2;
        return Merge(Sort(Arrays.copyOfRange(array, 0, mid)),
                     Sort(Arrays.copyOfRange(array, mid, array.length)));
    }


    //Merging two arrays in order:
    //e. g. {1, 3, 5} and {2, 4} after merging returns {1, 2, 3, 4, 5}
    static int[] Merge(int[] startArr, int[] endArr)
    {
        int si = 0,
            ei = 0;
        int[] merged = new int[startArr.length + endArr.length];
        for (int i = 0; i < startArr.length + endArr.length; i++)
        {
            if (ei < endArr.length && si < startArr.length) {
                merged[i] = startArr[si] > endArr[ei] ? endArr[ei++] : startArr[si++];
            } else {
                merged[i] = ei < endArr.length ? endArr[ei++] : startArr[si++];
            }
        }
        return merged;
    }

    public int Complexity(int length) {
        return (int)(length * Math.log(length));
    }
}
