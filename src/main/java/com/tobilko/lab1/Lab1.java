package com.tobilko.lab1;

import com.tobilko.util.RandomGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public class Lab1 {

    private final int VECTOR_SIZE = 100_000_00;
    private final int THREAD_LIMIT = 10;

    @SneakyThrows
    public double measureEuclideanNormCalculationWithSingleThread(double[] vector) {
        PartialResultCalculator calculator = new PartialResultCalculator(vector, 0, vector.length);

        calculator.run();
        calculator.join();

        return calculateEuclideanNormFromSum(calculator.getResult());
    }

    @SneakyThrows
    public double measureEuclideanNormCalculationWithMultipleThreads(double[] vector) {
        PartialResultCalculator[] calculators = new PartialResultCalculator[THREAD_LIMIT];

        // assign
        for (int i = 0; i < calculators.length; i++) {
            calculators[i] = new PartialResultCalculator(
                    vector,
                    calculateStartingIndexForI(i),
                    calculateEndingIndexForI(i)
            );
        }

        // run
        for (PartialResultCalculator calculator : calculators) {
            calculator.run();
        }

        // join
        for (PartialResultCalculator calculator : calculators) {
            calculator.join();
        }

        // get an overall sum
        double sum = Stream.of(calculators).mapToDouble(PartialResultCalculator::getResult).sum();

        return calculateEuclideanNormFromSum(sum);
    }

    private double calculateEuclideanNormFromSum(double sum) {
        return Math.sqrt(sum);
    }

    private int calculateStartingIndexForI(int i) {
        return VECTOR_SIZE / THREAD_LIMIT * i;
    }

    private int calculateEndingIndexForI(int i) {
        return VECTOR_SIZE / THREAD_LIMIT * (i + 1);
    }

    private class RandomDoubleArrayGenerator implements RandomGenerator<double[]> {

        private final Random generator = new Random();

        @Override
        public double[] generate() {
            return generator.doubles(VECTOR_SIZE).toArray();
        }

    }

    @RequiredArgsConstructor
    private class PartialResultCalculator extends Thread {

        private final double[] vector;
        private final int startingIndex;
        private final int endingIndex;

        @Getter
        private double result;

        @Override
        public void run() {
            for (int i = startingIndex; i < endingIndex; ) {
                result += vector[i++];
            }
        }

    }

    public static void main(String[] args) throws Exception {
        final Lab1 lab = new Lab1();
        final double[] vector = lab.new RandomDoubleArrayGenerator().generate();

        printResultsForMeasurement(() -> lab.measureEuclideanNormCalculationWithSingleThread(vector), System.currentTimeMillis());
        System.out.println();
        printResultsForMeasurement(() -> lab.measureEuclideanNormCalculationWithMultipleThreads(vector), System.currentTimeMillis());

        System.out.println();
        System.out.println("the numbers of cores = " + Runtime.getRuntime().availableProcessors());
    }

    private static void printResultsForMeasurement(Supplier<Double> measurement, long startingTimeMillis) {
        System.out.println("result = " + measurement.get());
        System.out.println("time = " + (System.currentTimeMillis() - startingTimeMillis));
    }

}
