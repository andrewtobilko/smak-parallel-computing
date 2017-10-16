package com.tobilko.lab2.generator.runnable;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.queue.Queue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class ProcessGeneratorRunnable implements GeneratorRunnable<Process> {

    private final Generator<Process> generator;
    private final int limit;
    private final Deque<Process> queue;

    @Override
    public void run() {
        int amount = limit;

        while (true) {
            System.out.println("The generator is generating...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}