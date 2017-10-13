package com.tobilko.lab2.process.generator;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;

import static com.tobilko.lab2.util.Util.generateRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class ProcessGenerator implements RandomGenerator<Process> {

    @Override
    public Process generate() {
        return new Process(generateRandomTimeInSeconds());
    }

}
