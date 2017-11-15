package com.tobilko.lab4.consumer.entity.lock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public final class CompositeLock<KEY extends Serializable> {

    private final Lock lock;
    private final Map<KEY, Condition> conditions;

}
