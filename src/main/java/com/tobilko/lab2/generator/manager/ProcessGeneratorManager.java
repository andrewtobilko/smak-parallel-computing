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

    private final Generator<Process> generator;
    private final int limit;
    private final Deque<Process> deque;

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
        if (process == null) {
            throw new IllegalArgumentException("NULL won't be added to the queue...");
        }

        boolean wasProcessAddedToTheQueue;

        synchronized (deque) {
            wasProcessAddedToTheQueue = deque.offerLast(process);
        }

        System.out.println(
                wasProcessAddedToTheQueue ?
                        String.format("%s was added to the queue!", process) :
                        String.format("An error occurred while adding %s to the queue...", process)
        );
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