package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcessor implements Processor {

    private final Integer id;
    private final int processingTime;

    @Override
    public boolean process(Process process) {
        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

}
