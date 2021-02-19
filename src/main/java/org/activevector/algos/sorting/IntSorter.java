package org.activevector.algos.sorting;

import java.util.Arrays;

@FunctionalInterface
public interface IntSorter {
    void sort(int[] arr);

    default String getArrayAsString(final int[] arr) {
        return Arrays.toString(arr);
    }

    default void swap(int[] arr, final int index1, final int index2) {
        if (index1 == index2) return;

        final int x = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = x;
    }

    default boolean recursionShortCircuit(int[] arr, final int fromIndex, final int toIndex) {
        if (fromIndex >= toIndex) return true;
        if (fromIndex == toIndex - 1) {
            if (arr[fromIndex] > arr[toIndex]) {
                swap(arr, fromIndex, toIndex);
            }
            return true;
        }
        return false;
    }

    default String name() {
        return this.getClass().getSimpleName();
    }
}
