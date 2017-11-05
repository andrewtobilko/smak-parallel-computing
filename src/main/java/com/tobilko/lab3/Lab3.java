package com.tobilko.lab3;

import java.util.Arrays;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Lab3 {

    public static void main(String[] args) {

        Arrays.asList(

                new SumDoubleCalculator(),
                new QuantityDoubleCalculator(d -> d > 100)

        ).forEach(Runnable::run);

    }

}
