package com.tobilko.lab3.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import static com.tobilko.lab3.util.Lab3Util.MAX_STREAM_SIZE;

/**
 * Created by Andrew Tobilko on 11/9/17.
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractCalculator implements Callable<String> {

    private final int poolSize;
    private int[] array;

    @Override
    public final String call() throws Exception {
        // generate an array and calculate the portion
        array = generateIntRandomArray();
        final int portion = calculatePortion();

        // create a pool and an accumulator
        final ExecutorService service = Executors.newFixedThreadPool(getPoolSize());
        final AtomicLong accumulator = new AtomicLong();

        // submit tasks
        for (int i = 0; i < getPoolSize(); ++i) {
            final Runnable task = getPartialWork(array, calculateStartingPositionForI(i, portion), calculateEndingPositionForI(i, portion), accumulator);
            service.submit(task);
        }

        // init a shutdown and wait for tasks done
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);

        return mapAccumulatorToOutputString(accumulator);
    }

    public abstract Runnable getPartialWork(int[] array, int startingPosition, int endingPosition, AtomicLong accumulator);

    public abstract String mapAccumulatorToOutputString(AtomicLong accumulator);

    // init stuff
    private int[] generateIntRandomArray() {
        final Random random = new Random();

        return IntStream.generate(random::nextInt).limit(MAX_STREAM_SIZE).toArray();
    }

    private int calculatePortion() {
        return array.length / (getPoolSize() - 1);
    }

    // calculation positions
    private int calculateStartingPositionForI(int i, int portion) {
        return i * portion;
    }

    private int calculateEndingPositionForI(int i, int portion) {
        return (i + 1) * portion;
    }

}
