package org.activevector.algos.sorting;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntSorterTest {
    private static final Random RANDOM = new Random();
    private static int[] BASE;

    private int[] arr;

    @BeforeAll
    public static void initArrForAllTests() {
        BASE = new int[500 + RANDOM.nextInt(501)];
        for (int i = 0; i < BASE.length; i++) {
            BASE[i] = RANDOM.nextInt(501) - 250;
        }
        System.out.printf("Testing with an array of size %d: %s%n%n", BASE.length, Arrays.toString(BASE));
    }

    @BeforeEach
    public void setUp() {
        arr = new int[BASE.length];
        System.arraycopy(BASE, 0, arr, 0, BASE.length);
    }

    @Test
    public void testBubbleSort() {
        testWith(new BubbleSort());
    }

    @Test
    public void testInsertionSort() {
        testWith(new InsertionSort());
    }

    @Test
    public void testMergeSort() {
        testWith(new MergeSort());
    }

    @Test
    public void testQuickSort() {
        testWith(new QuickSort());
    }

    private <T extends IntSorter> void testWith(final T sorter) {
        System.out.println("Testing " + sorter.name());

        final long start = System.nanoTime();
        sorter.sort(arr);
        final long end = System.nanoTime();

        System.out.println("After sort: " + Arrays.toString(arr));

        for (int i = 0, j = 1; j < arr.length; i++, j++) {
            assertThat(arr[j], is(greaterThanOrEqualTo(arr[i])));
        }

        final long elapsedTimeInNanoSec = end - start;
        final double elapsedTimeInMicroSec = elapsedTimeInNanoSec / 1000.0;
        final double elapsedTimeInMillSec = elapsedTimeInMicroSec / 1000;

        System.out.printf("%s took %d ns / %.3f µs / %.6f ms%n%n",
                sorter.name(), elapsedTimeInNanoSec, elapsedTimeInMicroSec, elapsedTimeInMillSec);
    }

    private static Matcher<Integer> greaterThanOrEqualTo(final int valueToCompareWith) {
        return new CustomMatcher<>("an integer greater than or equal to " + valueToCompareWith) {
            @Override
            public boolean matches(final Object item) {
                return (item instanceof Integer) && ((Integer) item).compareTo(valueToCompareWith) >= 0;
            }
        };
    }
}
