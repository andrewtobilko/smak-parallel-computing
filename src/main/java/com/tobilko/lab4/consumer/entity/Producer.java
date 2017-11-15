package com.tobilko.lab4.consumer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab4.consumer.util.SleepUtil.sleepRandomSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Producer implements Identifiable<Integer> {

    @Getter
    private final Integer id;

    private final ProducerGenerator generator = new ProducerGenerator();

    public Item produce() {
        sleepRandomSeconds(this::logProductionInterruption);
        return generator.generate();
    }

    private void logProductionInterruption() {
        System.out.printf("Someone interrupted %s while he was producing an item...\n", this);
    }

    @Override
    public String toString() {
        return getFormattedString();
    }

    private final static class ProducerGenerator {

        public Item generate() {
            return new Item(getRandomId());
        }

    }

}
