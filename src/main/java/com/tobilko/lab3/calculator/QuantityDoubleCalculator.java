package com.tobilko.lab3.calculator;

import java.util.concurrent.Executor;
import java.util.function.Predicate;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class QuantityDoubleCalculator extends ExecutorAwareCalculator {

    private final Predicate<Double> condition;

    public QuantityDoubleCalculator(final Executor executor, final Predicate<Double> predicate) {
        super(executor);
        condition = predicate;
    }

    @Override
    public void run() {
        System.out.println("quantity");
    }

}
