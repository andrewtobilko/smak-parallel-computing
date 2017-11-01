package com.tobilko.lab2.information;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrew Tobilko on 11/1/17.
 */
public final class Information {

    public static class RuntimeInformation {

        @Getter
        private static final AtomicInteger processesRemaining = new AtomicInteger();

        private RuntimeInformation() {}
    }

    @Getter
    public static class Statistics {

        public final AtomicInteger maxDequeSize = new AtomicInteger();
        public final AtomicInteger processesInterrupted = new AtomicInteger();

    }

    private Information() {}

}