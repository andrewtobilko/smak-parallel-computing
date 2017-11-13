package com.tobilko.lab4.barber.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;
import static com.tobilko.lab4.barber.util.BarberUtil.WAITING_ROOM_CAPACITY;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Barbershop {

    private final BarberChair chair;
    private final Barber barber;
    private final BarberWaitingRoom waitingRoom;
    private final BarberLock barberLock;

    public static Barbershop createStandardBarbershop() {
        final Lock lock = new ReentrantLock();

        return new Barbershop(
                new BarberChair(),
                new Barber(getRandomId(), getRandomTimeInSeconds()),
                BarberWaitingRoom.of(new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY)),
                BarberLock.of(lock, lock.newCondition())
        );
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class BarberLock {

        private final Lock lock;
        private final Condition newCustomerArrived;

    }

}
