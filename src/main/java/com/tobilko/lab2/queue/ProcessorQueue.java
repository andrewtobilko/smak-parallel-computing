package com.tobilko.lab2.queue;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;
import lombok.Getter;

import java.util.PriorityQueue;

import static com.tobilko.lab2.util.Util.generateRandomId;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private final long id = generateRandomId();
    private final int numberOfProcessesToGenerate;

    @Getter
    private final PriorityQueue<Process> processes;
    private RandomGenerator<Process> generator;

    public ProcessorQueue(int processAmount) {
        numberOfProcessesToGenerate = processAmount;
        processes = new PriorityQueue<>(processAmount);
        // TODO: 10/12/17 generator
    }

    @Override
    public String toString() {
        return "Queue #" + id;
    }

}
