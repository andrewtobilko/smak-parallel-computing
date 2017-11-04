package com.tobilko.lab2.information;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Andrew Tobilko on 11/1/17.
 */
public final class Information {

    public static class RuntimeInformation {

        private static int processesRemaining;

        public static synchronized void setProcessesRemaining(int value) {
            processesRemaining = value;
        }

        public static synchronized int getProcessesRemaining() {
            return processesRemaining;
        }

        public static synchronized void decrementProcessesRemaining() {
            --processesRemaining;
        }

        private RuntimeInformation() {}

    }

    @Getter
    @Setter
    public static class Statistics {

        private volatile int maxDequeSize;
        private volatile int processesInterrupted;

    }

    private Information() {}

}
