package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.Item;
import com.tobilko.lab4.consumer.entity.Producer;
import com.tobilko.lab4.consumer.entity.lock.DequeState;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.tobilko.lab4.consumer.Application.MAX_DEQUE_SIZE;
import static com.tobilko.lab4.consumer.entity.lock.DequeState.NOT_EMPTY;
import static com.tobilko.lab4.consumer.entity.lock.DequeState.NOT_FULL;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ProducerRunnable implements Runnable {

    private final Producer producer;
    private final Deque<Item> deque;

    private final CompositeLock<DequeState> compositeLock;

    @Override
    @SneakyThrows
    public void run() {
        final Lock lock = compositeLock.getLock();
        final Condition dequeNotFullCondition = compositeLock.getConditions().get(NOT_FULL);
        final Condition dequeNotEmptyCondition = compositeLock.getConditions().get(NOT_EMPTY);

        while(true) {
            // validate the size of the deque
            lock.lock();
            try {
                if (deque.size() == MAX_DEQUE_SIZE) {
                    System.out.printf("%s: I am waiting for the time when I can put an item into the deque...\n", producer);
                    dequeNotFullCondition.await();
                }
            } finally {
                lock.unlock();
            }

            // generate a new item
            final Item item = producer.produce();

            // put the generated item into the deque
            lock.lock();
            try {

                deque.addLast(item);
                System.out.printf("%s: I have added %s to the deque.\n", producer, item);
                dequeNotEmptyCondition.signal();
            } finally {
                lock.unlock();
            }
        }

    }

}
