package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.*;

import java.util.Deque;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.GREEN;
import static com.tobilko.lab2.util.OutputUtil.OutputColour.RED;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
public final class BasicProcessorManager extends Thread implements ProcessorManager {

    private final Processor processor;
    private final Deque<Process> deque;
    private final Generator<Process> generator;
    private final ProcessorManagerInterrupter interrupter;

    @Setter
    private Deque<Process>[] deques;


    public BasicProcessorManager(Processor processor, Deque<Process> deque, Generator<Process> generator) {
        this.processor = processor;
        this.deque = deque;
        this.generator = generator;

        // initialise the interrupter
        interrupter = new ProcessorManagerInterrupter(generator);
        interrupter.setDaemon(true);
        interrupter.start();
    }


    @Override
    public void run() {
        BasicProcessorManagerLogger.logProcessorManagerStart(this);

        while(true) {
            synchronized (generator) {
                // wait for the generator
                try {
                    generator.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                break; // TODO: 10/29/17
            }
        }

        BasicProcessorManagerLogger.logProcessorManagerFinish(this);
    }

    @Override
    public String toString() {
        return format("Processor Manager [%s]", processor);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class BasicProcessorManagerLogger {

        public static void logProcessorManagerStart(BasicProcessorManager manager) {
            println(GREEN, "%s is gonna start processing.", manager);
        }

        public static void logProcessorManagerFinish(BasicProcessorManager manager) {
            println(RED, "%s finished his work.", manager);
        }

    }

    @RequiredArgsConstructor
    private class ProcessorManagerInterrupter extends Thread {

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
                } catch (InterruptedException e) {
                    println(RED, "Someone interrupted the interrupter...");
                }

            }

        }

    }

}

