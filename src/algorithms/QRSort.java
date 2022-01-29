package algorithms;

import java.util.List;

public class QRSort {

    public static void Sort(int[] arr)
    {
        sort(arr, arr.length);
    }

    public static int[] sort(int[] arr, int maxRemainder)
    {
        int i;
        // Find the minimum and maximum values
        int minValue = arr[0], maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] < minValue)
                minValue = arr[i];
            else if(arr[i] > maxValue)
                maxValue = arr[i];
        int maxQuotient = ((maxValue - minValue) / maxRemainder) + 1;

        // Shadow array that is meant for temporary storage of all values
        int[] shadowArr = new int[arr.length];

        int[] countingArr = new int[Math.max(maxRemainder, maxQuotient)];

        // Perform Counting Sort, sorting based on remainder values
        for(i = 0; i < arr.length; ++i)
            ++countingArr[(arr[i] - minValue) % maxRemainder];
        for(i = 1; i < maxRemainder; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[(arr[i] - minValue) % maxRemainder]] = arr[i];

        if(maxQuotient > 1) {
            // Reset the values in order to reuse this counting array for sorting quotients
            for(i = 0; i < maxRemainder; ++i)
                countingArr[i] = 0;

            // Perform Counting Sort, sorting based on quotient values
            for(i = 0; i < arr.length; ++i)
                ++countingArr[(shadowArr[i] - minValue) / maxRemainder];
            for(i = 1; i < countingArr.length; ++i)
                countingArr[i] += countingArr[i-1];
            for(i = arr.length-1; i > -1; --i)
                arr[--countingArr[(shadowArr[i] - minValue) / maxRemainder]] = shadowArr[i];
        }
        else
            for(i = 0; i < arr.length; ++i)
                arr[i] = shadowArr[i];

        return arr;
    }
}
