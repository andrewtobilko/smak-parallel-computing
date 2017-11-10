package com.tobilko.lab4.barber;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.util.RandomUtil;
import com.tobilko.lab4.barber.util.ClientCounter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

import static com.tobilko.lab4.barber.util.BarberUtil.WAITING_ROOM_CAPACITY;

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
public final class Barbershop {

    private final Barber barber = new Barber(RandomUtil.getRandomTimeInSeconds());
    private final BlockingQueue<Consumer> room = new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY);

    public static void main(String[] args) {

        final ConsumerGenerator consumerGenerator = new ConsumerGenerator();



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

    /**
     * customer ->
     *      is the barber sleeping ?
     *          wake him up and get a haircut
     *          is the waiting room full ?
     *              leave
     *              sit and wait
     */
    @RequiredArgsConstructor
    static class ConsumerRunnable implements Runnable {

        private final ConsumerGenerator generator;
        private final Barber barber;
        private final Lock waiterLock = new ReentrantLock();


        @Override
        public void run() {

            while (true) {
                final Customer customer = generator.generate();
                ClientCounter.getNumberOfClientsExpected().decrementAndGet();

                if (isWaiterSleeping()) {
                    try {
                        wakeUpTheBarberAndGetAHaircut(customer);
                    } finally {
                        settleUpWithTheBarber();
                    }
                } else {
                    if (room)
                }

            }

        }

        private boolean isWaiterSleeping() {
            return waiterLock.tryLock();
        }

        private void wakeUpTheBarberAndGetAHaircut(Customer customer) {
            try {
                barber.makeHaircut(customer);
            } catch (InterruptedException e) {
                System.out.println("someone interrupted the barber while he is cutting customer's hair");
            }
        }

        private void settleUpWithTheBarber() {
            waiterLock.unlock();
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
