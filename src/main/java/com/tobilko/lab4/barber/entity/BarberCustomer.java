package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class BarberCustomer implements Identifiable<Integer> {

    private final Integer id;

    public boolean tryToSitOnTheChair(BarberChair chair) {
        logChairAttempt();

        if (chair.isFree()) {
            chair.setCurrentCustomer(this);
            return true;
        }

        return false;
    }

    private void logChairAttempt() {
        System.out.printf("%s is trying to sit on the chair...", this);
    }


    public boolean tryToJoinTheLine(BlockingQueue<BarberCustomer> line) {
        logLineAttempt();

        return line.offer(this);
    }

    private void logLineAttempt() {
        System.out.printf("%s is trying to join the line...", this);
    }

    @Override
    public String toString() {
        return format("%s [%d]", BarberCustomer.class.getSimpleName(), id);
    }

}
