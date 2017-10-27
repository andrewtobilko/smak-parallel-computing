package com.tobilko.lab2.generator.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class ProcessGeneratorManager implements GeneratorManager<Generator<Process>> {

    private final Generator<Process> generator; // the mechanism to generate processes by
    private final int limit;                    // a number of processes to generate
    private final Deque<Process> deque;         // a place where generated processes will be placed in

    @Override
    public final void run() {
        int remainingNumberOfProcessesToGenerate = limit;

        while (remainingNumberOfProcessesToGenerate-- > 0) {
            final Process process = generateProcess();

            addProcessToQueue(process);
            notifyOfProcessGenerated();

            sleepTillNextGeneration(process.getTimeToNextGeneration());
        }
    }

    private void addProcessToQueue(Process process) {
        validateProcess(process);

        boolean wasProcessAddedToTheQueue;
        synchronized (deque) {
            wasProcessAddedToTheQueue = deque.offerLast(process);
        }

        System.out.println(wasProcessAddedToTheQueue ?
                String.format("%s was added to the queue!", process) :
                String.format("An error occurred while adding %s to the queue...", process)
        );
    }

    private void validateProcess(Process process) {
        if (process == null) {
            throw new IllegalArgumentException("NULL won't be added to the queue...");
        }
    }

    private void notifyOfProcessGenerated() {
        synchronized (generator) {
            generator.notify();
        }
    }

    private Process generateProcess() {
        return generator.generate();
    }

    @SneakyThrows
    private void sleepTillNextGeneration(long timeToTheNextGeneration) {
        TimeUnit.SECONDS.sleep(timeToTheNextGeneration);
    }

}