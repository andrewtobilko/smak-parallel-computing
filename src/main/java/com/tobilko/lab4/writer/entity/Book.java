package com.tobilko.lab4.writer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Book implements Identifiable<Integer> {

    @Getter
    private final Integer id;

}
