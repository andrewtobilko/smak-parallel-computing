package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.BasicProcess;
import com.tobilko.lab2.util.Identifiable;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
public interface Processor extends Identifiable {

    long getProcessingTime();

    void executeProcess(BasicProcess process) throws InterruptedException;

    void waitFor(Object instance) throws InterruptedException;

}
