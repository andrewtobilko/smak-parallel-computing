package com.tobilko.lab2.processor.runnable;

import com.tobilko.lab2.processor.Processor;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.GREEN;
import static com.tobilko.lab2.util.OutputUtil.println;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class ProcessorRunnable implements Runnable {

    private final Processor processor;

    @Override
    public void run() {
        println(GREEN, "%s with %s started running...", Thread.currentThread().getName(), processor);
    }

}
