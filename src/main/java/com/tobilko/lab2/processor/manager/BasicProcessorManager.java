package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.information.Information;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.util.RandomUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Deque;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.*;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.format;
import static java.util.Objects.isNull;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
public final class BasicProcessorManager extends Thread implements ProcessorManager {

    private final Processor processor;                                      // to delegate process handling
    private final Deque<Process> deque;                                     // to take a process from

    @Setter
    private Deque<Process>[] deques;                                        // to steal a process from

    private final ProcessorManagerLogger logger;                            // to log out all events
    private final ProcessorManagerStatisticsCaretaker statisticsCaretaker;  // to maintain statistics

    public BasicProcessorManager(Processor processor, Deque<Process> deque, Generator<Process> generator, Information.Statistics statistics) {
        this.processor = processor;
        this.deque = deque;

        // initialise the interrupter
        Thread interrupter = new Thread(new ProcessorManagerInterrupter(generator));
        interrupter.setDaemon(true);
        interrupter.start();

        // initialise inner helpers
        logger = new ProcessorManagerLogger();
        statisticsCaretaker = new ProcessorManagerStatisticsCaretaker(statistics);
    }

    @Override
    public void run() {
        logger.logProcessorManagerStart();

        while (isThereAnyProcessesAvailable()) {

            final Process ownProcess = getProcessFromDeque(deque);

            if (isNull(ownProcess)) {
                performWorkStealing();
            } else {
                 executeProcessWithFailureCallback(ownProcess, logger::logFailureAtOwnProcessExecution);
            }
        }

        logger.logProcessorManagerFinish();
        logger.logStatistics(statisticsCaretaker.getStatistics());
    }

    private Process getProcessFromDeque(Deque<Process> deque) {
        Process process = null;

        synchronized (deque) {
            if (!deque.isEmpty()) {
                process = deque.pollFirst();
            }
        }

        return process;
    }

    private void performWorkStealing() {
        if (!canWorkingStealingBePerformed()) {
            return;
        }

        final Deque<Process> deque = selectRandomDequeFromArray(deques);
        final Process process = getProcessFromDeque(deque);

        // the random deque has no processes
        if (isNull(process)) {
            return;
        }

        // notify the interrupter
        synchronized (this) {
            this.notify();
        }

        executeProcessWithFailureCallback(process, () -> handleFailureAtStolenProcessExecution(deque, process));
    }

    private boolean canWorkingStealingBePerformed() {
        if (deques == null || deques.length == 0) {
            logger.logWorkStealingFailure();
            return false;
        }

        return true;
    }

    private Deque<Process> selectRandomDequeFromArray(Deque<Process>[] deques) {
        return deques[RandomUtil.getRandomIntBetween(0, deques.length)];
    }

    private void executeProcessWithFailureCallback(Process process, Runnable failureCallback) {
        try {
            processor.process(process);
            Information.RuntimeInformation.decrementProcessesRemaining();
        } catch (InterruptedException exception) {
            failureCallback.run();
        }
    }

    private boolean isThereAnyProcessesAvailable() {
        return Information.RuntimeInformation.getProcessesRemaining() > 0;
    }

    private void handleFailureAtStolenProcessExecution(Deque<Process> stolenFrom, Process stolenProcess) {
        logger.logFailureAtStolenProcessExecution();
        statisticsCaretaker.incrementProcessesInterrupted();

        putProcessBackToDeque(stolenFrom, stolenProcess);
    }

    private void putProcessBackToDeque(Deque<Process> source, Process stolenProcess) {
        synchronized (source) {
            source.addFirst(stolenProcess);
        }
    }

    @Override
    public String toString() {
        return format("Processor Manager [%s]", processor);
    }

    private class ProcessorManagerLogger {

        public void logProcessorManagerStart() {
            println(GREEN, "%s is gonna start processing.", BasicProcessorManager.this);
        }

        public void logProcessorManagerFinish() {
            println(RED, "%s finished his work.", BasicProcessorManager.this);
        }

        public void logWorkStealingFailure() {
            println(YELLOW, "%s couldn't steal a process since it didn't have deques.", BasicProcessorManager.this);
        }

        public void logStatistics(Information.Statistics statistics) {
            println(BLACK, "Statistics for %s: processes interrupted - %s, the max deque size - %s",
                    BasicProcessorManager.this.getProcessor(),
                    statistics.getProcessesInterrupted(),
                    statistics.getMaxDequeSize()
            );
        }

        public void logFailureAtOwnProcessExecution() {
            println(RED, "The interrupter of %s has interrupted a processor executing own process.",
                    BasicProcessorManager.this.getProcessor());
        }

        public void logFailureAtStolenProcessExecution() {
            println(RED, "The interrupter of %s has interrupted executing. A new process was generated for that processor.",
                    BasicProcessorManager.this.getProcessor());
        }

    }

    @RequiredArgsConstructor
    private class ProcessorManagerStatisticsCaretaker {

        @Getter
        private final Information.Statistics statistics;

        public void incrementProcessesInterrupted() {
            statistics.incrementProcessesInterrupted();
        }

    }

    private class ProcessorManagerInterrupter implements Runnable {

        private final Object monitor;
        private final Thread threadToInterrupt;

        private ProcessorManagerInterrupter(Object instance) {
            this.monitor = instance;
            threadToInterrupt = BasicProcessorManager.this;
        }

        @Override
        @SneakyThrows
        public void run() {

            while (true) {
                // wait for the manager
                synchronized (BasicProcessorManager.this) {
                    BasicProcessorManager.this.wait();
                }

                final long waitingStartTime = System.currentTimeMillis();

                // wait for the monitor
                synchronized (monitor) {
                    monitor.wait();
                }

                // interrupt the manager
                if (shouldBeInterruptedAtThisTime(waitingStartTime)) {
                    threadToInterrupt.interrupt();
                }
            }

        }

        private boolean shouldBeInterruptedAtThisTime(long waitingStartTime) {
            final int processorTime = BasicProcessorManager.this.getProcessor().getProcessingTime();

            return ((System.currentTimeMillis() - waitingStartTime) / 1000) < processorTime;
        }

    }

}
