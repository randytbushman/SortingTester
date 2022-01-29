package algorithms;

import java.util.List;

public class QuickSort {

    public static void Sort(int[] arr)
    {
        int p = partition(arr, 0, arr.length-1);
        quicksort(arr, 0, p-1);
        quicksort(arr, p+1, arr.length-1);
    }

    private static void quicksort(int[] arr, int start, int end)
    {
        if(end-start < 1)
            return;
        int p = partition(arr, start, end);
        quicksort(arr, start, p-1);
        quicksort(arr, p+1, end);
    }

    private static int partition(int[] arr, int start, int end)
    {
        int x = arr[end];
        int i = start - 1;
        for(int j = start; j < end; ++j)
            if(arr[j] < x)
                swap(arr, ++i, j);
        swap(arr, i + 1, end);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j)
    {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
