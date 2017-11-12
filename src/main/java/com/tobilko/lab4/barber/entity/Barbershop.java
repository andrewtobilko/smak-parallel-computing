package com.tobilko.lab4.barber.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
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
    private final BarberLock lock;

    public static Barbershop createStandardBarbershop() {
        final Lock chairLock = new ReentrantLock();
        final BarberLock lock = BarberLock.of(chairLock, chairLock.newCondition());

        return new Barbershop(
                new BarberChair(lock),
                new Barber(getRandomId(), getRandomTimeInSeconds()),
                BarberWaitingRoom.of(new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY)),
                lock
        );
    }

}
