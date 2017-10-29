package com.tobilko.lab2.generator;

import com.tobilko.lab2.process.BasicProcess;
import com.tobilko.lab2.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class ProcessGenerator implements Generator<Process> {

    @Getter
    private final Integer id;

    @Override
    public final Process generate() {
        return new BasicProcess(getRandomId(), getRandomTimeInSeconds());
    }

    @Override
    public String toString() {
        return format("Generator #%d", id);
    }
}