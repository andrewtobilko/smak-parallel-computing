package com.tobilko.lab2.statistics;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Andrew Tobilko on 10/25/17.
 */

// todo
public final class Statistics {

    private int processInterrupted;
    private int queueMaxSize;

}

/*

manager.wait(); || generator.wait();



processorThread.interrupt()

 */


/*


ProcessorManager
    - processorThread
    - processorInstance



 */

@RequiredArgsConstructor
class WorkingUnit {
    private final Deque<Process> deque;
    private final Processor processor;

    public void doo(Deque<Process> deque, Processor processor) {
        Process process = deque.pollFirst();
        try {
            processor.process(process);
        } catch (InterruptedException exception) {
            deque.addFirst(process); // go back to the queue
        }
    }
}

