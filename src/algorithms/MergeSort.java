package algorithms;

/**
 * This class provides the user with Merge Sort methods.
 * @author Randolph Bushman
 * @version 5.19.22
 */
public class MergeSort {
    /**
     * Sorts the given int array with recursive Merge Sort. This method possesses the time complexity O( n * log(n) ).
     * @param arr the int array to be sorted
     */
    public static void sort(int[] arr)
    {
        int mid = arr.length >> 1;
        int[] shadowArr = new int[arr.length];
        mergeSort(arr, shadowArr, 0, mid);
        mergeSort(arr, shadowArr, mid+1, arr.length-1);
        merge(arr, shadowArr, 0, mid, arr.length-1);
    }

    /**
     * This helper method represents the recursive function that performs Merge Sort.
     * @param arr the int array to be sorted
     * @param shadowArr an int array of size len(arr) and the temporary storage location for the elements in arr
     * @param s the start index in arr where we perform Merge Sort
     * @param e the end index in arr where we perform Merge Sort
     */
    private static void mergeSort(int[] arr, int[] shadowArr, int s, int e)
    {
        int mid = (s + e) >> 1;
        if(s >= e)
            return;
        mergeSort(arr, shadowArr, s, mid);
        mergeSort(arr, shadowArr, mid+1, e);
        merge(arr, shadowArr, s, mid, e);
    }

    /**
     * This helper method merges two sorted contiguous sections of arr separated by mid.
     * @param arr the arr we perform the merge on
     * @param shadowArr the temporary storage for the array elements being merged
     * @param s the start index to the section the array we merge
     * @param mid the index that separates the first contiguous section to the next
     * @param e the end index to the section the array we merge
     */
    private static void merge(int[] arr, int[] shadowArr, int s, int mid, int e)
    {
        int i = s;
        int j = mid+1;
        int k = 0;
        while(i <= mid && j <= e)
            if(arr[i] < arr[j])
                shadowArr[k++] = arr[i++];
            else
                shadowArr[k++] = arr[j++];

        while(i <= mid)
            shadowArr[k++] = arr[i++];

        while(j <= e)
            shadowArr[k++] = arr[j++];

        for(int x = e; x >= s; --x)
            arr[x] = shadowArr[--k];
    }
}
