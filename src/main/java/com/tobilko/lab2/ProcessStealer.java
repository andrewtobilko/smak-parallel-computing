package com.tobilko.lab2;

import com.tobilko.lab2.process.Process;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/25/17.
 */
public final class ProcessStealer implements StolenItem<Process, Deque<Process>> {

    @Override
    public Process getItem() {
        return null;
    }

    @Override
    public Deque<Process> getStolenFrom() {
        return null;
    }

}
