package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.information.Information;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Deque;
import java.util.Random;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.*;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.format;

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

            Process process = null;
            synchronized (deque) {
                if (!deque.isEmpty()) {
                    process = deque.pollFirst();
                }
            }

            if (process == null) {
                if (deques == null || deques.length == 0) {
                    logger.logWorkStealingFailure();
                    System.out.println(String.format("%s couldn't steal a process since it didn't have deques.", this));
                }
                Random random = new Random();

                int randomIndex = random.nextInt(deques.length);

                final Deque<Process> dequeToStealFrom = deques[randomIndex];
                Process alienProcess = null;
                synchronized (dequeToStealFrom) {
                    if (!dequeToStealFrom.isEmpty()) {
                        alienProcess = dequeToStealFrom.pollFirst();
                    }
                }

                if (alienProcess == null) {
                    continue;
                }

                try {
                    synchronized (this) {
                        this.notify();
                    }
                    System.err.println("starting to execute a stolen process");
                    processor.process(alienProcess);
                    decrementProcessesRemainingCounter();
                    System.err.println("ending to execute a stolen process");
                } catch (InterruptedException e) {
                    System.err.println("INTERRUPTER has found OUR process waiting in the queue. LET'S execute it!");

                    statisticsCaretaker.incrementProcessesInterrupted();

                    synchronized (dequeToStealFrom) {
                        dequeToStealFrom.addFirst(alienProcess);
                    }
                }

            } else {
                try {
                    processor.process(process);
                    decrementProcessesRemainingCounter();
                } catch (InterruptedException e) {
                    System.err.println("Someone interrupted the processor which was executing ITS OWN process. NO WAY!");
                }
            }

        }

        logger.logProcessorManagerFinish();
        logger.logStatistics(statisticsCaretaker.getStatistics());
    }

    private boolean isThereAnyProcessesAvailable() {
        return Information.RuntimeInformation.getProcessesRemaining() > 0;
    }

    private void decrementProcessesRemainingCounter() {
        Information.RuntimeInformation.decrementProcessesRemaining();
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

    }

    @RequiredArgsConstructor
    private class ProcessorManagerStatisticsCaretaker {

        @Getter
        private final Information.Statistics statistics;

        public void incrementProcessesInterrupted() {

            synchronized (statistics) {
                statistics.setProcessesInterrupted(statistics.getProcessesInterrupted() + 1);
            }

        }

    }

    @RequiredArgsConstructor
    private class ProcessorManagerInterrupter implements Runnable {

        private final Object monitor;

        @Override
        public void run() {

            while (true) {
                // wait
                try {
                    // wait for the manager
                    synchronized (BasicProcessorManager.this) {
                        BasicProcessorManager.this.wait();
                    }

                    // wait for the monitor
                    synchronized (monitor) {
                        monitor.wait();
                    }
                    // interrupt the manager
                    BasicProcessorManager.this.interrupt();
                    System.err.println("interrupted");
                } catch (InterruptedException e) {
                    println(RED, "Someone interrupted the interrupter...");
                }

            }

        }

    }

}
