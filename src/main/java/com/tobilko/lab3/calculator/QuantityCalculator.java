package com.tobilko.lab3.calculator;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class QuantityCalculator extends AbstractCalculator {

    private final Predicate<Integer> condition;

    public QuantityCalculator(int poolSize, Predicate<Integer> condition) {
        super(poolSize);
        this.condition = condition;
    }

    @Override
    public Runnable getPartialWork(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return calculateCountPartially(array, startingPosition, endingPosition, accumulator);
    }

    @Override
    public String mapAccumulatorToOutputString(AtomicLong accumulator) {
        return String.format("Quantity by the condition = %d", accumulator.get());
    }

    private Runnable calculateCountPartially(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return () -> {
            for (int i = startingPosition; i < endingPosition; ++i) {
                if (condition.test(array[i])) {
                    accumulator.getAndIncrement();
                }
            }
        };
    }

}
