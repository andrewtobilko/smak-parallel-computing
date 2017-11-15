package com.tobilko.lab4.consumer.entity;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
public final class Producer {

    private final ProducerGenerator generator = new ProducerGenerator();

    public Item produce() {
        return generator.generate();
    }

    private final static class ProducerGenerator {

        public Item generate() {
            return new Item(getRandomId());
        }

    }

}
