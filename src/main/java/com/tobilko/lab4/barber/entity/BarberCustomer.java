package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class BarberCustomer implements Identifiable<Integer> {

    private final Integer id;

    @Override
    public String toString() {
        return format("%s [%d]", BarberCustomer.class.getSimpleName(), id);
    }

}
