package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab4.barber.util.BarberUtil.TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE;
import static java.lang.String.*;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class Barber implements Identifiable<Integer> {

    private final Integer id;
    private final long haircutTime;

    public void makeHaircut(BarberCustomer customer) throws InterruptedException {
        logCustomer(customer);
        Thread.sleep(haircutTime * 2000);
    }

    private void logCustomer(BarberCustomer customer) {
        System.out.printf("%s is cutting %s's hair...\n", this, customer);
    }

    public BarberCustomer tryToCallInCustomerFromWaitingRoom(BarberWaitingRoom room) throws InterruptedException {
        return room.getLine().poll(TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE, MINUTES);
    }

    @Override
    public String toString() {
        return format("%s [%d, %ds]", Barber.class.getSimpleName(), id, haircutTime);
    }

}
