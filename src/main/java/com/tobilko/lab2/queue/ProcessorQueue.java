package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.util.RandomGenerator;

import java.util.PriorityQueue;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private PriorityQueue<Process> processes;
    private RandomGenerator<Process> generator;

}
