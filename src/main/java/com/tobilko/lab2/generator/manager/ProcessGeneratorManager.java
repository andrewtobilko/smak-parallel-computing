package com.tobilko.lab2.generator.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.information.Information;
import com.tobilko.lab2.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Deque;
import java.util.concurrent.TimeUnit;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.*;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.format;


/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
public final class ProcessGeneratorManager implements GeneratorManager<Generator<Process>> {

    private final Generator<Process> generator;                                         // a mechanism to generate processes by
    private final int limit;                                                            // a number of processes to generate
    private final Deque<Process> deque;                                                 // a place where generated processes will be placed in

    private final ProcessGeneratorManagerLogger logger;                                 // to log everything happened in the manager
    private final ProcessGeneratorManagerStatisticsCaretaker statisticsCaretaker;       // to maintain statistics

    public ProcessGeneratorManager(Generator<Process> generator, int limit, Deque<Process> deque, Information.Statistics statistics) {
        this.generator = generator;
        this.deque = deque;

        // initialise inner helpers
        logger = new ProcessGeneratorManagerLogger();
        statisticsCaretaker = new ProcessGeneratorManagerStatisticsCaretaker(statistics);

        validateLimit(this.limit = limit);
    }

    private void validateLimit(int limit) {
        if (limit < 1) {
            throw new IllegalArgumentException("Limit should be a positive number.");
        }
    }

    @Override
    public final void run() {
        logger.logManagerStart();

        int remainingNumberOfProcessesToGenerate = limit;

        while (remainingNumberOfProcessesToGenerate-- > 0) {
            // generate
            final Process process = generateProcess();

            // add to the queue and notify
            addProcessToQueue(process);
            notifyOfProcessGenerated();

            // not to wait when there is no processes to generate
            if (remainingNumberOfProcessesToGenerate == 0) {
                break;
            }

            // sleep
            sleepTillNextGeneration(process.getTimeToNextGeneration());
        }

        logger.logManagerFinish();
    }

    private void addProcessToQueue(Process process) {
        validateProcess(process);

        synchronized (deque) {
            deque.addLast(process);
            statisticsCaretaker.recalculateMaxQueueSizeByCurrentDequeSize(deque.size());
        }

        logger.logProcessAdded(process);
    }

    private void validateProcess(Process process) {
        if (process == null) {
            throw new IllegalArgumentException("NULL won't be added to the queue...");
        }
    }

    private void notifyOfProcessGenerated() {
        synchronized (generator) {
            generator.notifyAll();
        }
    }

    private Process generateProcess() {
        return generator.generate();
    }

    @SneakyThrows
    private void sleepTillNextGeneration(long timeToTheNextGeneration) {
        TimeUnit.SECONDS.sleep(timeToTheNextGeneration);
    }

    @Override
    public String toString() {
        return format("Generator Manager [%s]", generator);
    }

    private class ProcessGeneratorManagerLogger {

        private void logManagerStart() {
            println(GREEN, "%s is about to start generating.", ProcessGeneratorManager.this);
        }

        private void logManagerFinish() {
            println(RED, "%s is about to finish generating.", ProcessGeneratorManager.this);
        }

        private void logProcessAdded(Process process) {
            println(YELLOW, "%s: %s was added to the queue!", ProcessGeneratorManager.this, process);
        }

    }

    @RequiredArgsConstructor
    private class ProcessGeneratorManagerStatisticsCaretaker {

        private final Information.Statistics statistics;

        public void recalculateMaxQueueSizeByCurrentDequeSize(int currentSize) {
            if (statistics.getMaxDequeSize() < currentSize) {
                statistics.setMaxDequeSize(currentSize);
            }
        }

    }

}