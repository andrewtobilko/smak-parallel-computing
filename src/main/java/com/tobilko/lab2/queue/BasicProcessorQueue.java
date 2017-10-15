package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.BasicProcess;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
public final class BasicProcessorQueue implements ProcessorQueue {

    private final int id = generateRandomId();
    private final BlockingQueue<BasicProcess> processes = new LinkedBlockingDeque<>();

    @Override
    public String toString() {
        return format("Queue #%d [%d]", id, processes.size());
    }

}
