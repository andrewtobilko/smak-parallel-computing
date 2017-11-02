package com.tobilko.lab2.information;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Andrew Tobilko on 11/1/17.
 */
public final class Information {

    public static class RuntimeInformation {

        @Getter
        @Setter
        private static volatile int processesRemaining;

        public static synchronized void decrementProcessesRemaining() {
            --processesRemaining;
        }

        private RuntimeInformation() {}

    }

    @Getter
    public static class Statistics {

        @Setter
        private volatile int maxDequeSize;
        private volatile int processesInterrupted;

        public synchronized void incrementProcessesInterrupted() {
            ++processesInterrupted;
        }

    }

    private Information() {}

}