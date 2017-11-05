package com.tobilko.lab4.barber;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.util.RandomUtil;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
public final class Application {

    public static void main(String[] args) {

        final Barber barber = new Barber(5);
        final WaitingRoom room = new WaitingRoom(new ArrayBlockingQueue<Customer>(5));

        final ConsumerGenerator consumerGenerator = new ConsumerGenerator();

        while(true) {

        }

    }

    static class ConsumerGenerator implements Generator<Customer> {

        @Override
        public Customer generate() {
            return new Customer(RandomUtil.getRandomId());
        }

        @Override
        public Integer getId() {
            return RandomUtil.getRandomId();
        }

    }

    static class ConsumerManager implements Runnable {

        @Override
        public void run() {

        }

    }

}
