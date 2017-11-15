package com.tobilko.lab4.writer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Reader implements Identifiable<Integer> {

    @Getter
    private final Integer id;

    public void readFrom(Book book) {}

    @Override
    public String toString() {
        return getFormattedString();
    }

}
