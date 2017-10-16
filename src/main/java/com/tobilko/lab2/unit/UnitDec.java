package com.tobilko.lab2.unit;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.generator.thread.ProcessGeneratorThread;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.thread.ProcessorThread;
import com.tobilko.lab2.queue.ProcessQueue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
@RequiredArgsConstructor
public final class UnitDec implements Comparable<UnitDec> {

    private final ProcessQueue queue;

    private Thread processorThread;
    private Thread generatorThread;

    public UnitDec(RandomGenerator<Process> generator, Processor processor, ProcessQueue queue) {
        initialiseAndStartProcessGeneratorThread(generator);
        initialiseAndStartProcessorThread(processor);
        this.queue = queue;
    }

    private void initialiseAndStartProcessorThread(Processor processor) {
        (processorThread = new ProcessorThread(processor)).start();
    }

    private void initialiseAndStartProcessGeneratorThread(RandomGenerator<Process> generator) {
        (generatorThread = new ProcessGeneratorThread(generator)).start();
    }

    @SneakyThrows
    public void process() {
        // todo
    }

    @Override
    public int compareTo(UnitDec another) {
        final int queueSizeComparisonResult = Integer.compare(
                getQueue().getProcesses().size(),
                another.getQueue().getProcesses().size()
        );

        return queueSizeComparisonResult == 0 ?
                Long.compare(get.getProcessingTime(), another.getProcessor().getProcessingTime()) :
                queueSizeComparisonResult;
    }

}
