package com.tobilko.lab4.philosopher.entity;

import com.tobilko.lab2.util.Identifiable;
import javafx.util.Pair;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 11/13/17.
 */
@Getter
@RequiredArgsConstructor
public final class Philosopher implements Identifiable<Integer> {

    private final Integer id;
    private final Pair<Folk, Folk> folks;

    public void think() {
        try {
            TimeUnit.SECONDS.sleep(getRandomTimeInSeconds());
        } catch (InterruptedException e) {
            logThinkingInterruption();
        }
    }

    public void tryToEat() {
        synchronized (folks.getKey()) {
            logFirstFolkPickedUp();
            synchronized (folks.getValue()) {
                logSecondFolkPickedUp();
                eat();
            }
        }
    }

    private void eat() {
        try {
            logEating();
            TimeUnit.SECONDS.sleep(getRandomTimeInSeconds());
        } catch (InterruptedException e) {
            logEatingInterruption();
        }
    }

    private void logEating() {
        System.out.printf("\t\t\t%s: I am eating...\n", this);
    }

    private void logEatingInterruption() {
        System.err.printf("Someone interrupted %s while he was eating...\n", this);
    }

    private void logThinkingInterruption() {
        System.err.printf("Someone interrupted %s while he was eating...\n", this);
    }

    private void logFirstFolkPickedUp() {
        System.out.printf("\t%s: A folk has been picked up. Waiting for the second one...\n", this);
    }

    private void logSecondFolkPickedUp() {
        System.out.printf("\t\t%s: The second folk has been picked up!\n", this);
    }

    @Override
    public String toString() {
        return getFormattedString();
    }

}
