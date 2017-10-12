package com.tobilko.lab2.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Util {

    public static long generateRandomId() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }

    public static int generateRandomProcessorWorkTime() {
        return ThreadLocalRandom.current().nextInt(1, 11);
    }

}
