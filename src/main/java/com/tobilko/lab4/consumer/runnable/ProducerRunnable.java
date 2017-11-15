package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.Item;
import com.tobilko.lab4.consumer.entity.Producer;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ProducerRunnable implements Runnable {

    private final Producer producer;
    private final Deque<Item> deque;

    private final CompositeLock compositeLock;

    @Override
    public void run() {
        final Lock lock = compositeLock.getLock();

        while(true) {
            final Item item = producer.produce();
        }

    }

}
