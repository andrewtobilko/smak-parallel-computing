package com.tobilko.lab3.calculator;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public final class MinCalculator extends AbstractCalculator {

    public MinCalculator(final int poolSize) {
        super(poolSize);
    }

    @Override
    public Runnable getPartialWork(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return calculateMinElementIndex(array, startingPosition, endingPosition, accumulator);
    }

    @Override
    public String mapAccumulatorToOutputString(AtomicLong accumulator) {
        return format("Min = array[%d] = %d", accumulator.get(), getArray()[(int) accumulator.get()]);
    }

    private Runnable calculateMinElementIndex(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return () -> {
            for (int i = startingPosition; i < endingPosition; i++) {

                boolean isResultSet = false;
                int index;
                while (!isResultSet && array[i] < array[index = (int) accumulator.get()]) {
                    isResultSet = accumulator.compareAndSet(index, i);
                }
            }
        };
    }

}
