package com.tobilko.lab2.processor;


import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.Identifiable;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Processor extends Identifiable<Integer> {

    int getProcessingTime();

    boolean process(Process process);

}
