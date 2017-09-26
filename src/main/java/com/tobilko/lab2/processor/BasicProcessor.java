package com.tobilko.lab2.processor;

import com.tobilko.lab2.thread.ProcessorAware;
import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class BasicProcessor implements Processor, ProcessorAware {

    /**
     * the time for serving a process
     */
    private final long serviceProcessTime;

    private ProcessorQueue queue;

    private Processor siblingProcess;

    @Override
    public Processor getProcessor() {
        return siblingProcess;
    }

    @Override
    public void setProcessor(Processor processor) {
        siblingProcess = processor;
    }

    @Override
    public void process() {

    }

}
