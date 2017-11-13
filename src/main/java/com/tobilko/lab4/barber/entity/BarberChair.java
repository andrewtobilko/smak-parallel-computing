package com.tobilko.lab4.barber.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberChair {

    @Getter
    @Setter
    private volatile BarberCustomer currentCustomer;

    @Getter
    private final ChairLock chairLock;

    public boolean isFree() {
        return currentCustomer == null;
    }

    public void getChairFree() {
        currentCustomer = null;
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class ChairLock {

        private final Lock lock;
        private final Condition chairOccupied;

    }
}
