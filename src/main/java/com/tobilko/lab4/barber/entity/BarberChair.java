package com.tobilko.lab4.barber.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberChair {

    @Getter
    @Setter
    private BarberCustomer currentCustomer;

    public boolean isFree() {
        return currentCustomer == null;
    }

    public void getChairFree() {
        currentCustomer = null;
    }

}
