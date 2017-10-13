package com.tobilko.lab2.process.generator.thread;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.process.generator.ProcessGenerator;
import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.ColourUtil.Color.BLACK;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/14/17.
 */
public final class ProcessGeneratorThread extends Thread {

    private final ProcessorQueue queue;
    private final RandomGenerator<Process> generator;

    public ProcessGeneratorThread(ProcessorQueue queue, int amount) {
        this.queue = queue;
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
            println(format("Generated a process for %s - %s. The number of processes remaining: %d.",
                    queue, process, numberOfProcessesToGenerate--), BLACK);

            synchronized (queue.getProcessor()) {
                println(format("%s is notifying %s.", queue, queue.getProcessor()), BLACK);
                queue.getProcessor().notify();
            }

            Thread.sleep(timeInterval * 1000);
        }
    }

}