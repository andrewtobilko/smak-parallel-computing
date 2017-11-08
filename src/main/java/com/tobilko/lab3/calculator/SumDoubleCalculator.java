package com.tobilko.lab3.calculator;

import java.util.concurrent.Executor;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class SumDoubleCalculator extends ExecutorAwareCalculator {

    public SumDoubleCalculator(final Executor executor) {
        super(executor);
    }

    @Override
    public void run() {
        System.out.println("SumDoubleCalculator");
    }

}
