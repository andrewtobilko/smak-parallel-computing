package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.*;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class ProcessorManager implements Runnable {

    private final Processor processor;
    @Getter
    private final Deque<Process> deque;
    private final Generator<Process> generator;

    @Setter
    private Deque<Process>[] deques;

    @Override
    public void run() {
        ProcessorManagerLogger processorManagerLogger = new ProcessorManagerLogger();

    }

    private static class ProcessorManagerLogger {

    }

}
