package com.tobilko.lab4.barber.manager;

import com.tobilko.lab4.barber.entity.Barber;
import com.tobilko.lab4.barber.entity.BarberChair;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import com.tobilko.lab4.barber.entity.Barbershop;
import lombok.RequiredArgsConstructor;

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
        final BarberChair chair = barbershop.getChair();

        while (getNumberOfClientsExpected().get() > 0) {

            BarberCustomer customer = barber.tryToCallInCustomerFromWaitingRoom(barbershop.getWaitingRoom());

            if (!isNull(customer)) {
                // offer a seat to the customer
                offerSeatToCustomer(customer);
            } else {
                // wait for a customer from outside
                waitForCustomerFromOutside();

                // make sure the chair is not free
                if (makeSureSeatIsOccupied()) {
                    customer = chair.getCurrentCustomer();
                } else {
                    continue;
                }

            }

            // make a haircut
            barber.makeHaircut(customer);

            // say goodbye to the customer
            sayGoodByeToCustomer(customer);

        }

    }

    private boolean makeSureSeatIsOccupied() {
        final Lock lock = barbershop.getBarberLock().getLock();

        lock.lock();
        try {
            if (barbershop.getChair().isFree()) {
                System.err.println("The barber has woken up, but the chair is empty...");
                return false;
            }
        } finally {
            lock.unlock();
        }

        return true;
    }

    private void waitForCustomerFromOutside() {
        final Lock lock = barbershop.getBarberLock().getLock();

        try {
            lock.lock();
            try {
                barbershop.getBarberLock().getNewCustomerArrived().await();
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.err.println("Someone interrupted the barber while he was waiting for a new customer...");
        }

    }

    private void offerSeatToCustomer(BarberCustomer customer) {
        final Lock lock = barbershop.getBarberLock().getLock();
        final BarberChair chair = barbershop.getChair();

        lock.lock();
        try {
            if (!chair.isFree()) {
                throw new IllegalArgumentException("The chair is already occupied.");
            }

            chair.setCurrentCustomer(customer);
        } finally {
            lock.unlock();
        }
    }

    private void sayGoodByeToCustomer(BarberCustomer customer) {
        final Lock lock = barbershop.getBarberLock().getLock();

        lock.lock();
        try {
            barbershop.getChair().getChairFree();
        } finally {
            lock.unlock();
        }
    }

}
