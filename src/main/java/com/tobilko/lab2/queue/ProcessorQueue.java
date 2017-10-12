package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab1.generator.RandomGenerator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

import static com.tobilko.lab2.util.Util.generateRandomId;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private final long id = generateRandomId();
    private final int numberOfProcessesToGenerate;
    private final PriorityQueue<Process> processes;
    private RandomGenerator<Process> generator;

    public ProcessorQueue(int processAmount) {
        numberOfProcessesToGenerate = processAmount;
        processes = new PriorityQueue<>(processAmount);

        initialiseStateChecker();
    }

    private void initialiseStateChecker() {
        Thread checker = new Thread(this::checkState);
        checker.setDaemon(true);

        checker.start();
    }

    @SneakyThrows
    private void checkState() {
        while (true) {
            int size = processes.size();

            if (size == 0) {
                System.out.println(this + " is empty!");
            } else {
                System.out.printf("%s has %d processors in line.", this, size);
            }

            Thread.sleep(5000L);
        }
    }

    @Override
    public String toString() {
        return "Queue #" + id;
    }

}
