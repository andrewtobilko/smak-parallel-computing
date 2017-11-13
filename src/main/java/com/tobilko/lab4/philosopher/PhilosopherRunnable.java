package com.tobilko.lab4.philosopher;

import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/13/17.
 */
@RequiredArgsConstructor
public class PhilosopherRunnable implements Runnable {

    private final Philosopher philosopher;

    @Override
    public void run() {
        philosopher.think();
    }

}
