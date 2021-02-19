package org.activevector.algos.sorting;

public class MergeSort implements IntSorter {
    @Override
    public void sort(int[] arr) {
        recursiveMergeSort(arr, 0, arr.length - 1);
    }

    private void recursiveMergeSort(int[] arr, final int fromIndex, final int toIndex) {
        if (recursionShortCircuit(arr, fromIndex, toIndex)) {
            return;
        }

        final int midIndex = (fromIndex + toIndex) / 2;

        recursiveMergeSort(arr, fromIndex, midIndex);
        recursiveMergeSort(arr, midIndex + 1, toIndex);

        merge(arr, fromIndex, midIndex, toIndex);
    }

    private void merge(int[] arr, int fromIndex, int midIndex, int toIndex) {
        int[] buffer = new int[toIndex - fromIndex + 1];
        for (int i = fromIndex, j = midIndex + 1, k = 0; k < buffer.length; k++) {
            if (i > midIndex) {
                buffer[k] = arr[j++];
                continue;
            }
            if (j > toIndex) {
                buffer[k] = arr[i++];
                continue;
            }

            if (arr[i] <= arr[j]) {
                buffer[k] = arr[i++];
            }
            else {
                buffer[k] = arr[j++];
            }
        }
        System.arraycopy(buffer, 0, arr, fromIndex, buffer.length);
    }
}
