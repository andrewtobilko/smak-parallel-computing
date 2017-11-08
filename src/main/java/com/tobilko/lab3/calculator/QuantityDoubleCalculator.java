package com.tobilko.lab3.calculator;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@RequiredArgsConstructor
public final class QuantityDoubleCalculator implements Callable<String> {

    private final Predicate<Double> condition;

    @Override
    public String call() {
        return "quantity";
    }

}
