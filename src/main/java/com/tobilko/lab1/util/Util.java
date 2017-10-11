package com.tobilko.lab1.util;

/**
 * Created by Andrew Tobilko on 10/11/17.
 */
public final class Util {

    public static final int ARRAY_SIZE = 10_000_000;
    public static final int RANDOM_NUMBER_ORIGIN = 1;
    public static final int RANDOM_NUMBER_BOUND = 1;

    public static final int THREAD_LIMIT = 10;

    public static double calculateAccelerationFactor(double timeForSingleThread, double timeForMultipleThreads) {
        return timeForSingleThread / timeForMultipleThreads;
    }

    public static double calculateEfficiencyFactor(double accelerationFactor, int cores) {
        return accelerationFactor / cores;
    }

    public static double calculateEuclideanNormFromSum(double sum) {
        return Math.sqrt(sum);
    }

}
