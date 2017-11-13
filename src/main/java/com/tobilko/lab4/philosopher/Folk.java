package com.tobilko.lab4.philosopher;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/13/17.
 */
@Getter
@RequiredArgsConstructor
public final class Folk implements Identifiable<Integer> {

    private final Integer id;

}
