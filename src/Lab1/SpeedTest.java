package Lab1;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.lang.annotation.*;
import java.lang.reflect.Method;



public class SpeedTest
{
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    public @interface TestArray{}

    @TestArray
    static int[] GetArraySorted(int arrayLength)
    {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++)
        {
            res[i] = i;
        }
        return res;
    }

    @TestArray
    static int[] GetArrayAdded(int arrayLength)
    {
        int[] res = new int[arrayLength];
        int i;
        for (i = 0; i < arrayLength; i++)
        {
            res[i] = arrayLength - i - 1;
        }
        res[i] = (int)Math.random() * arrayLength;
        return res;
    }

    @TestArray
    static int[] GetArrayReverseSorted(int arrayLength)
    {
        int[] res = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++)
        {
            res[i] = arrayLength - i - 1;
        }
        return res;
    }

    @TestArray
    static int[] GetArrayRandom(int arrayLength)
    {
        {
            int[] res = new int[arrayLength];
            for (int i = 0; i < arrayLength; i++)
            {
                res[i] = (int)Math.random() * arrayLength;
            }
            return res;
        }
    }

    static Method[] GetArrayGenerators()
    {
        final Set<Method> methods = new HashSet<>();
        for (final Method method : SpeedTest.class.getDeclaredMethods())
        {
            if (method.isAnnotationPresent(TestArray.class))
            {
                methods.add(method.);
            }
        }
        return methods.toArray(new Method[methods.size()]);
    }

    static Sorter[] GetSorters()
    {
        Reflections reflections = new Reflections("Lab1");
        Set<Class<? extends Sorter>> classes = reflections.getSubTypesOf(Sorter.class);
        return classes.toArray(new Sorter[classes.size()]);
    }

    public static void ShowSpeedTest(int arrayLength)
    {
        Sorter[] sorters = GetSorters();
        Method[] generators = GetArrayGenerators();

        for (Sorter sorter:sorters)
        {
            for (int i = 2; i < 7; i++)
            {
                for (int j = 1; j < 10; j++)
                {
                    int len = j * (int)Math.pow(10, i);
                    List<Integer> res = new ArrayList<>();
                    res.add(len);
                    for (Method generator:generators)
                    {
                        int[] array = generator(len);//!
                        //start timer
                        array = sorter.Sort(array);
                        //stop timer
                        //res.add(time)
                    }
                    InputOutputManager.PrintArray(res.toArray(new int[res.size()]));//!
                }
            }
        }


    }
}
