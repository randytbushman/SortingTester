package algorithms;

import java.util.Arrays;

public class RadixSort {
    public static void Sort(int[] arr) {
        int i;

        int minValue = arr[0], maxValue = arr[0];
        long exp = 0;
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
                countingArr[(int) ((arr[i] - minValue) / exp) % radix]++;

            for (i = 1; i < radix; ++i)
                countingArr[i] += countingArr[i - 1];

            for (i = radix - 1; i > -1; --i)
                shadowArr[--countingArr[(int) ((arr[i] - minValue) / exp) % radix]] = arr[i];

            exp *= radix;
            if (maxValue / exp > 0) {
                Arrays.fill(countingArr, 0);
                for (i = 0; i < radix; ++i)
                    countingArr[(int) ((shadowArr[i] - minValue) / exp) % radix]++;

                for (i = 1; i < radix; ++i)
                    countingArr[i] += countingArr[i - 1];

                for (i = radix - 1; i > -1; --i)
                    arr[--countingArr[(int) ((shadowArr[i] - minValue) / exp) % radix]] = shadowArr[i];
            } else
                for (i = 0; i < radix; ++i)
                    arr[i] = shadowArr[i];

        }


    }
}
