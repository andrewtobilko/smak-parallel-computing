package com.tobilko.lab4.barber;

import lombok.RequiredArgsConstructor;

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

}
