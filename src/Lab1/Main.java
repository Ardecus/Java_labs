package Lab1;

public class Main {
    public static void main(String[] args) {
        SpeedTest.ShowSpeedTest();
        int[] array = InputOutputManager.ReadArray();
        Sorter sorter = MethodChooser.GetAppropriateMethod(array.length);
        sorter.Sort(array);
        InputOutputManager.PrintArray(array);
    }
}
