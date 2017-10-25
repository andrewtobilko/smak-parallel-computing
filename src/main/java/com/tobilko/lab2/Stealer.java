package com.tobilko.lab2;

/**
 * Created by Andrew Tobilko on 10/25/17.
 */
public interface Stealer<T extends StolenItem<?, ?>> {

    T steal();

}
