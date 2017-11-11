package com.tobilko.lab4.barber.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class BarberUtil {

    public static final int WAITING_ROOM_CAPACITY = 5;
    public static final int TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_OUTSIDE = 5;
    public static final int TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE = 5;

    @SneakyThrows
    public static void simulateClientDelay() {
        Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
    }

    public static final class ClientCounter {

        private static final AtomicInteger numberOfClientsExpected = new AtomicInteger(20);

        public static AtomicInteger getNumberOfClientsExpected() {
            return numberOfClientsExpected;
        }

    }

}
