package com.tobilko.lab4.barber;

import com.tobilko.lab2.generator.Generator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;
import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static com.tobilko.lab4.barber.util.BarberUtil.*;

@Getter
public final class Barbershop {

    private final BarberChair chair;
    private final Barber barber;
    private final BlockingQueue<BarberCustomer> room;

    public Barbershop() {
        final Lock chairLock = new ReentrantLock();
        final Condition newCustomerCame = chairLock.newCondition();

        // wait for condition
        // check for the queue

        chair = new BarberChair(newCustomerCame);
        barber = new Barber(getRandomTimeInSeconds());
        room = new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY);
    }

    public static void main(String[] args) {


    }

    static class ConsumerGenerator implements Generator<BarberCustomer> {

        @Override
        public BarberCustomer generate() {
            return new BarberCustomer(getRandomId());
        }

        @Override
        public Integer getId() {
            return getRandomId();
        }

    }

    /**
     * customer ->
     * is the barber sleeping ?
     * wake him up and get a haircut
     * is the waiting room full ?
     * leave
     * sit and wait
     */
    @RequiredArgsConstructor
    static class ConsumerRunnable implements Runnable {

        private final ConsumerGenerator generator;
        private final Thread barberThread;
        private final BlockingQueue<BarberCustomer> room;
        private final Lock waiterLock = new ReentrantLock();


        @Override
        public void run() {

            do {
                final BarberCustomer customer = generator.generate();
                System.out.printf("%s is coming...", customer);

                if (isWaiterSleeping()) {
                    System.out.println("The barber");
                    try {
                        wakeUpTheBarberAndGetAHaircut(customer);
                    } finally {
                        settleUpWithTheBarber();
                    }
                } else {
                    if (!room.offer(customer)) {
                        System.out.println("I can't stand it! I am leaving!");
                    }
                }

                simulateClientDelay();
            } while (getNumberOfClientsExpected().decrementAndGet() > 0);

        }

        private boolean isWaiterSleeping() {
            return waiterLock.tryLock();
        }

        private void wakeUpTheBarberAndGetAHaircut(BarberCustomer customer) {
            barberThread.interrupt();
            try {
                barberThread.interrupt();
//                barberThead.interrupt();
                // notify the barberThread
            } catch (Exception e) { // TODO: 11/10/17
                System.out.println("someone interrupted the barber while he is cutting customer's hair");
            }
        }

        private void settleUpWithTheBarber() {
            waiterLock.unlock();
        }

    }

    /**
     * barber ->
     * is anybody in the waiting room ?
     * take one and make a haircut
     * sleep = block on the room
     */
    @RequiredArgsConstructor
    static class BarberRunnable extends Thread {

        private final BlockingQueue<BarberCustomer> room;
        private final Barber barber;
        private final Lock chair;

        @Override
        public void run() {

            while (getNumberOfClientsExpected().get() > 0) {
                final BarberCustomer customer;
                try {

                    customer = room.poll(TIME_TO_WAIT_FOR_A_NEW_CUSTOMER, TimeUnit.MINUTES);
                    try {
                        barber.makeHaircut(customer);
                    } catch (InterruptedException e) {
                        System.out.println("someone interrupted the barber while he is working");
                    }
                } catch (InterruptedException e) {
                    System.out.println("someone interrupted the barber while he was waiting for a new customer");

                }
            }

        }
    }

}
