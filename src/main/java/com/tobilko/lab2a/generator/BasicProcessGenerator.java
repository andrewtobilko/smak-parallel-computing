package com.tobilko.lab2a.generator;

import com.tobilko.lab2a.process.BasicProcess;
import com.tobilko.lab2a.process.Process;
import com.tobilko.lab2a.util.RandomUtil;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public class BasicProcessGenerator implements Generator<Process> {

    @Override
    public Process generate() {
        return new BasicProcess(
                RandomUtil.getRandomId(),
                RandomUtil.getRandomTimeInSeconds()
        );
    }

}