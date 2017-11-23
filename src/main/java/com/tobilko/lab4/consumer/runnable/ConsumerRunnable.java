package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.Consumer;
import com.tobilko.lab4.consumer.entity.Item;
import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.lock.DequeState;
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
    public void run() {

        while (true) {
            // validate the size of the deque
            validateDequeSize();

            // try to get an item from the deque
            final Item item = getItemFromDeque();

            // consume the item
            consumer.consume(item);
        }

    }

    @SneakyThrows(InterruptedException.class)
    private void validateDequeSize() {
        final Lock lock = compositeLock.getLock();
        final Condition dequeNotEmptyCondition = compositeLock.getConditions().get(DequeState.NOT_EMPTY);

        lock.lock();
        try {
            if (deque.isEmpty()) {
                logWaitingForNotEmptyDeque();
                dequeNotEmptyCondition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    private Item getItemFromDeque() {
        final Lock lock = compositeLock.getLock();
        final Condition dequeNotFullCondition = compositeLock.getConditions().get(DequeState.NOT_FULL);

        final Item item;
        lock.lock();
        try {
            logTakingFromDeque();
            item = deque.pollFirst();
            dequeNotFullCondition.signal();
        } finally {
            lock.unlock();
        }
        return item;
    }

    private void logTakingFromDeque() {
        System.out.printf("%s: I've taken an item from the deque.\n", consumer);
    }

    private void logWaitingForNotEmptyDeque() {
        System.out.printf("%s: I'm waiting for an item in the deque...\n", consumer);
    }

}
