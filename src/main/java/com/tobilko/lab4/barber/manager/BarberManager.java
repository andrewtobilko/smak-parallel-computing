package com.tobilko.lab4.barber.manager;

import com.tobilko.lab4.barber.entity.*;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static java.util.Objects.isNull;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberManager implements Runnable {

    private final Barbershop barbershop;

    @Override
    public void run() {

        final Barber barber = barbershop.getBarber();
        final BarberWaitingRoom room = barbershop.getWaitingRoom();
        final BarberChair chair = barbershop.getChair();

        final Lock lock = barbershop.getBarberLock().getLock();
        final Condition newCustomerArrived = barbershop.getBarberLock().getNewCustomerArrived();

        while (getNumberOfClientsExpected().get() > 0) {

            final BarberCustomer customerFromLine = barber.tryToCallInCustomerFromWaitingRoom(room);

            if (!isNull(customerFromLine)) {    // a customer has been taken from the line
                // check chair availability
                lock.lock();
                try {
                    if (!chair.isFree()) {
                        throw new IllegalArgumentException("The chair is already occupied.");
                    }

                    chair.setCurrentCustomer(customerFromLine);
                } finally {
                    lock.unlock();
                }

                // make a haircut
                barber.makeHaircut(customerFromLine);

                // say goodbye to the customer
                lock.lock();
                try {
                    chair.getChairFree();
                } finally {
                    lock.unlock();
                }
            } else {    // no customers found in the line
                try {
                    // wait for a new customer from outside
                    lock.lock();
                    try {
                        newCustomerArrived.await();
                    } finally {
                        lock.unlock();
                    }

                    lock.lock();
                    try {
                        if (chair.isFree()) {
                            System.err.println("The barber has woken up, but the chair is empty");
                            continue;
                        }
                    } finally {
                        lock.unlock();
                    }

                    // make a haircut
                    barber.makeHaircut(chair.getCurrentCustomer());

                    // say goodbye to the customer
                    lock.lock();
                    try {
                        chair.getChairFree();
                    } finally {
                        lock.unlock();
                    }
                } catch (InterruptedException e) {
                    System.err.println("!!! newCustomerArrived.await()");
                }
            }

        }

    }

}
