package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.processor.Processor;

/**
 * Created by Andrew Tobilko on 10/29/17.
 */
public interface ProcessorManager extends Runnable {

    Processor getProcessor();

}
