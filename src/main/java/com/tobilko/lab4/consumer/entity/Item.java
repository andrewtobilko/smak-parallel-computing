package com.tobilko.lab4.consumer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Item implements Identifiable<Integer> {

    @Getter
    private final Integer id;

    @Override
    public String toString() {
        return getFormattedString();
    }

}
