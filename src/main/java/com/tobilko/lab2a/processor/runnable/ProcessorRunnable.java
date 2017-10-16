package com.tobilko.lab2a.processor.runnable;

import com.tobilko.lab2a.processor.Processor;
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
            System.out.println("The processor is running...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
