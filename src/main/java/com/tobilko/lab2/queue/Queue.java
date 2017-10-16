package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.Identifiable;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Queue extends Identifiable<Integer> {

    Deque<Process> getProcesses();

}
