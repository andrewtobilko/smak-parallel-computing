package com.tobilko.lab2;

import com.tobilko.lab2.unit.thread.UnitThread;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class Lab2 {

    private static final int PROCESSOR_NUMBER = 2;
    private static final int[] PROCESS_NUMBERS = {2, 5};

    public static void main(String[] args) {
        checkInputParametersValidity();

        final Thread[] threads = new UnitThread[PROCESSOR_NUMBER];

        // 1. initialise all working units (comprises a processor and a queue)
        //      1.1. create an empty queue within the same thread
        //      1.2. create a thread for the processor and run the thread
        //      1.3.
        // 2. create all source threads
        // 3. attach a working unit to a source thread
        // 4. start all source threads
        // 5.



        // The classes CompositeUnit and BasicUnit implement the Runnable.


        final Unit mainUnit = new CompositeUnit();

        for (int i = 0; i < PROCESSOR_NUMBER; i++) {
            Unit unit = new BasicUnit();


        }


        // start
        for (Thread thread : threads) {
            thread.start();
        }

    }

    private static void checkInputParametersValidity() {
        if (PROCESS_NUMBERS.length != PROCESSOR_NUMBER) {
            throw new IllegalArgumentException("Check input parameters again.");
        }
    }

}

interface Unit extends Runnable {

    default Collection<Unit> getChildUnits() {
        throw new UnsupportedOperationException("The unit doesn't have child units...");
    }

    default Unit getParentUnit() {
        throw new UnsupportedOperationException("The unit doesn't have a parent...");
    }

}

class CompositeUnit implements Unit {

    private final Collection<Unit> units;
    private final Unit parent;

    @Override
    public void run() {

    }

}

class BasicUnit implements Unit {

    @Override
    public void run() {

    }

}