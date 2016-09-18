package Lab1;

import Lab1.Sorters.*;

import java.util.ArrayList;
import java.util.List;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class SpeedTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface LinearArray{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface PolynomialArray{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface Array{}

    //{1, 2, 3 ... n}
    @LinearArray @Array
    static int[] GetArraySorted(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = i;
        }
        return res;
    }

    //{1, 2, 3 ... n - 1, random}
    @LinearArray @Array
    static int[] GetArrayAdded(int arrayLength) {
        int[] res = new int[arrayLength];
        int i;
        for (i = 0; i < arrayLength; i++) {
            res[i] = arrayLength - i - 1;
        }
        res[i] = (int)Math.random() * arrayLength;
        return res;
    }

    //{n, n - 1, n - 2 ... 1}
    @PolynomialArray @Array
    static int[] GetArrayReverseSorted(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = arrayLength - i - 1;
        }
        return res;
    }

    //{ all random }
    @PolynomialArray @Array
    static int[] GetArrayRandom(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = (int)(Math.random() * arrayLength);
        }
        return res;
    }

    static void SpeedTestForGenerators (Method[] generators, Sorter[] sorters, int start, int end)
    {
        //table head
        List<String> genNames = new ArrayList<>();
        genNames.add("ArrayLength");
        for (Method generator : generators) {
            genNames.add(generator.getName());
        }

        for (Sorter sorter : sorters) {
            System.out.println(sorter.getClass().getName() + ":");
            InputOutputManager.PrintArray(genNames);

            for (int i = start; i < end; i++) {
                for (int j = 1; j < 10; j++) {
                    if (j * (int) Math.pow(10, i) > Integer.MAX_VALUE) {
                        break;
                    }
                    int len = j * (int) Math.pow(10, i);
                    List<Integer> res = new ArrayList<>();
                    res.add(len);
                    for (Method generator : generators) {
                        Object[] params = {len};
                        int[] array = new int[len];
                        try {
                            array = (int[]) generator.invoke(null, params);
                        } catch (Exception e) { }
                        long startTime = System.currentTimeMillis(); //start timer
                        sorter.Sort(array);
                        long endTime = System.currentTimeMillis();   //stop timer
                        long totalTime = endTime - startTime;        //and get spent time in ms
                        res.add((int)totalTime);
                    }
                    InputOutputManager.PrintArray(res);
                }
            }
        }
    }

    public static void ShowSpeedTest() {
        Sorter[] sorters = new Sorter[] {new DrownSorter()};
        // splited in different arrays for adequate time testing
        // sorted arrays take 100 times more array elements to test
        Method[] lineGenerator = Getters.GetGenerators(LinearArray.class);
        Method[] polyGenerator = Getters.GetGenerators(PolynomialArray.class);

        SpeedTestForGenerators(lineGenerator, sorters, 6, 8);
        SpeedTestForGenerators(polyGenerator, sorters, 6, 8);
    }
}
