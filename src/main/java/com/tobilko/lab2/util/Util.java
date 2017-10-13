package com.tobilko.lab2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Util {

    private static final int MIN_SECONDS = 1;
    private static final int MAX_SECONDS = 11;

    public static long generateRandomId() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }

    public static int generateRandomTimeInSeconds() {
        return ThreadLocalRandom.current().nextInt(MIN_SECONDS, MAX_SECONDS);
    }

}
