package com.tobilko.lab4.barber;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.util.RandomUtil;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * customer ->
 *      is the barber sleeping ?
 *          wake him up and get a haircut
 *          is the waiting room full ?
 *              leave
 *              sit and wait
 *
 * barber ->
 *      is anybody in the waiting room ?
 *          take one and make a haircut
 *          sleep
 *
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class Application {

    public static void main(String[] args) {

        final Barber barber = new Barber(5);
        final WaitingRoom room = new WaitingRoom(new ArrayBlockingQueue<Customer>(5));

        final ConsumerGenerator consumerGenerator = new ConsumerGenerator();

        while (true) {

        }

    }

    static class ConsumerGenerator implements Generator<Customer> {

        @Override
        public Customer generate() {
            return new Customer(RandomUtil.getRandomId());
        }

        @Override
        public Integer getId() {
            return RandomUtil.getRandomId();
        }

    }

    @RequiredArgsConstructor
    static class ConsumerRunnable implements Runnable {

        private final ConsumerGenerator generator;
        private final WaitingRoom room;
        private final Barber barber;

        @Override
        public void run() {

            while (true) {
                final Customer customer = generator.generate();

                synchronized (barber) {
                }
            }

        }

    }

    static class BarberRunnable implements Runnable {

        @Override
        public void run() {

            while (true) {

            }

        }
    }

}
