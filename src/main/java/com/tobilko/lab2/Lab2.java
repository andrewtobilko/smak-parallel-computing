package com.tobilko.lab2;

import com.tobilko.lab2.generator.BasicProcessGenerator;
import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.generator.runnable.ProcessGeneratorRunnable;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.BasicProcessor;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.runnable.ProcessorRunnable;
import com.tobilko.lab2.util.RandomUtil;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Lab2 {

    public static void main(String[] args) {

        final int L1 = 5;
        final int L2 = 2;

        final Processor processor1 = new BasicProcessor(RandomUtil.getRandomId(), RandomUtil.getRandomTimeInSeconds());
        final Processor processor2 = new BasicProcessor(RandomUtil.getRandomId(), RandomUtil.getRandomTimeInSeconds());

        final Generator<Process> generator = new BasicProcessGenerator();

        final Runnable generatorRunnable1 = new ProcessGeneratorRunnable(generator, L1);
        final Runnable generatorRunnable2 = new ProcessGeneratorRunnable(generator, L2);

        new Thread(generatorRunnable1).start();
        new Thread(generatorRunnable2).start();

    }

}
