package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.queue.ProcessorQueue;
import com.tobilko.lab2.queue.state.QueueStateChecker;
import lombok.Getter;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.ColourUtil.Color.BLACK;
import static com.tobilko.lab2.util.ColourUtil.println;
import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class Processor {

    private final long id = generateRandomId();

    @Getter
    private final long processingTime;
    private final ProcessorQueue queue;

    public Processor(long processingTime, ProcessorQueue queue) {
        this.processingTime = processingTime;
        this.queue = queue;

        initialiseQueueStateChecker();
    }

    private void initialiseQueueStateChecker() {
        new QueueStateChecker(queue).start();
    }

    @SneakyThrows
    public void process() {
        while (true) {
            Process processToExecute = queue.getProcesses().poll();

            if (processToExecute != null) {
                println(format("%s is executing %s...", this, processToExecute), BLACK);
                Thread.sleep(processingTime * 1000);
            } else {
                // todo : check other queues
            }
        }
    }

    @Override
    public String toString() {
        return "Processor #" + id;
    }

}
