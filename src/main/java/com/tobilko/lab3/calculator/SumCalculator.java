package com.tobilko.lab3.calculator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class SumCalculator extends AbstractCalculator {

    public SumCalculator(int poolSize) {
        super(poolSize);
    }

    @Override
    public Runnable getPartialWork(int[] array, int startingIndex, int endingIndex, AtomicLong accumulator) {
        return calculateSumPartially(array, startingIndex, endingIndex, accumulator);
    }

    @Override
    public String mapAccumulatorToOutputString(AtomicLong accumulator) {
        return String.format("Sum = %d", accumulator.get());
    }

    private Runnable calculateSumPartially(int[] array, int startingIndex, int endingIndex, AtomicLong accumulator) {
        return () -> {
            for (int i = startingIndex; i < endingIndex; ++i) {
                accumulator.addAndGet(array[i]);
            }
        };
    }

}
