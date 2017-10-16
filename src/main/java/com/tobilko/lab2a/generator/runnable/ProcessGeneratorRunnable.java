package com.tobilko.lab2a.generator.runnable;

import com.tobilko.lab2a.generator.Generator;
import com.tobilko.lab2a.process.Process;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class ProcessGeneratorRunnable implements GeneratorRunnable<Process> {

    private final Generator<Process> generator;
    private @NonNull int limit;

    @Override
    public void run() {
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