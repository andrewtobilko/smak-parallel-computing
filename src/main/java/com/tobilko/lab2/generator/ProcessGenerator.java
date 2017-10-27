package com.tobilko.lab2.generator;

import com.tobilko.lab2.process.BasicProcess;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.RandomUtil;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class ProcessGenerator implements Generator<Process> {

    @Override
    public final Process generate() {
        return new BasicProcess(
                RandomUtil.getRandomId(),
                RandomUtil.getRandomTimeInSeconds()
        );
    }

}