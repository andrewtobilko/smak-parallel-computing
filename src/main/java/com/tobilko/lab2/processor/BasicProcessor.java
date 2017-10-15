package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.BasicProcess;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcessor implements Processor {

    private final int id = generateRandomId();
    private final long processingTime;

    @Override
    public void executeProcess(BasicProcess process) {
        try {
            Thread.sleep(getProcessingTime() * 1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void waitFor(Object instance) throws InterruptedException {
        instance.wait();
    }

    @Override
    public String toString() {
        return format("Processor #%d [%ds]", id, processingTime);
    }

}
