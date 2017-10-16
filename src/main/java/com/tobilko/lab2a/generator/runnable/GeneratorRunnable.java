package com.tobilko.lab2a.generator.runnable;

import com.tobilko.lab2a.generator.Generator;
import com.tobilko.lab2a.process.Process;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface GeneratorRunnable<T extends Process> extends Runnable {

    Generator<T> getGenerator();

}