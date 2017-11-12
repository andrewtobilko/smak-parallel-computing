package com.tobilko.lab4.barber.entity;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import static com.tobilko.lab4.barber.util.BarberUtil.TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class Barber implements Identifiable<Integer> {

    private final Integer id;
    private final long haircutTime;

    public void makeHaircut(BarberCustomer customer, BarberChair chair) throws InterruptedException {
        logCustomer(customer);
        Thread.sleep(haircutTime * 2000);
        freeChairFromCustomer(chair, customer);
    }

    private void logCustomer(BarberCustomer customer) {
        System.out.printf("%s is cutting %s's hair...\n", this, customer);
    }

    private void freeChairFromCustomer(BarberChair chair, BarberCustomer customer) {
        chair.getChairFree();
        logGettingChairFree(customer);
    }

    private void logGettingChairFree(BarberCustomer customer) {
        System.err.printf("%s has got a new haircut. He is leaving in a good moon.", customer);
    }

    @SneakyThrows
    public BarberCustomer tryToCallInCustomerFromWaitingRoom(BarberWaitingRoom room) {
        return room.getLine().poll(TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE, MINUTES);
    }

    @Override
    public String toString() {
        return format("%s [%d, %ds]", Barber.class.getSimpleName(), id, haircutTime);
    }

}
