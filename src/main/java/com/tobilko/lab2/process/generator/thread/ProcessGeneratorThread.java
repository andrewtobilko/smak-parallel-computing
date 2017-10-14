package com.tobilko.lab2.process.generator.thread;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.process.generator.ProcessGenerator;
import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.SneakyThrows;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.tobilko.lab2.util.ColourUtil.Color.GREEN;
import static com.tobilko.lab2.util.ColourUtil.Color.YELLOW;
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
        Thread.sleep(10000l);
        while (numberOfProcessesToGenerate > 0) {
            final Process process = generator.generate();
            queue.getProcesses().add(process);

            synchronized (queue) {
                println(format("%s notified %s about %s.", queue, queue.getProcessor(), process), YELLOW);
                queue.notify();
            }

            final long timeInterval = process.getTimeInterval();
            println(format("Generated %s for %s. The number of processes remaining to generate: %d.",
                    process, queue, --numberOfProcessesToGenerate), GREEN);

            Thread.sleep(timeInterval * 1000);
        }
    }

}
