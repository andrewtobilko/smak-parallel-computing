package com.tobilko.lab2a;

import com.tobilko.lab2a.generator.BasicProcessGenerator;
import com.tobilko.lab2a.generator.runnable.GeneratorRunnable;
import com.tobilko.lab2a.generator.runnable.ProcessGeneratorRunnable;
import com.tobilko.lab2a.process.Process;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Lab2 {
    public static void main(String[] args) {
        final int NUMBER_OF_GENERATORS = 2;
        final int[] PROCESS_NUMBERS_TO_GENERATE = {5, 5};

        for (int i = 0; i < NUMBER_OF_GENERATORS; i++) {
            final GeneratorRunnable<Process> generatorRunnable = new ProcessGeneratorRunnable(
                    new BasicProcessGenerator(),
                    PROCESS_NUMBERS_TO_GENERATE[i]
            );
        }
        final GeneratorRunnable<Process> generatorRunnable = new ProcessGeneratorRunnable(
                new BasicProcessGenerator(),
                100
        );
    }
}
