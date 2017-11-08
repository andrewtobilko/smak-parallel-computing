package com.tobilko.lab3.calculator;

import java.util.concurrent.Executor;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public final class ChecksumCalculator extends ExecutorAwareCalculator {

    public ChecksumCalculator(final Executor executor) {
        super(executor);
    }

    @Override
    public void run() {
        System.out.println("checksum");
    }

}
