package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@Setter
@Getter
@RequiredArgsConstructor
public final class BarberChair implements Identifiable<Integer> {

    private final Integer id;
    private BarberCustomer currentCustomer;

    public boolean isFree() {
        return currentCustomer == null;
    }

    public void getChairFree() {
        currentCustomer = null;
    }

    @Override
    public String toString() {
        return format("%s [%d]", BarberChair.class.getSimpleName(), id);
    }

}
