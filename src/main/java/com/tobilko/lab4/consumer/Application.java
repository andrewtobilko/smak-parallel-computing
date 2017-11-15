package com.tobilko.lab4.consumer;

import com.google.common.collect.ImmutableMap;
import com.tobilko.lab4.consumer.entity.*;
import com.tobilko.lab4.consumer.entity.lock.CompositeLock;
import com.tobilko.lab4.consumer.entity.lock.DequeState;
import com.tobilko.lab4.consumer.runnable.ConsumerRunnable;
import com.tobilko.lab4.consumer.runnable.ProducerRunnable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
public final class Application {

    public static final int MAX_DEQUE_SIZE = 5;

    public static void main(String[] args) {

        // init a consumer + a producer
        final Consumer consumer = new Consumer(getRandomId());
        final Producer producer = new Producer(getRandomId());

        // init a lock + conditions
        final Lock lock = new ReentrantLock();
        final CompositeLock<DequeState> compositeLock = CompositeLock.of(lock, ImmutableMap.of(
                DequeState.NOT_EMPTY, lock.newCondition(),
                DequeState.NOT_FULL, lock.newCondition())
        );

        // init a deque
        final Deque<Item> deque = new LinkedList<>();

        // start threads
        new Thread(new ConsumerRunnable(consumer, deque, compositeLock)).start();
        new Thread(new ProducerRunnable(producer, deque, compositeLock)).start();

    }

}
