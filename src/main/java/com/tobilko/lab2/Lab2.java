package com.tobilko.lab2;

import com.tobilko.lab2.unit.Unit;
import com.tobilko.lab2.unit.thread.UnitThread;
import com.tobilko.lab2.queue.BasicProcessorQueue;

import static com.tobilko.lab2.util.Util.generateRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class Lab2 {

    private static final int PROCESSOR_NUMBER = 2;
    private static final int[] PROCESS_NUMBERS = {2, 5};

    public static void main(String[] args) {
        checkInputParametersValidity();

        final Thread[] threads = new UnitThread[PROCESSOR_NUMBER];

        // initialise processors
//        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
//            final BasicProcessorQueue queue = new BasicProcessorQueue(PROCESS_NUMBERS[i]);
//
//            final Unit processor = new Unit(
//                    generateRandomTimeInSeconds(),
//                    queue
//            );
//            queue.setProcessor(processor);
//            threads[i] = new UnitThread(processor);
//        }

        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
            final Unit unit = new Unit(

            );

            threads[i] = new UnitThread(unit);
        }

        // initialise adjacent processors
//        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
//            java.util.Queue adjacentProcessors = threads[i].getUnit().getAdjacentQueues();
//
//            for (int j = 0; j < PROCESSOR_NUMBER; j++) {
//                if (i == j) {
//                    continue;
//                }
//
//                adjacentProcessors.add(threads[j].getProcessor());
//            }
//        }

        // start
        for (UnitThread thread : threads) {
            thread.start();
        }

    }

    private static void checkInputParametersValidity() {
        if (PROCESS_NUMBERS.length != PROCESSOR_NUMBER) {
            throw new IllegalArgumentException("Check input parameters again.");
        }
    }

}
