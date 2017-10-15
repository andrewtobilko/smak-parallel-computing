package com.tobilko.lab2.unit;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.generator.thread.ProcessGeneratorThread;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.thread.ProcessorThread;
import com.tobilko.lab2.queue.BasicProcessorQueue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
@RequiredArgsConstructor
public final class Unit implements Comparable<Unit> {

    // the same thread
    private final BasicProcessorQueue queue;
    private Thread processorThread;

    public Unit(RandomGenerator<Process> generator, Processor processor) {
        initialiseAndStartProcessGeneratorThread(generator);
        initialiseAndStartProcessorThread(processor);
    }

    private void initialiseAndStartProcessorThread(Processor processor) {
        (processorThread = new ProcessorThread(processor)).start();
    }

    private void initialiseAndStartProcessGeneratorThread(RandomGenerator<Process> generator) {
        new ProcessGeneratorThread(generator).start();
    }

    @SneakyThrows
    public void process() {
        // todo
    }

    @Override
    public int compareTo(Unit another) {
        final int queueSizeComparisonResult = Integer.compare(
                getQueue().getProcesses().size(),
                another.getQueue().getProcesses().size()
        );

        return queueSizeComparisonResult == 0 ?
                Long.compare(processor.getProcessingTime(), another.getProcessor().getProcessingTime()) :
                queueSizeComparisonResult;
    }

}
