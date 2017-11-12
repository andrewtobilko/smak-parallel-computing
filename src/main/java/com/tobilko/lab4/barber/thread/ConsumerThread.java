package com.tobilko.lab4.barber.thread;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab4.barber.entity.*;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static com.tobilko.lab4.barber.util.BarberUtil.simulateClientDelay;

/**
 * customer ->
 *      is the barber sleeping ?
 *          wake him up and get a haircut
 *          is the waiting room full ?
 *              leave
 *              sit and wait
 *
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class ConsumerThread extends Thread { // TODO: 11/11/17 thread

    private final Generator<BarberCustomer> generator = new ConsumerGenerator();
    private final Barbershop barbershop;

    @Override
    public void run() {

        final BarberChair chair = barbershop.getChair();
        final BarberWaitingRoom room = barbershop.getWaitingRoom();

        do {
            final BarberCustomer customer = generator.generate();
            System.out.printf("%s is coming...\n", customer);

            if (customer.tryToSitOnTheChair(chair)) {
                System.out.printf("\t\t\t%s sat on the chair!\n", customer);
            } else {
                if (customer.tryToJoinTheLine(room.getLine())) {
                    System.out.printf("\t\t\t%s joined the line.\n", customer);
                } else {
                    System.err.printf("\t\t\tNo free chairs in the waiting room. %s is leaving...\n", customer);
                }
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

