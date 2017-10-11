package com.tobilko.lab1.generator;

import java.util.Random;

import static com.tobilko.lab1.util.Util.VECTOR_SIZE;

/**
 * Created by Andrew Tobilko on 10/11/17.
 */
public final class RandomDoubleArrayGenerator implements RandomGenerator<double[]> {

    private final Random generator = new Random();

    @Override
    public double[] generate() {
        return generator.doubles(VECTOR_SIZE, 0, 1000).toArray();
    }

}