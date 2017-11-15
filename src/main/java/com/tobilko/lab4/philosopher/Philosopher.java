package com.tobilko.lab4.philosopher;

import com.tobilko.lab2.util.Identifiable;
import javafx.util.Pair;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/13/17.
 */
@Getter
@RequiredArgsConstructor
public final class Philosopher implements Identifiable<Integer> {

    private final Integer id;
    private final Pair<Folk, Folk> folks;

    public void eat() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logEatingInterruption();
        }
    }

    private void logEatingInterruption() {
        System.err.printf("Someone interrupted %s while he was eating...\n", this);
    }

    public void think() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            logThinkingInterruption();
        }
    }

    private void logThinkingInterruption() {
        System.err.printf("Someone interrupted %s while he was eating...\n", this);
    }

    @Override
    public String toString() {
        return format("%s [%d]", Philosopher.class.getSimpleName(), id);
    }

}
