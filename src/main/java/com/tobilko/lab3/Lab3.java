package com.tobilko.lab3;

import com.tobilko.lab3.calculator.ChecksumCalculator;
import com.tobilko.lab3.calculator.MinMaxCalculator;
import com.tobilko.lab3.calculator.QuantityDoubleCalculator;
import com.tobilko.lab3.calculator.SumDoubleCalculator;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Lab3 {

    public static void main(String[] args) {

        final Predicate<Double> CONDITION = d -> d > 100;

        Arrays.asList(

                new SumDoubleCalculator(),                      // sum
                new QuantityDoubleCalculator(CONDITION),        // quantity of elements by condition
                new MinMaxCalculator(),                         // min and max with indices
                new ChecksumCalculator()                        // checksum using xor

        ).forEach(Runnable::run);

    }

}
