package com.tobilko.lab2;

import com.tobilko.lab2.generator.BasicProcessGenerator;
import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.generator.runnable.ProcessGeneratorRunnable;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.BasicProcessor;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.runnable.ProcessorRunnable;
import com.tobilko.lab2.util.RandomUtil;

import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Lab2 {
    public static void main(String[] args) {

        final int NUMBER_OF_GENERATORS = 2;
        final int[] PROCESS_NUMBERS_TO_GENERATE = {5, 5};

        for (int i = 0; i < NUMBER_OF_GENERATORS; i++) {

            // create a generator
            final Generator<Process> generator = new BasicProcessGenerator();

            // create a processor
            final Processor processor = new BasicProcessor(
                    RandomUtil.getRandomId(),
                    RandomUtil.getRandomTimeInSeconds()
            );

            // create a deque
            Deque<Process> queue = new LinkedBlockingDeque<>();

            // create a runnable for a generator thread
            final Runnable generatorRunnable = new ProcessGeneratorRunnable(
                    generator,
                    PROCESS_NUMBERS_TO_GENERATE[i],
                    queue
            );

            // create a runnable for a processor thread
            final Runnable processorRunnable = new ProcessorRunnable(processor);

            // create a unit to manage all of them together


        }

    }
}
