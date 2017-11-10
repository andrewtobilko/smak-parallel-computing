package com.tobilko.lab4.barber.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientCounter {

    @Getter
    private static final AtomicInteger numberOfClientsExpected = new AtomicInteger(20);

}
