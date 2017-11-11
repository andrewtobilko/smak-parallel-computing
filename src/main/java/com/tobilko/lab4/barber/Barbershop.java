package com.tobilko.lab4.barber;

import com.tobilko.lab4.barber.entity.Barber;
import com.tobilko.lab4.barber.entity.BarberChair;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import com.tobilko.lab4.barber.thread.BarberThread;
import com.tobilko.lab4.barber.thread.ConsumerThread;
import com.tobilko.lab4.barber.util.LockWithCondition;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;
import static com.tobilko.lab4.barber.util.BarberUtil.WAITING_ROOM_CAPACITY;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Barbershop {

    private final BarberChair chair;
    private final Barber barber;
    private final BlockingQueue<BarberCustomer> room;

    public static Barbershop createStandardBarbershop() {
        final Lock chairLock = new ReentrantLock();
        final Condition newCustomerCame = chairLock.newCondition();

        final BarberChair chair = new BarberChair(LockWithCondition.of(chairLock, newCustomerCame));
        final Barber barber = new Barber(getRandomTimeInSeconds());

        return new Barbershop(
                null,
                null,
                new ArrayBlockingQueue<>(WAITING_ROOM_CAPACITY)
        );
    }

    public static void main(String[] args) {
        final Barbershop barbershop = Barbershop.createStandardBarbershop();

        new BarberThread(barbershop).start();
        new ConsumerThread(barbershop).start();
    }

}
