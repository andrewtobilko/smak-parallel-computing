package com.tobilko.lab2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Util {

    public static final int MIN_SECONDS = 1;
    public static final int MAX_SECONDS = 11;

    public static int generateRandomId() {
        return generateRandomIntBetween(100, 1000);
    }

    public static int generateRandomTimeInSeconds() {
        return generateRandomIntBetween(MIN_SECONDS, MAX_SECONDS);
    }

    private static int generateRandomIntBetween(int lower, int upper) {
        return ThreadLocalRandom.current().nextInt(lower, upper);
    }

}
