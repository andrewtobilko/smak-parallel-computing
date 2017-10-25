package com.tobilko.lab2.unit;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.util.ThreadAware;
import lombok.RequiredArgsConstructor;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class UnitRunnable implements Runnable {

    private final Deque<Process> queue;
    private final ThreadAware<Processor> processorThreadAware;

    @Override
    public void run() {
        while (true) {
            Processor processor = processorThreadAware.getInstance();
        }
    }

}
