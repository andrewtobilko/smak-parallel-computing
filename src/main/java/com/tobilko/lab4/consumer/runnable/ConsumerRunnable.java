package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.Consumer;
import com.tobilko.lab4.consumer.entity.lock.DequeState;
import com.tobilko.lab4.consumer.entity.Item;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Deque;
import java.util.concurrent.locks.Condition;
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
    @SneakyThrows
    public void run() {
        final Lock lock = compositeLock.getLock();
        final Condition dequeNotEmptyCondition = compositeLock.getConditions().get(DequeState.NOT_EMPTY);
        final Condition dequeNotFullCondition = compositeLock.getConditions().get(DequeState.NOT_FULL);

        while (true) {
            // validate the size of the deque
            lock.lock();
            try {
                if (deque.isEmpty()) {
                    System.out.printf("%s: I am waiting for an item in the deque...\n", consumer);
                    dequeNotEmptyCondition.await();
                }
            } finally {
                lock.unlock();
            }

            // try to get an item from the deque
            final Item item;
            lock.lock();
            try {
                System.out.printf("%s: I have taken an item from the deque.\n", consumer);
                item = deque.pollFirst();
                dequeNotFullCondition.signal();
            } finally {
                lock.unlock();
            }

            // consume the item
            consumer.consume(item);
        }

    }

}
