package com.tobilko.lab2.information;

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

    public static class Statistics {

        private int maxDequeSize;
        private int processesInterrupted;

        // processesInterrupted
        public synchronized void incrementProcessesInterrupted() {
            ++processesInterrupted;
        }

        public synchronized int getProcessesInterrupted() {
            return processesInterrupted;
        }


        // maxDequeSize
        public synchronized int getMaxDequeSize() {
            return maxDequeSize;
        }

        public synchronized void setMaxDequeSize(int size) {
            maxDequeSize = size;
        }

    }

    private Information() {}

}
