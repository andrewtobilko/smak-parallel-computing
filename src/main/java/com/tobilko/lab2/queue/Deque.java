package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.Identifiable;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Deque extends Identifiable<Integer> {

    java.util.Deque<Process> getProcesses();

}
