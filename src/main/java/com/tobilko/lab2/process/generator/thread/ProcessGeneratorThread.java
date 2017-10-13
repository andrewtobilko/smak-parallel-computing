package com.tobilko.lab2.process.generator.thread;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.process.generator.ProcessGenerator;
import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.ColourUtil.Color.GREEN;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/14/17.
 */
public final class ProcessGeneratorThread extends Thread {

    private final ProcessorQueue queue;
    private final RandomGenerator<Process> generator;

    public ProcessGeneratorThread(ProcessorQueue queue) {
        this.queue = queue;
        numberOfProcessesToGenerate = queue.getProcesses().remainingCapacity();
        generator = new ProcessGenerator();
    }

    private int numberOfProcessesToGenerate;

    @Override
    @SneakyThrows
    public void run() {
        while (numberOfProcessesToGenerate > 0) {
            final Process process = generator.generate();
            queue.getProcesses().add(process);

            final long timeInterval = process.getTimeInterval();
            println(format("Generated %s for %s. The number of processes remaining to generate: %d.",
                    process, queue, numberOfProcessesToGenerate--), GREEN);

            Thread.sleep(timeInterval * 1000);
        }
    }

}