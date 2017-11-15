package com.tobilko.lab4.consumer.runnable;

import com.tobilko.lab4.consumer.entity.Item;
import lombok.RequiredArgsConstructor;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ProducerRunnable implements Runnable {

    private final Deque<Item> deque;

    @Override
    public void run() {
        System.out.println("ProducerRunnable");
    }

}
