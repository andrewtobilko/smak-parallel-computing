package com.tobilko.lab1;

import com.tobilko.util.RandomGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public class Lab1 {

    private final int VECTOR_SIZE = 200_000_000;
    private final int THREAD_LIMIT = 4;

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
            return generator.doubles(VECTOR_SIZE, 0, 1000).toArray();
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

        long startingTime = System.currentTimeMillis();
        double r1 = lab.measureEuclideanNormCalculationWithSingleThread(vector);
        long t1 = System.currentTimeMillis() - startingTime;

        long startingTime1 = System.currentTimeMillis();
        double r2 = lab.measureEuclideanNormCalculationWithMultipleThreads(vector);
        long t2 = System.currentTimeMillis() - startingTime1;

        System.out.println("r1 = " + r1 + ", r2 = " + r2 + ", r2-r1 = " + (r2 - r1));
        System.out.println("t1 = " + t1 + ", t2 = " + t2 + ", t2-t1 = " + (t2 - t1));

        double accelerationFactor = calculateAccelerationFactor(t1, t2);
        System.out.println("the acceleration factor = " + accelerationFactor);

        int cores = Runtime.getRuntime().availableProcessors();
        double efficiencyFactor = calculateEfficiencyFactor(accelerationFactor, cores);
        System.out.println("the efficiency factor = " + efficiencyFactor);
        System.out.println("the numbers of cores = " + cores);
    }

    private static double calculateAccelerationFactor(double t1, double tN) {
        return t1 / tN;
    }

    private static double calculateEfficiencyFactor(double sN, int cores) {
        return sN / cores;
    }

}

class Lab2 {



}
