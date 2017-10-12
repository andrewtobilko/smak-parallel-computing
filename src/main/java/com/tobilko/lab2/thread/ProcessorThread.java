package com.tobilko.lab2.thread;

import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
@RequiredArgsConstructor
public final class ProcessorThread extends Thread {

    private final Processor processor;

    @Override
    public void run() {
        System.out.println(processor.toString() + " is gonna start...");
        processor.process();
    }

}
