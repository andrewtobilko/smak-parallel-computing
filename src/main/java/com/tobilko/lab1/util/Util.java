package com.tobilko.lab1.util;

/**
 * Created by Andrew Tobilko on 10/11/17.
 */
public final class Util {

    public static final int ARRAY_SIZE = 100_000;
    public static final int LOAD_NUMBER = 10_000;

    public static final int RANDOM_NUMBER_ORIGIN = 0;
    public static final int RANDOM_NUMBER_BOUND = 1000;

    public static final int THREAD_LIMIT = 8;

    public static double calculateAccelerationFactor(double timeForSingleThread, double timeForMultipleThreads) {
        return timeForSingleThread / timeForMultipleThreads;
    }

    public static double calculateEfficiencyFactor(double accelerationFactor, int cores) {
        return accelerationFactor / cores;
    }

    public static int calculateStartingIndexForI(int i) {
        return ARRAY_SIZE / THREAD_LIMIT * i;
    }

    public static int calculateEndingIndexForI(int i) {
        return ARRAY_SIZE / THREAD_LIMIT * (i + 1);
    }

    public static double calculateEuclideanNormFromSum(double sum) {
        return Math.sqrt(sum);
    }

}
