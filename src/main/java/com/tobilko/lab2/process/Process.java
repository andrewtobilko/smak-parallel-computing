package com.tobilko.lab2.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class Process {

    private final long id = generateRandomId();

    @Getter
    private final long timeInterval;

    @Override
    public String toString() {
        return format("Process #%d [%ds]", id, timeInterval);
    }

}
