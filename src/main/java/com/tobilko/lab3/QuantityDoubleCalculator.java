package com.tobilko.lab3;

import lombok.RequiredArgsConstructor;

import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.DoubleStream;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@RequiredArgsConstructor
public final class QuantityDoubleCalculator implements Runnable {

    private final Predicate<Double> condition;

    @Override
    public void run() {
        final Random random = new Random();
        long count = DoubleStream.generate(random::nextDouble).parallel().filter(condition::test).count();

        System.out.println("quantity of the stream: " + count);
    }

}
