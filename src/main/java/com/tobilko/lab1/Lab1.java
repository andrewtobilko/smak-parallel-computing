package com.tobilko.lab1;

import com.tobilko.lab1.calculator.PartialResultCalculator;
import com.tobilko.lab1.generator.RandomDoubleArrayGenerator;
import lombok.SneakyThrows;

import static com.tobilko.lab1.util.Util.*;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public final class Lab1 {

    @SneakyThrows
    public double measureEuclideanNormCalculationWithSingleThread(double[] array) {
        PartialResultCalculator calculator = new PartialResultCalculator(array, 0, array.length);

        calculator.run();

        return calculateEuclideanNormFromSum(calculator.getResult());
    }

    @SneakyThrows
    public double measureEuclideanNormCalculationWithMultipleThreads(double[] array) {
        PartialResultCalculator[] calculators = new PartialResultCalculator[THREAD_LIMIT];

        // assign
        for (int i = 0; i < calculators.length; i++) {
            calculators[i] = new PartialResultCalculator(
                    array,
                    calculateStartingIndexForI(i),
                    calculateEndingIndexForI(i)
            );
        }

        // start
        for (PartialResultCalculator calculator : calculators) {
            calculator.start();
        }

        // join
        for (PartialResultCalculator calculator : calculators) {
            calculator.join();
        }

        // get an overall sum
        double sum = 0;

        for (PartialResultCalculator calculator : calculators) {
            sum += calculator.getResult();
        }

        return calculateEuclideanNormFromSum(sum);
    }

    public static void main(String[] args) {
        final Lab1 lab = new Lab1();
        final double[] array = new RandomDoubleArrayGenerator().generate();

        // with a single thread
        long startingTime = System.currentTimeMillis();
        double result = lab.measureEuclideanNormCalculationWithSingleThread(array);
        long finalTimeForSingleThread = System.currentTimeMillis() - startingTime;
        System.out.printf("\nThe result for a single thread: %f, %dms\n", result, finalTimeForSingleThread);

        // with multiple threads
        startingTime = System.currentTimeMillis();
        result = lab.measureEuclideanNormCalculationWithMultipleThreads(array);
        long finalTimeForMultipleThreads = System.currentTimeMillis() - startingTime;
        System.out.printf("The result for multiple threads: %f, %dms\n", result, finalTimeForMultipleThreads);

        double accelerationFactor = calculateAccelerationFactor(finalTimeForSingleThread, finalTimeForMultipleThreads);
        System.out.println("\nThe acceleration factor: " + accelerationFactor);

        int processingThreads = Runtime.getRuntime().availableProcessors();
        double efficiencyFactor = calculateEfficiencyFactor(accelerationFactor, processingThreads);
        System.out.println("The efficiency factor: " + efficiencyFactor);
        System.out.println("The numbers of cores: " + processingThreads / 2);
        System.out.println("The numbers of processing threads: " + processingThreads);
    }

}
