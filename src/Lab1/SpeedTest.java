package Lab1;

import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class SpeedTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface LinearArray{}

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface PolynomialArray{}


    @LinearArray
    static int[] GetArraySorted(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = i;
        }
        return res;
    }

    @LinearArray
    static int[] GetArrayAdded(int arrayLength) {
        int[] res = new int[arrayLength];
        int i;
        for (i = 0; i < arrayLength; i++) {
            res[i] = arrayLength - i - 1;
        }
        res[i] = (int)Math.random() * arrayLength;
        return res;
    }

    @PolynomialArray
    static int[] GetArrayReverseSorted(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = arrayLength - i - 1;
        }
        return res;
    }

    @PolynomialArray
    static int[] GetArrayRandom(int arrayLength) {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            res[i] = (int)(Math.random() * arrayLength);
        }
        return res;
    }


    static Method[] GetGenerators(Class annotation) {
        final Set<Method> methods = new HashSet<>();
        for (final Method method : SpeedTest.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }
        return methods.toArray(new Method[methods.size()]);
    }

    static Sorter[] GetSorters() {
        Reflections reflections = new Reflections("Lab1");
        Set<Class<? extends Sorter>> classes = reflections.getSubTypesOf(Sorter.class);
        List<Sorter> res = new ArrayList<>();
        for (Class<? extends Sorter> sorterClass:classes) {
            try {
                res.add(sorterClass.newInstance());
            } catch (Exception e) { }
        }
        return res.toArray(new Sorter[res.size()]);
    }

    static void SpeedTestForGenerators (Method[] generators, Sorter[] sorters, int start, int end)
    {
        List<String> genNames = new ArrayList<>();
        genNames.add("Array length");
        for (Method generator:generators) {
            genNames.add(generator.getName());
        }

        for (Sorter sorter:sorters) {
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
                        long startTime = System.currentTimeMillis();
                        sorter.Sort(array);
                        long endTime = System.currentTimeMillis();
                        long totalTime = endTime - startTime;
                        res.add((int)totalTime);
                    }
                    InputOutputManager.PrintArray(res);
                }
            }
        }
    }

    public static void ShowSpeedTest() {
        Sorter[] sorters = GetSorters();
        Method[] lineGenerator = GetGenerators(LinearArray.class);
        Method[] polyGenerator = GetGenerators(PolynomialArray.class);

        SpeedTestForGenerators(lineGenerator, sorters, 6, 8);
        SpeedTestForGenerators(polyGenerator, sorters, 3, 5);
    }
}
