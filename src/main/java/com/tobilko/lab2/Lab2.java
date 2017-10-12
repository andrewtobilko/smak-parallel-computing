package com.tobilko.lab2;

import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.queue.ProcessorQueue;
import com.tobilko.lab2.thread.ProcessorThread;

import static com.tobilko.lab2.util.Util.generateRandomProcessorWorkTime;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class Lab2 {

    public static void main(String[] args) {

        final int PROCESSOR_NUMBER = 2;
        final int[] PROCESS_NUMBERS = {5, 2};

        if (PROCESS_NUMBERS.length != PROCESSOR_NUMBER) {
            throw new IllegalArgumentException("Check input parameters again.");
        }

        final ProcessorThread[] threads = new ProcessorThread[PROCESSOR_NUMBER];

        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
            final ProcessorQueue queue = new ProcessorQueue(PROCESS_NUMBERS[i]);

            final Processor processor = new Processor(
                    generateRandomProcessorWorkTime(),
                    queue
            );
            queue.setProcessor(processor);
            threads[i] = new ProcessorThread(processor);
        }

        for (ProcessorThread thread : threads) {
            thread.start();
        }

    }

}
