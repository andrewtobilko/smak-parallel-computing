package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
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
            Process processToExecute = queue.getProcesses().poll();

            if (processToExecute != null) {
                System.out.printf("%s is executing %s...\n", this, processToExecute);
                Thread.sleep(processingTime * 1000);
            }


            System.out.println("!");
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
                    System.err.printf("%s of %s is empty!\n", queue, Processor.this);

                    synchronized (queue) {
                        System.out.printf("%s waiting for %s...", Processor.this, queue);
                        queue.wait();
                    }
                } else {
                    System.err.printf("%s of %s has %d processors in line.\n", queue, Processor.this, size);
                }

                Thread.sleep(5000L);
            }
        }
    }

}
