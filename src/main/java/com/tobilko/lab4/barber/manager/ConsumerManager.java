package com.tobilko.lab4.barber.manager;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab4.barber.entity.BarberChair;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import com.tobilko.lab4.barber.entity.BarberWaitingRoom;
import com.tobilko.lab4.barber.entity.Barbershop;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Condition;
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

        final Lock lock = barbershop.getBarberLock().getLock();
        final Condition newCustomerArrived = barbershop.getBarberLock().getNewCustomerArrived();

        do {
            final BarberCustomer customer = generator.generate();
            System.out.printf("%s is coming...\n", customer);

            // try to occupy the chair
            lock.lock();
            try {
                if (chair.isFree()) {
                    System.out.println("the chair is free!! NOTIFYING THE BARBER!!");
                    chair.setCurrentCustomer(customer);
                    newCustomerArrived.signal();
                    continue;
                }
            } finally {
                lock.unlock();
            }

            // try to join the line or quit
            if (customer.tryToJoinTheLine(room.getLine())) {
                System.out.printf("\t\t\t%s joined the line.\n", customer);
            } else {
                System.err.printf("\t\t\tNo free chairs in the waiting room. %s is leaving...\n", customer);
            }

            simulateClientDelay();
        } while (getNumberOfClientsExpected().decrementAndGet() > 0);

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

