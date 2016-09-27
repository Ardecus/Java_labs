package Lab1;

import Lab1.Sorters.Sorter;

public class Main {
    public static void main(String[] args) {
        //SpeedTest.ShowSpeedTest();
        int[] array = InputOutputManager.ReadArray();
        array = MethodChooser.Sort(array);
        InputOutputManager.PrintArray(array);
    }
}
