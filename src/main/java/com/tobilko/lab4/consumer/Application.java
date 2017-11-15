package com.tobilko.lab4.consumer;

import com.tobilko.lab4.consumer.entity.Item;
import com.tobilko.lab4.consumer.runnable.ConsumerRunnable;
import com.tobilko.lab4.consumer.runnable.ProducerRunnable;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
public final class Application {

    public static void main(String[] args) {
        // TODO: 11/15/17 ReentrantLock

        final Lock lock = new ReentrantLock();
        final Deque<Item> deque = new LinkedList<>(); // TODO: 11/15/17 choose the impl

        new Thread(new ConsumerRunnable(deque)).start();
        new Thread(new ProducerRunnable(deque)).start();

    }

}
