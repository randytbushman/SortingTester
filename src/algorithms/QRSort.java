package algorithms;

import java.util.List;

public class QRSort {

    public static void Sort(int[] arr)
    {
        sort(arr, arr.length);
    }

    public static int[] sort(int[] arr, int divisor)
    {
        int i;
        // Find the minimum and maximum values
        int minValue = arr[0], maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] < minValue)
                minValue = arr[i];
            else if(arr[i] > maxValue)
                maxValue = arr[i];

        int maxQuotient = ((maxValue - minValue) / divisor) + 1;

        // Shadow array that is meant for temporary storage for all values
        int[] shadowArr = new int[arr.length];
        int[] countingArr = new int[Math.max(divisor, maxQuotient)];

        // Perform Counting Sort with remainder values
        for(i = 0; i < arr.length; ++i)
            ++countingArr[(arr[i] - minValue) % divisor];
        for(i = 1; i < divisor; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[(arr[i] - minValue) % divisor]] = arr[i];

        if(maxQuotient > 1) {
            // Reset the values to reuse counting array for sorting quotients
            for(i = 0; i < divisor; ++i)
                countingArr[i] = 0;

            // Perform Counting Sort on quotient values
            for(i = 0; i < arr.length; ++i)
                ++countingArr[(shadowArr[i] - minValue) / divisor];
            for(i = 1; i < countingArr.length; ++i)
                countingArr[i] += countingArr[i-1];
            for(i = arr.length-1; i > -1; --i)
                arr[--countingArr[(shadowArr[i] - minValue) / divisor]] = shadowArr[i];
        }
        else
            for(i = 0; i < arr.length; ++i)
                arr[i] = shadowArr[i];

        return arr;
    }
}
