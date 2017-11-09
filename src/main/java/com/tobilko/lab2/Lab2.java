package com.tobilko.lab2;

import static com.tobilko.lab2.Lab2Initialiser.InputParameters;
import static com.tobilko.lab2.Lab2Initialiser.initialise;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Lab2 {

    public static void main(String[] args) {
        final int NUMBER_OF_PROCESSORS = 2;
        final int[] NUMBERS_OF_PROCESSES_TO_GENERATE = {5, 10};

        initialise(InputParameters.of(NUMBER_OF_PROCESSORS, NUMBERS_OF_PROCESSES_TO_GENERATE));
    }

}
