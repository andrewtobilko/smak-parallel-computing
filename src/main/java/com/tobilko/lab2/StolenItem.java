package com.tobilko.lab2;

/**
 * Created by Andrew Tobilko on 10/25/17.
 */
public interface StolenItem<WHAT, FROM> {

    WHAT getItem();
    FROM getStolenFrom();

}
