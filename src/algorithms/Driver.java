package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class Driver
{
    private static StringBuilder report = new StringBuilder("Array Length");
    private static List<Consumer<int[]>> sortFunctions = new ArrayList<>();

    public static void main(String[] args) {
        int numTrials = 20;
        int trialLengthIncrement = 1;
        int initialLength = 1;
        int maxLength = 100;
        int maxValue = 1000000000;
        String outputFileName = initialLength + "sl" + maxLength + "ml" + maxValue + "mv" + ".csv";

        //Add sorting algorithms here
        //addSortingAlgorithmToTest(MergeSort::Sort, "Merge Sort Avg");
        //addSortingAlgorithmToTest(QuickSort::Sort, "Quicksort Avg");
        //addSortingAlgorithmToTest(CountingSort::Sort, "Counting Sort Avg");
        addSortingAlgorithmToTest(RadixSort::Sort, "Radix Sort Avg");
        //addSortingAlgorithmToTest(QRSort::Sort, "QR Sort Avg");

        //Append the report with new line
        report.append("\n");

        // Call all sorting algorithms with simple lists so there is no initial delay from the JIT compiler.
        for(Consumer<int[]> f : sortFunctions) f.accept(new int[]{1, 5, 2, 3, 6, 7, 8});

        long start;
        long[] timeArr = new long[sortFunctions.size()];

        for(int length = initialLength; length <= maxLength; length += trialLengthIncrement) {
            int[] arr;
            int[] copyArr;

            for(int i = 0; i < numTrials; ++i) {
                arr = generateRandomArray(maxValue, length);
                for(int j = 0; j < sortFunctions.size(); ++j) {
                    copyArr = arr.clone();
                    start = System.nanoTime();
                    sortFunctions.get(j).accept(copyArr);
                    timeArr[j] += System.nanoTime() - start;
                }
            }
            report.append(length);
            for (long l : timeArr) report.append(",").append((l * 1.0) / numTrials / 1000000);
            report.append("\n");

            Arrays.fill(timeArr, 0);
            System.out.println("Status: " + 100.0 * length / maxLength + "%");
        }
        System.out.println(report);
        System.out.println(maxValue);
        writeToFile(outputFileName, report.toString());
    }

    private static void addSortingAlgorithmToTest(Consumer<int[]> algorithm, String algorithmName) {
        report.append(",").append(algorithmName);
        sortFunctions.add(algorithm);
    }

    private static int[] generateRandomArray(int maxValue, int length) {
        int[] randomArr = new int[length];
        Random rand = new Random();
        for(int i = 0; i < randomArr.length; ++i)
            randomArr[i] = rand.nextInt(maxValue);
        randomArr[0] = maxValue; // Ensures the max value is in the array
        return randomArr;
    }

    public static boolean writeToFile(String fileName, String contents)
    {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(contents);
            myWriter.close();
            return true;
        } catch (IOException e) {
            System.err.println("File is likely open in another process. Cannot save!");
            return false;
        }
    }
}
