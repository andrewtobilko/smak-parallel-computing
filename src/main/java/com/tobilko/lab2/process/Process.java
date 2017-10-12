package com.tobilko.lab2.process;

import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.Util.generateRandomId;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@RequiredArgsConstructor
public final class Process {

    private final long id = generateRandomId();
    
    // // TODO: 10/12/17 rename
    private final long timeInterval;

    @Override
    public String toString() {
        return "Process #" + id;
    }

}
