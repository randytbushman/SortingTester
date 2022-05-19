package algorithms;

/**
 * This class provides the user with QuickSort methods.
 * @author Randolph Bushman
 * @version 5.19.22
 */public class QuickSort {

    /**
     * Sorts the given int array with QuickSort. This method possesses the lower-bound time complexity O( n * log(n) )
     * and upper-bound time complexity O( n^2 ).
     * @param arr the int array to be sorted
     */
    public static void sort(int[] arr)
    {
        int p = partition(arr, 0, arr.length-1);
        quicksort(arr, 0, p-1);
        quicksort(arr, p+1, arr.length-1);
    }

    /**
     * This helper method represents the recursive function that performs Merge Sort.
     * @param arr the int array to be sorted
     * @param s the start index in arr where we perform Merge Sort
     * @param e the end index in arr where we perform Merge Sort
     */
    private static void quicksort(int[] arr, int s, int e)
    {
        if(e-s < 1)
            return;
        int p = partition(arr, s, e);
        quicksort(arr, s, p-1);
        quicksort(arr, p+1, e);
    }

    /**
     * This helper method partitions the array based on the first value in the array section. All lesser values shall
     * move to the left of the partition value and all greater than or equal values shall move to the right.
     * @param arr the array to be partitioned
     * @param s the start index in arr where we perform Merge Sort
     * @param e the end index in arr where we perform Merge Sort
     * @return the index of the partitioned value
     */
    private static int partition(int[] arr, int s, int e)
    {
        int x = arr[e];
        int i = s - 1;
        for(int j = s; j < e; ++j)
            if(arr[j] < x)
                swap(arr, ++i, j);
        swap(arr, i + 1, e);
        return i + 1;
    }

    /**
     * Swqps the value at index j with the value at index i in arr.
     * @param arr the array we perform swap on
     * @param i the index location of the value to be swapped
     * @param j the index location of the value to be swapped
     */
    private static void swap(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
