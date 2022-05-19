package algorithms;

/**
 * This class provides the user with Quotient Remainder sort methods.
 * @author Randolph Bushman
 * @version 5.19.22
 */
public class QRSort {
    /**
     * Sorts the given int array with QR Sort with the divisor set equal to the array length. This method possesses the
     * time complexity O( n + k/n )
     * @param arr the int array to be sorted
     */
    public static void sortDivisorN(int[] arr) {
        sort(arr, arr.length);
    }

    /**
     * Sorts the given int array with QR Sort with the divisor set equal to the array length. This method possesses the
     * time complexity O( n + d + k/d ).
     * @param arr the int array to be sorted
     * @param divisor the value we use to calculate the quotient and remainder values of each array element
     */
    public static void sort(int[] arr, int divisor)
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
    }

    /**
     * An optimized QR sort method that assumes the minimum value in the input int array is zero. This method possesses
     * the time complexity O( n + max_arr_value/n ). This method only works with positive int array values.
     * @param arr the positive int array to be sorted
     * @param divisor the value we use to calculate the quotient and remainder values of each array element
     */
    public static void sortMinValueZero(int[] arr, int divisor)
    {
        int i;
        // Find the minimum and maximum values
        int maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] > maxValue)
                maxValue = arr[i];

        int maxQuotient = (maxValue / divisor) + 1;

        // Shadow array that is meant for temporary storage for all values
        int[] shadowArr = new int[arr.length];
        int[] countingArr = new int[Math.max(divisor, maxQuotient)];

        // Perform Counting Sort with remainder values
        for(i = 0; i < arr.length; ++i)
            ++countingArr[arr[i] % divisor];
        for(i = 1; i < divisor; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[arr[i] % divisor]] = arr[i];

        if(maxQuotient > 1) {
            // Reset the values to reuse counting array for sorting quotients
            for(i = 0; i < divisor; ++i)
                countingArr[i] = 0;

            // Perform Counting Sort on quotient values
            for(i = 0; i < arr.length; ++i)
                ++countingArr[shadowArr[i]/ divisor];
            for(i = 1; i < countingArr.length; ++i)
                countingArr[i] += countingArr[i-1];
            for(i = arr.length-1; i > -1; --i)
                arr[--countingArr[shadowArr[i] / divisor]] = shadowArr[i];
        }
        else
            for(i = 0; i < arr.length; ++i)
                arr[i] = shadowArr[i];
    }

    /**
     * Sorts the given int array with QR Sort with the divisor set equal to a power of 2. This method utilizes bitwise
     * operations to calculate remainders and quotients which saves on computation time. This method possesses the time
     * complexity O( n + 2^p + k/(2^p) ).
     * @param arr the int array to be sorted
     * @param p the p-th value of 2 used as the divisor
     */
    public static void sortPower2(int[] arr, int p)
    {
        int i;
        int divisor = 1 << p;

        // Find the minimum and maximum values
        int minValue = arr[0], maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] < minValue)
                minValue = arr[i];
            else if(arr[i] > maxValue)
                maxValue = arr[i];

        int maxQuotient = ((maxValue - minValue) >> p) + 1;

        // Shadow array that is meant for temporary storage for all values
        int[] shadowArr = new int[arr.length];
        int[] countingArr = new int[Math.max(divisor, maxQuotient)];

        // Perform Counting Sort with remainder values
        for(i = 0; i < arr.length; ++i)
            ++countingArr[(arr[i] - minValue) & (divisor-1)];
        for(i = 1; i < divisor; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[(arr[i] - minValue) & (divisor-1)]] = arr[i];

        if(maxQuotient > 1) {
            // Reset the values to reuse counting array for sorting quotients
            for(i = 0; i < divisor; ++i)
                countingArr[i] = 0;

            // Perform Counting Sort on quotient values
            for(i = 0; i < arr.length; ++i)
                ++countingArr[(shadowArr[i] - minValue) >> p];
            for(i = 1; i < countingArr.length; ++i)
                countingArr[i] += countingArr[i-1];
            for(i = arr.length-1; i > -1; --i)
                arr[--countingArr[(shadowArr[i] - minValue) >> p]] = shadowArr[i];
        }
        else
            for(i = 0; i < arr.length; ++i)
                arr[i] = shadowArr[i];
    }

    /**
     * Sorts the given int array with QR Sort with the divisor set equal to a power of 2. This method utilizes bitwise
     * operations to calculate remainders and quotients which saves on computation time. This method also assumes the
     * minimum value in the input int array is zero. This method possesses the time complexity
     * O( n + 2^p + max_arr_value/(2^p) ). This method only works with positive int array values.
     * @param arr the positive int array to be sorted
     * @param p the p-th value of 2 used as the divisor
     */
    public static void sortPower2MinValueZero(int[] arr, int p)
    {
        int i;
        int divisor = 1 << p;

        // Find the minimum and maximum values
        int maxValue = arr[0];
        for(i = 1; i < arr.length; ++i)
            if(arr[i] > maxValue)
                maxValue = arr[i];

        int maxQuotient = (maxValue >> p) + 1;

        // Shadow array that is meant for temporary storage for all values
        int[] shadowArr = new int[arr.length];
        int[] countingArr = new int[Math.max(divisor, maxQuotient)];

        // Perform Counting Sort with remainder values
        for(i = 0; i < arr.length; ++i)
            ++countingArr[arr[i] & (divisor-1)];
        for(i = 1; i < divisor; ++i)
            countingArr[i] += countingArr[i-1];
        for(i = arr.length-1; i > -1; --i)
            shadowArr[--countingArr[arr[i] & (divisor-1)]] = arr[i];

        if(maxQuotient > 1) {
            // Reset the values to reuse counting array for sorting quotients
            for(i = 0; i < divisor; ++i)
                countingArr[i] = 0;
            // Perform Counting Sort on quotient values
            for(i = 0; i < arr.length; ++i)
                ++countingArr[shadowArr[i] >> p];
            for(i = 1; i < countingArr.length; ++i)
                countingArr[i] += countingArr[i-1];
            for(i = arr.length-1; i > -1; --i)
                arr[--countingArr[shadowArr[i] >> p]] = shadowArr[i];
        }
        else
            for(i = 0; i < arr.length; ++i)
                arr[i] = shadowArr[i];
    }
}
