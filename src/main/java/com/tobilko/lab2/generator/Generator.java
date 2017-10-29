package com.tobilko.lab2.generator;

import com.tobilko.lab2.util.Identifiable;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Generator<T> extends Identifiable<Integer> {

    T generate();

}
