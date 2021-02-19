package org.activevector.algos.sorting;

public class BubbleSort implements IntSorter {
    @Override
    public void sort(int[] arr) {
        for (int upToIndex = arr.length - 1; upToIndex > 0; upToIndex--) {
            boolean swapped = false;
            for (int i = 0; i < upToIndex; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}