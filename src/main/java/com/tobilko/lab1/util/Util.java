package com.tobilko.lab1.util;

/**
 * Created by Andrew Tobilko on 10/11/17.
 */
public final class Util {

    public static final int VECTOR_SIZE = 200_000_000;
    public static final int THREAD_LIMIT = 4;

    public static double calculateAccelerationFactor(double t1, double tN) {
        return t1 / tN;
    }

    public static double calculateEfficiencyFactor(double sN, int cores) {
        return sN / cores;
    }

    public static double calculateEuclideanNormFromSum(double sum) {
        return Math.sqrt(sum);
    }

}
