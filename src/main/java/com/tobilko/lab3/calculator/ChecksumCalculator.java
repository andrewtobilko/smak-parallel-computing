package com.tobilko.lab3.calculator;

import java.util.concurrent.Callable;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public final class ChecksumCalculator implements Callable<String> {

    @Override
    public String call() {
        return "checksum";
    }

}
