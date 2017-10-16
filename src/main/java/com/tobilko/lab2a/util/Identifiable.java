package com.tobilko.lab2a.util;

import java.io.Serializable;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public interface Identifiable<T extends Serializable> {
    T getId();
}