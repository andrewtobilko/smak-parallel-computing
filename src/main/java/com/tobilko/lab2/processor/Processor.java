package com.tobilko.lab2.processor;

import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.Getter;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.Util.generateRandomId;

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
            System.out.println(this + " is looking for a process...");
            Thread.sleep(5000L);
        }
    }

    @Override
    public String toString() {
        return "Processor #" + id;
    }

    private final class QueueStateChecker extends Thread {

        private final ProcessorQueue queue;

        public QueueStateChecker(ProcessorQueue queue) {
            this.queue = queue;
            setDaemon(true);
        }

        @Override
        public void run() {
            checkQueueState();
        }

        @SneakyThrows
        private void checkQueueState() {
            while (true) {
                int size = queue.getProcesses().size();

                if (size == 0) {
                    System.out.printf("%s of %s is empty!\n", queue, Processor.this);
                } else {
                    System.out.printf("%s of %s has %d processors in line.\n", queue, Processor.this, size);
                }

                Thread.sleep(5000L);
            }
        }
    }

}
