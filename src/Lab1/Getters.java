package Lab1;

import Lab1.Sorters.Sorter;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Getters {
    //returns an array of all methods with given annotation
    public static Method[] GetGenerators(Class annotation) {
        final Set<Method> methods = new HashSet<>();
        for (final Method method : SpeedTest.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methods.add(method);
            }
        }
        return methods.toArray(new Method[methods.size()]);
    }

    //returns an array of elements of all classes which implement Sorter interface
    public static Sorter[] GetSorters() {
        Reflections reflections = new Reflections("Lab1/Sorters");
        Set<Class<? extends Sorter>> classes = reflections.getSubTypesOf(Sorter.class);
        List<Sorter> res = new ArrayList<>();
        for (Class<? extends Sorter> sorterClass:classes) {
            try {
                res.add(sorterClass.newInstance());
            } catch (Exception e) { }
        }
        return res.toArray(new Sorter[res.size()]);
    }
}
