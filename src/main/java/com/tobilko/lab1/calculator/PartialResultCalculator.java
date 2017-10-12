package com.tobilko.lab1.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab1.util.Util.LOAD_NUMBER;

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
        calculatePartially();
        //calculatePartiallyWithLoad();
    }

    private void calculatePartially() {
        for (int i = startingIndex; i < endingIndex; ++i) {
            result += Math.pow(array[i], 2);
        }
    }

    private void calculatePartiallyWithLoad() {
        for (int j = 0; j < LOAD_NUMBER; ++j) {
            for (int i = startingIndex; i < endingIndex; ++i) {
                result += Math.pow(array[i], 2);
            }
        }
    }

}
