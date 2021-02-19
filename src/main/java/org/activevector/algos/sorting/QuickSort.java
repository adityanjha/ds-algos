package org.activevector.algos.sorting;

public class QuickSort implements IntSorter {
    @Override
    public void sort(int[] arr) {
        recursiveQuickSort(arr, 0, arr.length - 1);
    }

    private void recursiveQuickSort(int[] arr, final int fromIndex, final int toIndex) {
        if (recursionShortCircuit(arr, fromIndex, toIndex)) return;

        int i = fromIndex - 1;
        for (int j = fromIndex; j < toIndex; j++) {
            if (arr[j] <= arr[toIndex]) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, toIndex);

        if (i > 0) recursiveQuickSort(arr, 0, i);

        if (i + 2 < toIndex) recursiveQuickSort(arr, i + 2, toIndex);
    }
}