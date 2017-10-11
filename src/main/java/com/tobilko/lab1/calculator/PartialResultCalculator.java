package com.tobilko.lab1.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/11/17.
 */
@RequiredArgsConstructor
public final class PartialResultCalculator extends Thread {

    private final double[] array;
    private final int startingIndex;
    private final int endingIndex;

    @Getter
    private double result;

    @Override
    public void run() {
        for (int i = startingIndex; i < endingIndex; ) {
            result += array[i++];
        }
    }

}