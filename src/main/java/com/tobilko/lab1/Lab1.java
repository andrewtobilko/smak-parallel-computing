package com.tobilko.lab1;

import com.tobilko.configuration.RandomGenerator;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.RunnerException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static com.tobilko.configuration.JMHConfiguration.startWithDefaultBenchmarkConfigurationByClass;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public class Lab1 {

    private static class LabData {

        public static final int VECTOR_SIZE = 100_000_000;
        public static final int THREAD_LIMIT = 10;
        public static final double[] VECTOR = RandomDoubleArrayGenerator.INSTANCE.generate();

    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1)
    @Warmup(iterations = 0)
    public void measureEuclideanNormCalculationWithSingleThread() {
        System.out.println(LabData.VECTOR);

        double sum = 0;
        for (double v : LabData.VECTOR) {
            sum += Math.pow(v, 2);
        }

        System.out.println("result* = " + Math.sqrt(sum));
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1)
    @Warmup(iterations = 0)
    public void measureEuclideanNormCalculationWithMultipleThreads() throws InterruptedException {
        PartialSumCalculator[] calculators = new PartialSumCalculator[LabData.THREAD_LIMIT];

        System.out.println(LabData.VECTOR);

        for (int i = 0; i < calculators.length; i++) {
            (calculators[i] = new PartialSumCalculator(
                    LabData.VECTOR,
                    calculateStartingIndexForI(i),
                    calculateEndingIndexForI(i))
            ).run();

            calculators[i].join();
        }

        double sum = 0;
        for (PartialSumCalculator calculator : calculators) {
            sum += calculator.getSum();
        }

        System.out.println("p.result = " + Math.sqrt(sum));

    }

    private int calculateStartingIndexForI(int i) {
        return LabData.VECTOR_SIZE / LabData.THREAD_LIMIT * i;
    }

    private int calculateEndingIndexForI(int i) {
        return LabData.VECTOR_SIZE / LabData.THREAD_LIMIT * (i + 1);
    }

    private static class RandomDoubleArrayGenerator implements RandomGenerator<double[]> {

        public static final RandomDoubleArrayGenerator INSTANCE = new RandomDoubleArrayGenerator(LabData.VECTOR_SIZE);

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

    private class PartialSumCalculator extends Thread {

        private final double[] vector;
        private final int startingIndex;
        private final int endingIndex;

        private double sum;

        public PartialSumCalculator(double[] vector, int startingIndex, int endingIndex) {
            this.vector = vector;
            this.startingIndex = startingIndex;
            this.endingIndex = endingIndex;
        }

        @Override
        public void run() {
            for (int i = startingIndex; i < endingIndex; ++i) {
                sum += vector[i];
            }
        }

        public double getSum() {
            return sum;
        }

    }

    public static void main(String[] args) throws RunnerException {
        startWithDefaultBenchmarkConfigurationByClass(Lab1.class);
    }

}
