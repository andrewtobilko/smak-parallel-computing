package com.tobilko.lab4.barber.runnable;

import com.tobilko.lab4.barber.entity.*;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static java.util.Objects.isNull;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberRunnable implements Runnable {

    private final Barbershop barbershop;

    @Override
    public void run() {

        final Barber barber = barbershop.getBarber();
        final BarberWaitingRoom room = barbershop.getWaitingRoom();
        final BarberChair chair = barbershop.getChair();

        final Lock lock = barbershop.getLock().getLock();
        final Condition newCustomerArrived = barbershop.getLock().getCondition();

        while (getNumberOfClientsExpected().get() > 0) {

//            try {
//                if (barbershop.getWaitingRoom().isEmpty()) {
//                    lock.lock();
//                    try {
//                        condition.await();
//                        if (!chair.isFree()) {
//                            barber.makeHaircut(chair.getCurrentCustomer(), chair);
//                            chair.getChairFree();
//                        }
//                    } finally {
//                        lock.unlock();
//                    }
//                }
//            } catch (InterruptedException exception) {
//                System.err.println("someone interrupted the barber while he was waiting for a new customer");
//            }
//
//            final BarberCustomer customer = barber.tryToCallInCustomerFromWaitingRoom(room);
//            try {
//                barber.makeHaircut(customer, chair);
//            } catch (InterruptedException e) {
//                System.out.println("someone interrupted the barber while he is working");
//            }

            final BarberCustomer customerFromLine = barber.tryToCallInCustomerFromWaitingRoom(room);

            if (!isNull(customerFromLine)) {    // a customer has been taken from the line
                barber.makeHaircut(customerFromLine, chair);
            } else {    // no customers found in the line
                try {
                    newCustomerArrived.await();
                } catch (InterruptedException e) {
                    System.out.println("interrupted while waiting a new customer");
                }
            }

        }

    }

}
