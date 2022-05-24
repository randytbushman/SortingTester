package algorithms;

import java.util.Arrays;

/**
 * This class provides the user with RadixSort methods.
 * @author Randolph Bushman
 * @version 5.19.22
 */
public class RadixSort {
    /**
     * Performs RadixSort on the given int array with the radix set equal to the array length. This method possesses the
     * time complexity O( n * log_n(k) )
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
        maxValue -= minValue;

        int radix = arr.length;
        int[] countingArr = new int[radix];     // Array initializes to all zeros
        int[] shadowArr = new int[radix];

        boolean isNextRadix = maxValue > 0;
        for (int exp = 1; isNextRadix;) {
            for (i = 0; i < radix; ++i)
                countingArr[((arr[i] - minValue) / exp) % radix]++;
            for (i = 1; i < radix; ++i)
                countingArr[i] += countingArr[i - 1];
            for (i = radix - 1; i > -1; --i)
                shadowArr[--countingArr[((arr[i] - minValue) / exp) % radix]] = arr[i];
            for (i = 0; i < radix; ++i)
                arr[i] = shadowArr[i];
            if (maxValue / (exp *= radix) > 0)
                Arrays.fill(countingArr, 0);
            else
                isNextRadix = false;
        }
    }
}
