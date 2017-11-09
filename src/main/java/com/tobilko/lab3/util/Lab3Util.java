package com.tobilko.lab3.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Andrew Tobilko on 11/9/17.
 */
public final class Lab3Util {

    public static final int MAX_STREAM_SIZE = 10000;

    public static <T> T sneakyThrows(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
