package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class BasicProcessor implements Processor {

    private final Integer id;
    private final int processingTime;

    public BasicProcessor(Integer id, int processingTime) {
        // set an id
        this.id = id;

        // set a processing time
        if (processingTime <= 0) {
            throw new IllegalArgumentException("A processor can't be created with processing time <= 0.");
        }
        this.processingTime = processingTime;
    }

    @Override
    public boolean process(Process process) {
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public int getProcessingTime() {
        return processingTime;
    }

}
