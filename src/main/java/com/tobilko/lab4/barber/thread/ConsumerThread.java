package com.tobilko.lab4.barber.thread;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab4.barber.Barbershop;
import com.tobilko.lab4.barber.entity.BarberCustomer;
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

        do {
            final BarberCustomer customer = generator.generate();
            System.out.printf("%s is coming...", customer);

            if (!customer.tryToSitOnChair(barbershop.getChair())) {
                if (!barbershop.getRoom().offer(customer)) {
                    System.out.println("I can't stand it! I am leaving!");
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

