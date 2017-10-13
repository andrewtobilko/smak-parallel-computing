package com.tobilko.lab2.queue;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.generator.ProcessGenerator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.util.ColourUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.PriorityQueue;

import static com.tobilko.lab2.util.ColourUtil.*;
import static com.tobilko.lab2.util.ColourUtil.Color.*;
import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
public final class ProcessorQueue {

    private final long id = generateRandomId();

    @Setter
    @Getter
    private Processor processor;

    @Getter
    private final PriorityQueue<Process> processes;

    public ProcessorQueue(int processAmount) {
        processes = new PriorityQueue<>(processAmount);
        initialiseProcessGeneration(processAmount);
    }

    private void initialiseProcessGeneration(int processAmount) {
        new ProcessGeneratorThread(processAmount).start();
    }

    @Override
    public String toString() {
        return "Queue #" + id;
    }

    private class ProcessGeneratorThread extends Thread {

        private final RandomGenerator<Process> generator;

        public ProcessGeneratorThread(int amount) {
            numberOfProcessesToGenerate = amount;
            generator = new ProcessGenerator();
            setDaemon(true);
        }

        private int numberOfProcessesToGenerate;

        @Override
        @SneakyThrows
        public void run() {
            while (numberOfProcessesToGenerate >= 0) {
                Process process = generator.generate();
                long timeInterval = process.getTimeInterval();
                println(format("Generated a process for %s - %s. The number of processes remaining: %d.\n",
                        ProcessorQueue.this, process, numberOfProcessesToGenerate--), BLACK);

                synchronized (ProcessorQueue.this.getProcessor()) {
                    println(format("%s is notifying %s.", ProcessorQueue.this, ProcessorQueue.this.getProcessor()), BLACK);
                    ProcessorQueue.this.getProcessor().notify();
                }

                Thread.sleep(timeInterval * 1000);
            }
        }

    }

}
