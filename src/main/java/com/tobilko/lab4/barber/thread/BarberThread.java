package com.tobilko.lab4.barber.thread;

import com.tobilko.lab4.barber.entity.*;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;

/**
 * barber ->
 *      is anybody in the waiting room ?
 *          take one and make a haircut
 *          sleep
 *
 * Created by Andrew Tobilko on 11/11/17.
 */
// TODO: 11/11/17 thread
// TODO: 11/11/17 exceptions
@RequiredArgsConstructor
public final class BarberThread extends Thread {

    private final Barbershop barbershop;

    @Override
    public void run() {

        final Barber barber = barbershop.getBarber();
        final BarberWaitingRoom room = barbershop.getWaitingRoom();
        final BarberChair chair = barbershop.getChair();

        final Lock lock = barbershop.getLock().getLock();
        final Condition condition = barbershop.getLock().getCondition();

        while (getNumberOfClientsExpected().get() > 0) {

            try {
                if (barbershop.getWaitingRoom().isEmpty()) {
                    lock.lock();
                    try {
                        condition.await();
                        if (!chair.isFree()) {
                            barber.makeHaircut(chair.getCurrentCustomer(), chair);
                            chair.getChairFree();
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (InterruptedException exception) {
                System.err.println("someone interrupted the barber while he was waiting for a new customer");
            }

            final BarberCustomer customer = barber.tryToCallInCustomerFromWaitingRoom(room);
            try {
                barber.makeHaircut(customer, chair);
            } catch (InterruptedException e) {
                System.out.println("someone interrupted the barber while he is working");
            }

        }

    }

}
