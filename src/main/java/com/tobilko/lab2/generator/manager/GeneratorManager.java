package com.tobilko.lab2.generator.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface GeneratorManager<T extends Generator<? extends Process>> extends Runnable {

    T getGenerator();

}
