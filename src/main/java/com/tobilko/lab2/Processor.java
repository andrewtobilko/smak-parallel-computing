package com.tobilko.lab2;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class Processor implements ProcessorAware {

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

    public void setProcessor(Processor processor) {
        siblingProcess = processor;
    }

}
