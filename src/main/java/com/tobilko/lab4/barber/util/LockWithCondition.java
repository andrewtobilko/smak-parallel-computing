package com.tobilko.lab4.barber.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public final class LockWithCondition {

    private final Lock lock;
    private final Condition condition;

}
