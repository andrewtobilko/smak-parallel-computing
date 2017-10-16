package com.tobilko.lab2.process;

import com.tobilko.lab2.util.Identifiable;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Process extends Identifiable<Integer> {

    int getTimeToNextGeneration();

}
