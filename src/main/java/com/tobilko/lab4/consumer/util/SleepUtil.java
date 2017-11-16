package com.tobilko.lab4.consumer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SleepUtil {

    public static void sleepRandomSeconds(Runnable failureRunnable) {
        try {
            TimeUnit.SECONDS.sleep(getRandomTimeInSeconds());
        } catch (InterruptedException e) {
            failureRunnable.run();
        }
    }

    public static void sleepForShortTime(Runnable failureRunnable) {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            failureRunnable.run();
        }
    }

}
