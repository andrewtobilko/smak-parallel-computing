package com.tobilko.lab2.generator.thread;

import com.tobilko.lab1.generator.RandomGenerator;
import com.tobilko.lab2.process.Process;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
@RequiredArgsConstructor
public class ProcessGeneratorThread extends Thread {

    private final RandomGenerator<Process> generator;

    @Override
    @SneakyThrows
    public void run() {
        while(true) {
            System.out.println("I am a generator!");
            Thread.sleep(1000L);
        }
    }

}
