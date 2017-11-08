package com.tobilko.lab3.calculator;

import java.util.concurrent.Callable;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class SumDoubleCalculator implements Callable<String> {

    @Override
    public String call() {
        return "sum";
    }


}
