package com.tobilko.lab4.barber.entity;

import com.tobilko.lab4.barber.util.LockWithCondition;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
    private final BarberWaitingRoom room;

    public static Barbershop createStandardBarbershop() {
        // TODO: 11/11/17 ??? 2
        final Lock chairLock = new ReentrantLock();
        final Condition newCustomerCame = chairLock.newCondition();

        return new Barbershop(
                new BarberChair(LockWithCondition.of(chairLock, newCustomerCame)),
                new Barber(getRandomId(), getRandomTimeInSeconds()),
                BarberWaitingRoom.of(new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY))
        );
    }

}
