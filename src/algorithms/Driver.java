package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

public class Driver
{
    private static final StringBuilder report = new StringBuilder("Array Length");
    private static final List<Consumer<int[]>> sortFunctions = new ArrayList<>();

    public static void main(String[] args) {
        int numTrials = 35;
        int trialLengthIncrement = 1000;
        int initialLength = 1000;
        int maxLength = 1000000;
        int minValue = 0;
        int maxValue = 100000000;
        String outputFileName = initialLength + "sl" + maxLength + "ml" + maxValue + "mv" + ".csv";

        //Add sorting algorithms here
        //addSortingAlgorithmToTest(MergeSort::Sort, "Merge Sort Avg");
        //addSortingAlgorithmToTest(QuickSort::Sort, "Quicksort Avg");
        //addSortingAlgorithmToTest(CountingSort::Sort, "Counting Sort Avg");
        //addSortingAlgorithmToTest(RadixSort::Sort, "Radix Sort Avg");
        addSortingAlgorithmToTest(arr -> QRSort.sort(arr, 128), "QR Sort Avg");
        addSortingAlgorithmToTest(arr -> QRSort.sortPower2(arr, 7), "QR P2 Sort Avg");
        addSortingAlgorithmToTest(arr -> QRSort.sortPower2MinValueZero(arr, 7), "QR P2 Zero Sort Avg");

        report.append("\n");
        System.out.println(report);

        // Call all sorting algorithms with simple lists so there is no initial delay from the JIT compiler.
        for(Consumer<int[]> f : sortFunctions) f.accept(new int[]{1, 5, 2, 3, 6, 7, 8});

        long start;
        long[] timeArr = new long[sortFunctions.size()];

        for(int length = initialLength; length < maxLength; length += trialLengthIncrement) {
            int[] arr;
            int[] copyArr;

            for(int i = 0; i < numTrials; ++i) {
                arr = shuffle(linSpace(length, minValue, maxValue));
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
        System.out.println(maxValue);
        writeToFile(outputFileName, report.toString());
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1])
                return false;
        }
        return true;
    }

    /**
     * Adds the given sorting algorithm to the tester and creates an entry for it in the report.
     * @param algorithm a sorting algorithm Consumer that takes in an int array
     * @param algorithmName the name of the algorithm we use in the report
     */
    private static void addSortingAlgorithmToTest(Consumer<int[]> algorithm, String algorithmName) {
        report.append(",").append(algorithmName);
        sortFunctions.add(algorithm);
    }

    /**
     * Returns an array with specified length with ascending linearly separated int values from min to max.
     * @param length the length of the array we generate
     * @param min the minimum value in the array we generate
     * @param max the maximum value in the array we generate
     * @return an array of specified length with ascending linearly separate int values
     */
    private static int[] linSpace(int length, int min, int max) {
        int[] arr = new int[length];
        arr[0] = min;
        arr[arr.length - 1] = max;
        for(int i = 1; i < arr.length - 1; ++i)
            arr[i] = (i * ((max - min) / length)) + arr[0];
        return arr;
    }

    /**
     * Shuffles the array such that each random permutation is equally likely to occur. Utilizes the Fisher–Yates
     * algorithm.
     * @param arr the int array to be shuffled
     * @return the shuffled array
     */
    private static int[] shuffle(int[] arr) {
        Random rand = new Random();
        for(int i = arr.length - 1; i > 0; --i) {
            int randomIndex = rand.nextInt(i + 1);
            int temp = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /**
     * Writes the given string to a file.
     * @param fileName the name of the file
     * @param contents the string contents of the file
     */
    public static void writeToFile(String fileName, String contents)
    {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(contents);
            myWriter.close();
        } catch (IOException e) {
            System.err.println("File is likely open in another process. Cannot save!");
        }
    }
}
