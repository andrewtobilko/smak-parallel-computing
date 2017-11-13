package com.tobilko.lab4.barber.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab4.barber.entity.BarberChair;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import com.tobilko.lab4.barber.entity.BarberWaitingRoom;
import com.tobilko.lab4.barber.entity.Barbershop;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Lock;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static com.tobilko.lab4.barber.util.BarberUtil.simulateClientDelay;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class ConsumerManager implements Runnable {

    private final Generator<BarberCustomer> generator = new ConsumerGenerator();
    private final Barbershop barbershop;

    @Override
    public void run() {

        final BarberChair chair = barbershop.getChair();
        final BarberWaitingRoom room = barbershop.getWaitingRoom();

        do {
            final BarberCustomer customer = generator.generate();
            logCustomerComing(customer);

            // try to occupy the chair
            if (tryToSeatCustomerOnChair(customer)) {
                continue;
            }

            // try to join the line or quit
            if (room.getLine().offer(customer)) {
                logCustomerJoinedLine(customer);
            } else {
                logCustomerLeft(customer);
            }

            simulateClientDelay();
        } while (getNumberOfClientsExpected().decrementAndGet() > 0);

    }

    private void logCustomerComing(BarberCustomer customer) {
        System.out.printf("%s is coming...\n", customer);
    }

    private void logCustomerJoinedLine(BarberCustomer customer) {
        System.out.printf("\t\t\t%s joined the line.\n", customer);
    }

    private void logCustomerLeft(BarberCustomer customer) {
        System.err.printf("\t\t\tThere is no free chairs in the waiting room. %s is leaving...\n", customer);
    }

    private boolean tryToSeatCustomerOnChair(BarberCustomer customer) {
        final Lock lock = barbershop.getBarberLock().getLock();
        final BarberChair chair = barbershop.getChair();

        lock.lock();
        try {
            if (barbershop.getChair().isFree()) {
                System.out.printf("%s has taken the chair and notified the barber.\n", customer);
                chair.setCurrentCustomer(customer);
                barbershop.getBarberLock().getNewCustomerArrived().signal();

                return true;
            }
        } finally {
            lock.unlock();
        }

        return false;
    }

    private static class ConsumerGenerator implements Generator<BarberCustomer> {

        @Override
        public BarberCustomer generate() {
            return new BarberCustomer(getRandomId());
        }

        @Override
        public Integer getId() {
            return getRandomId();
        }

    }

}

