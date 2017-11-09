package com.tobilko.lab3.calculator;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.String.*;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public final class ChecksumCalculator extends AbstractCalculator {

    public ChecksumCalculator(final int poolSize) {
        super(poolSize);
    }

    @Override
    public Runnable getPartialWork(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return calculateChecksumPartially(array, startingPosition, endingPosition, accumulator);
    }

    @Override
    public String mapAccumulatorToOutputString(AtomicLong accumulator) {
        return format("Checksum = %d", accumulator.get());
    }

    private Runnable calculateChecksumPartially(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator) {
        return () -> {
            for (int i = startingPosition; i < endingPosition; ++i) {
                final int currentPosition = i;
                accumulator.getAndUpdate((v) -> v ^ array[currentPosition]);
            }
        };
    }

}
