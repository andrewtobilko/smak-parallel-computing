package com.tobilko.lab2.util;

import java.io.Serializable;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Identifiable<T extends Serializable> {

    T getId();

    default String getFormattedString() {
        return format("%s [%s]", this.getClass().getSimpleName(), getId());
    }

}