package com.tobilko.lab2.thread;

import com.tobilko.lab2.processor.Processor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
@RequiredArgsConstructor
public final class ProcessorThread extends Thread {

    private final Processor processor;

    @Override
    public void run() {
        System.out.printf("%s is gonna start...\n", processor);
        processor.process();
    }

}
