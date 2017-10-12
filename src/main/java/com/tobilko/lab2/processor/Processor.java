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
        Thread checker = new Thread(this::checkQueueState);
        checker.setDaemon(true);

        checker.start();
    }

    @SneakyThrows
    private void checkQueueState() {
        while (true) {
            int size = queue.getProcesses().size();

            if (size == 0) {
                System.out.printf("%s of %s is empty!\n", this.queue, this);
            } else {
                System.out.printf("%s of %s has %d processors in line.\n", this.queue, this, size);
            }

            Thread.sleep(5000L);
        }
    }

    @SneakyThrows
    public void process() {
        while(true) {
            System.out.println(this + " is looking for a process...");
            Thread.sleep(5000L);
        }
    }

    @Override
    public String toString() {
        return "Processor #" + id;
    }

}
