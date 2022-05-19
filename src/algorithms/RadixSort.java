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
        int exp = 0;
        for(i = 1; i < arr.length; ++i)
            if(arr[i] < minValue)
                minValue = arr[i];
            else if(arr[i] > maxValue)
                maxValue = arr[i];

        int radix = arr.length;
        int[] countingArr = new int[radix];
        int[] shadowArr = new int[radix];

        maxValue -= minValue;

        for (exp = 1; maxValue / exp > 0; exp *= radix) {
            Arrays.fill(countingArr, 0);
            for (i = 0; i < radix; ++i)
                countingArr[((arr[i] - minValue) / exp) % radix]++;

            for (i = 1; i < radix; ++i)
                countingArr[i] += countingArr[i - 1];

            for (i = radix - 1; i > -1; --i)
                shadowArr[--countingArr[((arr[i] - minValue) / exp) % radix]] = arr[i];

            exp *= radix;
            if (maxValue / exp > 0) {
                Arrays.fill(countingArr, 0);
                for (i = 0; i < radix; ++i)
                    countingArr[((shadowArr[i] - minValue) / exp) % radix]++;

                for (i = 1; i < radix; ++i)
                    countingArr[i] += countingArr[i - 1];

                for (i = radix - 1; i > -1; --i)
                    arr[--countingArr[((shadowArr[i] - minValue) / exp) % radix]] = shadowArr[i];
            } else
                for (i = 0; i < radix; ++i)
                    arr[i] = shadowArr[i];

        }
    }
}
