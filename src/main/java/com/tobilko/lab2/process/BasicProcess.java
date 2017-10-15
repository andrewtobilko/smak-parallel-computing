package com.tobilko.lab2.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcess implements Process {

    private final int id;
    private final long timeInterval;

    @Override
    public String toString() {
        return format("Process #%d [%ds]", id, timeInterval);
    }

}
