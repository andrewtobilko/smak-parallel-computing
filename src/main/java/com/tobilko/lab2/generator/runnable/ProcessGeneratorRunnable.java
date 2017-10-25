package com.tobilko.lab2.generator.runnable;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class ProcessGeneratorRunnable implements GeneratorRunnable<Process> {

    private final Generator<Process> generator;
    private final int limit;
//    private final Deque<Process> queue;

    @Override
    @SneakyThrows
    public void run() {
        int remainingNumberOfProcessesToGenerate = limit;

        while (remainingNumberOfProcessesToGenerate-- > 0) {
            Process process = generator.generate();
            Thread.sleep(process.getTimeToNextGeneration() * 1000); // todo
        }

    }

}