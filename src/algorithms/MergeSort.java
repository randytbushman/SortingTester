package algorithms;

import java.util.List;

public class MergeSort {
    public static void Sort(int[] arr)
    {
        int mid = arr.length >> 1;
        int[] shadowArr = new int[arr.length];
        mergeSort(arr, shadowArr, 0, mid);
        mergeSort(arr, shadowArr, mid+1, arr.length-1);
        merge(arr, shadowArr, 0, mid, arr.length-1);
    }

    private static void mergeSort(int[] arr, int[] shadowArr, int s, int e)
    {
        int mid = (s + e) >> 1;
        if(s >= e)
            return;
        mergeSort(arr, shadowArr, s, mid);
        mergeSort(arr, shadowArr, mid+1, e);
        merge(arr, shadowArr, s, mid, e);
    }

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
