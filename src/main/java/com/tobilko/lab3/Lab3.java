package com.tobilko.lab3;

import com.tobilko.lab3.calculator.ChecksumCalculator;
import com.tobilko.lab3.calculator.MinMaxCalculator;
import com.tobilko.lab3.calculator.QuantityDoubleCalculator;
import com.tobilko.lab3.calculator.SumDoubleCalculator;
import com.tobilko.lab3.stuff.Executable;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Lab3 {

    public static void main(String[] args) {

        final Predicate<Double> CONDITION = d -> d > 100;
        final Executor EXECUTOR = Executors.newSingleThreadExecutor();

        Arrays.asList(

                new SumDoubleCalculator(EXECUTOR),                          // sum
                new QuantityDoubleCalculator(EXECUTOR, CONDITION),          // quantity of elements by condition
                new MinMaxCalculator(EXECUTOR),                             // min and max with indices
                new ChecksumCalculator(EXECUTOR)                            // checksum using xor

        ).forEach(Executable::execute);

    }

}
