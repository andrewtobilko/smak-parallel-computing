package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.locks.Lock;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class Barber implements Identifiable<Integer> {

    private final Integer id;
    private final long haircutTime;

    public void makeHaircut(BarberCustomer customer, BarberChair chair) throws InterruptedException {
        logCustomer(customer);

        final Lock lock = chair.getChairLock().getLock();

        // check chair availability
        lock.lock();
        try {
            if (!chair.isFree()) {
                throw new IllegalArgumentException("The chair is already occupied.");
            }

            chair.setCurrentCustomer(customer);
        } finally {
            lock.unlock();
        }

        // make a haircut
        Thread.sleep(haircutTime * 2000);

        // say goodbye to the customer
        lock.lock();
        try {
            chair.getChairFree();
        } finally {
            lock.unlock();
        }

    }

    private void logCustomer(BarberCustomer customer) {
        System.out.printf("%s is cutting %s's hair...\n", this, customer);
    }

    private void logGettingChairFree(BarberCustomer customer) {
        System.err.printf("%s has got a new haircut. He is leaving in a good moon.", customer);
    }

    @SneakyThrows
    public BarberCustomer tryToCallInCustomerFromWaitingRoom(BarberWaitingRoom room) {
        return room.getLine().poll();
    }

    @Override
    public String toString() {
        return format("%s [%d, %ds]", Barber.class.getSimpleName(), id, haircutTime);
    }

}
