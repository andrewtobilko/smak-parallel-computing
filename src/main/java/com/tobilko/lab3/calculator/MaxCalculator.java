package com.tobilko.lab3.calculator;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.*;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public final class MaxCalculator extends AbstractCalculator {

    public MaxCalculator(final int poolSize) {
        super(poolSize);
    }

    @Override
    public Runnable getPartialWork(int[] array, int start, int end, AtomicLong accumulator) {
        return calculateMaxElementIndex(array, start, end, accumulator);
    }

    @Override
    public String mapAccumulatorToOutputString(AtomicLong accumulator) {
        return format("Max = array[%d] = %d", accumulator.get(), getArray()[(int) accumulator.get()]);
    }

    private Runnable calculateMaxElementIndex(int[] array, int start, int end, AtomicLong accumulator) {
        return () -> {
            for (int i = start; i < end; i++) {
                boolean isResultSet = false;
                int index;

                while (!isResultSet && array[i] > array[index = (int) accumulator.get()]) {
                    isResultSet = accumulator.compareAndSet(index, i);
                }
            }
        };
    }

}
