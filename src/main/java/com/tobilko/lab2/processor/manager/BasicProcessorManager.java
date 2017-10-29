package com.tobilko.lab2.processor.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.*;

import java.util.Deque;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.GREEN;
import static com.tobilko.lab2.util.OutputUtil.OutputColour.RED;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.*;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class BasicProcessorManager implements ProcessorManager {

    @Getter
    private final Processor processor;
    @Getter
    private final Deque<Process> deque;
    private final Generator<Process> generator;

    @Setter
    private Deque<Process>[] deques;

    @Override
    public void run() {
        BasicProcessorManagerLogger.logProcessorManagerStart(this);
        // todo
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

}
