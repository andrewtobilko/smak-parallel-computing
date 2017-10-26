package com.tobilko.lab2.processor.supervisor;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Deque;
import java.util.List;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ProcessorSupervisor implements Runnable {

    private final Processor processor;

    private Deque<Process> deque;
    private List<Deque<Process>> deques;

    @Override
    public void run() {

        while (true) {
            if (!deque.isEmpty()) {
                // todo: execute own task
            } else {
                // todo : try to steal
            }
        }

    }

}
