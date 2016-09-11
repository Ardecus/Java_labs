package Lab1;

import java.util.Arrays;
import java.util.List;

public class InputOutputManager {
    public static int[] ReadArray() {
        System.out.println("Enter array elements, divided by space:");
        String[] input = System.console().readLine().split(" ");
        int[] res = new int[input.length];
        try {
            for (int i = 0; i < input.length; i++) {
                res[i] = Integer.parseInt(input[i]);
            }
            return res;
        } catch (Exception ex) {
            System.out.println("An error occurred while reading an array");
            return new int[0];
        }
    }

    public static void PrintArray(int[] array) {
        if (array.length == 0) {
            System.out.println("Empty array");
        } else {
            String res = Arrays.toString(array);
            System.out.println(res.substring(1, res.length() - 1));
        }
    }

    public static void PrintArray(List list) {
        if (list.size() == 0) {
            System.out.println("Empty array");
        } else {
            String res = Arrays.toString(list.toArray());
            System.out.println(res.substring(1, res.length() - 1));
        }
    }
}
