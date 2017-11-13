package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class Barber implements Identifiable<Integer> {

    private final Integer id;
    private final long haircutTime;

    public void makeHaircut(BarberCustomer customer) {
        logCustomer(customer);
        emulateMakingHaircut();
    }

    private void logCustomer(BarberCustomer customer) {
        System.out.printf("%s is cutting %s's hair...\n", this, customer);
    }

    private void emulateMakingHaircut() {
        try {
            TimeUnit.SECONDS.sleep(haircutTime);
        } catch (InterruptedException e) {
            logBarberInterruption();
        }
    }

    private void logBarberInterruption() {
        System.err.println("Someone interrupted the barber while he was working...");
    }

    public BarberCustomer tryToCallInCustomerFromWaitingRoom(BarberWaitingRoom room) {
        return room.getLine().poll();
    }

    @Override
    public String toString() {
        return format("%s [%d, %ds]", Barber.class.getSimpleName(), id, haircutTime);
    }

}
