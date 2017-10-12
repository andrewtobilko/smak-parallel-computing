package com.tobilko.lab2.generator;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;

import java.util.Random;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class ProcessGenerator implements RandomGenerator<Process> {

    @Override
    public Process generate() {
        return new Process(new Random().nextInt(10)); // todo
    }

}
