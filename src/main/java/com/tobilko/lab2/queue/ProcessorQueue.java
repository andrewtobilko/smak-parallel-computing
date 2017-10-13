package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.process.generator.thread.ProcessGeneratorThread;
import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.Setter;

import java.util.PriorityQueue;

import static com.tobilko.lab2.util.Util.generateRandomId;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private final long id = generateRandomId();

    @Setter
    @Getter
    private Processor processor;

    @Getter
    private final PriorityQueue<Process> processes;

    public ProcessorQueue(int processAmount) {
        processes = new PriorityQueue<>(processAmount);
        initialiseProcessGeneration(processAmount);
    }

    private void initialiseProcessGeneration(int processAmount) {
        new ProcessGeneratorThread(this, processAmount).start();
    }

    @Override
    public String toString() {
        return "Queue #" + id;
    }

}
