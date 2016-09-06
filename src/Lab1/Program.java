package Lab1;

public class Program
{
    public static void main(String[] args)
    {
        //Type t = InputReader.GetType();
        int[] array = InputOutputManager.ReadArray();
        Sorter sorter = MethodChooser.GetAppropriateMethod(array.length);
        array = sorter.Sort(array);
        InputOutputManager.PrintArray(array);
    }
}
