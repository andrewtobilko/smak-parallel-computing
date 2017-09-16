package com.tobilko;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.util.Random;
import java.util.stream.DoubleStream;

import static com.tobilko.configuration.JMHConfiguration.startWithDefaultBenchmarkConfigurationByClass;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public class Lab1 {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 10)
    @Warmup(iterations = 0)
    public double calculate() {

        final int VECTOR_SIZE = 10_000_000;
        RandomDoubleArrayGenerator generator = new RandomDoubleArrayGenerator(VECTOR_SIZE);
        double[] vector = generator.generate();

        return Math.sqrt(DoubleStream.of(vector).map(v -> Math.pow(v, 2)).sum());
    }

    private interface RandomGenerator<T> {
        T generate();
    }

    private class RandomDoubleArrayGenerator implements RandomGenerator<double[]> {

        private final Random generator = new Random();

        private final long arraySize;

        public RandomDoubleArrayGenerator(long arraySize) {
            this.arraySize = arraySize;
        }

        @Override
        public double[] generate() {
            return generator.doubles(arraySize).toArray();
        }

    }

    public static void main(String[] args) throws RunnerException {
        startWithDefaultBenchmarkConfigurationByClass(Lab1.class);
    }

}