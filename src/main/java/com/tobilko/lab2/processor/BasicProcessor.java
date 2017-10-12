package com.tobilko.lab2.processor;

import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.Util.generateRandomId;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class BasicProcessor implements Processor {

    private final long id = generateRandomId();
    // // TODO: 10/12/17
    private final long serviceProcessTime;

    private ProcessorQueue queue;

//    @Override
//    public Processor getProcessor() {
//        return siblingProcess;
//    }
//
//    @Override
//    public void setProcessor(Processor processor) {
//        siblingProcess = processor;
//    }

    @Override
    public void process() {

    }

    @Override
    public String toString() {
        return "Processor #" + id;
    }

}
