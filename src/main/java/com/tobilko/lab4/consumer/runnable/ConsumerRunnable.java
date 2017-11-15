package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.Consumer;
import com.tobilko.lab4.consumer.entity.lock.DequeState;
import com.tobilko.lab4.consumer.entity.Item;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ConsumerRunnable implements Runnable {

    private final Consumer consumer;
    private final Deque<Item> deque;

    private final CompositeLock<DequeState> compositeLock;

    @Override
    public void run() {
        final Lock lock = compositeLock.getLock();

        while (true) {
            consumer.consume(null);
        }

    }

}
