package com.tobilko.lab2.processor;

import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.*;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class Processor {

    private final long id = generateRandomId();

    @Getter
    private final long processingTime;
    private final ProcessorQueue queue;

    @SneakyThrows
    public void process() {
        while(true) {
            System.out.println(this + " is looking for a process...");
            Thread.sleep(5000L);
        }
    }

    @Override
    public String toString() {
        return "Processor #" + id;
    }

}
