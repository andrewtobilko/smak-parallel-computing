package com.tobilko.lab2.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcess implements Process {

    private final Integer id;
    private final int timeToNextGeneration;

    @Override
    public String toString() {
        return format("Process #%d [%ds]", getId(), getTimeToNextGeneration());
    }
}