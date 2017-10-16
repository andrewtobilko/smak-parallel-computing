package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import lombok.Getter;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
public final class BasicProcessQueue implements ProcessQueue {

    private final int id;
    private final BlockingDeque<Process> processes;

    public BasicProcessQueue() {
        id = generateRandomId();
        processes = new LinkedBlockingDeque<>();
    }

    @Override
    public String toString() {
        return format("Queue #%d [%d]", id, processes.size());
    }

}
