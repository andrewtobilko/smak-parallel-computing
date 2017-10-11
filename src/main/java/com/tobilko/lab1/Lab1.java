package com.tobilko.lab1;

import com.tobilko.lab1.calculator.PartialResultCalculator;
import com.tobilko.lab1.generator.RandomDoubleArrayGenerator;
import lombok.SneakyThrows;

import java.util.stream.Stream;

import static com.tobilko.lab1.util.Util.*;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public final class Lab1 {

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

    private int calculateStartingIndexForI(int i) {
        return VECTOR_SIZE / THREAD_LIMIT * i;
    }

    private int calculateEndingIndexForI(int i) {
        return VECTOR_SIZE / THREAD_LIMIT * (i + 1);
    }

    public static void main(String[] args) throws Exception {
        final Lab1 lab = new Lab1();
        final double[] vector = new RandomDoubleArrayGenerator().generate();

        long startingTime = System.currentTimeMillis();
        double result = lab.measureEuclideanNormCalculationWithSingleThread(vector);
        long finalTimeForSingleThread = System.currentTimeMillis() - startingTime;

        System.out.println("Results for a single thread: " + result + ", " + finalTimeForSingleThread);

        startingTime = System.currentTimeMillis();
        result = lab.measureEuclideanNormCalculationWithMultipleThreads(vector);
        long finalTimeForMultipleThreads = System.currentTimeMillis() - startingTime;

        System.out.println("Results for multiple threads: " + result + ", " + finalTimeForMultipleThreads);

        double accelerationFactor = calculateAccelerationFactor(finalTimeForSingleThread, finalTimeForMultipleThreads);
        System.out.println("The acceleration factor: " + accelerationFactor);

        int cores = Runtime.getRuntime().availableProcessors();
        double efficiencyFactor = calculateEfficiencyFactor(accelerationFactor, cores);
        System.out.println("The efficiency factor: " + efficiencyFactor);
        System.out.println("The numbers of cores: " + cores);
    }

}
