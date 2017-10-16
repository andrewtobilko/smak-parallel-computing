package com.tobilko.lab2a.unit;

import com.tobilko.lab2a.generator.Generator;
import com.tobilko.lab2a.processor.Processor;
import com.tobilko.lab2a.queue.Queue;
import com.tobilko.lab2a.util.ThreadAware;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Unit implements Runnable {

    private Queue queue;
    private ThreadAware<Processor> processorThreadAware;
    private ThreadAware<Generator> generatorThreadAware;

    @Override
    public void run() {
        while (true) {
            System.out.println("I am managing everything...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
