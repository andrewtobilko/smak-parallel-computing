package com.tobilko.lab2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class RandomUtil {

    public static final int MIN_SECONDS_BETWEEN_GENERATIONS = 1;
    public static final int MAX_SECONDS_BETWEEN_GENERATIONS = 11;

    public static int getRandomId() {
        return getRandomIntBetween(100, 1000);
    }

    public static int getRandomTimeInSeconds() {
        return getRandomIntBetween(MIN_SECONDS_BETWEEN_GENERATIONS, MAX_SECONDS_BETWEEN_GENERATIONS);
    }

    private static int getRandomIntBetween(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }

    private RandomUtil() {}

}