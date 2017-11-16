package com.tobilko.lab4.writer.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab4.consumer.util.SleepUtil.sleepRandomSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class Writer implements Identifiable<Integer> {

    @Getter
    private final Integer id;

    public void writeTo(Book book) {
        sleepRandomSeconds(this::logWritingInterruption);
    }

    private void logWritingInterruption() {
        System.out.printf("Someone interrupted %s while he was writing the book.\n", this);
    }

    @Override
    public String toString() {
        return getFormattedString();
    }

}
