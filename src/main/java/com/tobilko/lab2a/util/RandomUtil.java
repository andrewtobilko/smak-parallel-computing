package com.tobilko.lab2a.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class RandomUtil {

    public static int getRandomId() {
        return getRandomIntBetween(0, 1000);
    }

    public static int getRandomTimeInSeconds() {
        return getRandomIntBetween(1, 11);
    }

    private static int getRandomIntBetween(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }

    private RandomUtil() {}

}