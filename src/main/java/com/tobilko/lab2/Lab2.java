package com.tobilko.lab2;

import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.thread.ProcessorThread;
import com.tobilko.lab2.queue.ProcessorQueue;

import java.util.Queue;

import static com.tobilko.lab2.util.Util.generateRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class Lab2 {

    private static final int PROCESSOR_NUMBER = 3;
    private static final int[] PROCESS_NUMBERS = {5, 5, 4};

    public static void main(String[] args) {
        checkInputParametersValidity();

        final ProcessorThread[] threads = new ProcessorThread[PROCESSOR_NUMBER];

        // initialise processors
        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
            final ProcessorQueue queue = new ProcessorQueue(PROCESS_NUMBERS[i]);

            final Processor processor = new Processor(
                    generateRandomTimeInSeconds(),
                    queue
            );
            queue.setProcessor(processor);
            threads[i] = new ProcessorThread(processor);
        }

        // initialise adjacent processors
        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
            Queue<Processor> adjacentProcessors = threads[i].getProcessor().getAdjacentProcessors();

            for (int j = 0; j < PROCESSOR_NUMBER; j++) {
                if (i == j) {
                    continue;
                }

                adjacentProcessors.add(threads[j].getProcessor());
            }
        }

        // start
        for (ProcessorThread thread : threads) {
            thread.start();
        }

    }

    private static void checkInputParametersValidity() {
        if (PROCESS_NUMBERS.length != PROCESSOR_NUMBER) {
            throw new IllegalArgumentException("Check input parameters again.");
        }
    }

}
