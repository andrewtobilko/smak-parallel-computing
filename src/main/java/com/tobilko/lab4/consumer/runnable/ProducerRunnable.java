package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.Item;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ProducerRunnable implements Runnable {

    private final Lock lock;
    private final Deque<Item> deque;

    @Override
    public void run() {



    }

}
