package com.tobilko.lab4.consumer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab4.consumer.util.SleepUtil.sleepRandomSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Consumer implements Identifiable<Integer> {

    @Getter
    private final Integer id;

    public void consume(final Item item) {
        sleepRandomSeconds(() -> logConsumptionInterruption(item));
    }

    private void logConsumptionInterruption(Item item) {
        System.out.printf("Someone interrupted %s while he was consuming %s...\n", this, item);
    }

}
