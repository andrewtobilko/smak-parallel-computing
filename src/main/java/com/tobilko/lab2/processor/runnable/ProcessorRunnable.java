package com.tobilko.lab2.processor.runnable;

import com.tobilko.lab2.processor.Processor;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class ProcessorRunnable implements Runnable {

    private final Processor processor;

    @Override
    public void run() {
        while (true) {
        }
    }

}
