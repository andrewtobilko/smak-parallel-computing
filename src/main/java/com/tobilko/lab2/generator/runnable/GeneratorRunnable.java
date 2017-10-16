package com.tobilko.lab2.generator.runnable;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface GeneratorRunnable<T extends Process> extends Runnable {

    Generator<T> getGenerator();

}