package com.tobilko.lab2.thread;

import com.tobilko.lab2.processor.Processor;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
public interface ProcessorAware {

    Processor getProcessor();

    void setProcessor(Processor processor);

}
