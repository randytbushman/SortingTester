package algorithms;

/**
 * This class provides the user with Quotient Remainder sort methods.
 * @author Randolph Bushman
 * @version 5.19.22
 */
public class CountingSort {
    /**
     * Sorts the given int array with Counting Sort. This method possesses the time complexity O( n + k )
     * @param arr the int array to be sorted
     */
    public static void sort(int[] arr) {
        int i;
        int minValue = arr[0], maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] < minValue)
                minValue = arr[i];
            else if(arr[i] > maxValue)
                maxValue = arr[i];

        int[] shadowArr = new int[arr.length];
        int[] countingArr = new int[maxValue-minValue + 1];
        for(i = 0; i < arr.length; ++i)
            ++countingArr[arr[i] - minValue];
        for(i = 1; i < countingArr.length; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[arr[i] - minValue]] = arr[i];
        for(i = 0; i < shadowArr.length; ++i)
            arr[i] = shadowArr[i];
    }
}
