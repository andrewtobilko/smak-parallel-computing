package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.process.generator.thread.ProcessGeneratorThread;
import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private final long id = generateRandomId();

    @Setter
    @Getter
    private Processor processor;

    @Getter
    private final BlockingQueue<Process> processes;

    public ProcessorQueue(int processAmount) {
        processes = new ArrayBlockingQueue<>(processAmount);
        initialiseProcessGeneration();
    }

    private void initialiseProcessGeneration() {
        new ProcessGeneratorThread(this).start();
    }

    @Override
    public String toString() {
        return format("Queue #%d [%d]", id, processes.size());
    }

}
