package com.tobilko.lab4.barber.entity;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

import static com.tobilko.lab4.barber.util.BarberUtil.TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE;
import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@RequiredArgsConstructor
public final class Barber {

    private final long haircutTime;

    public void makeHaircut(BarberCustomer customer) throws InterruptedException {
        logCustomer(customer);
        Thread.sleep(haircutTime * 1000);
    }

    private void logCustomer(BarberCustomer customer) {
        System.out.printf("%s is cutting %s's hair...", Barber.class.getSimpleName(), customer);
    }

    public BarberCustomer tryToCallInCustomerFromLine(BlockingQueue<BarberCustomer> line) throws InterruptedException {
        return line.isEmpty() ?
                null :
                line.poll(TIME_TO_WAIT_FOR_A_NEW_CUSTOMER_FROM_THE_LINE, MINUTES);
    }

}
