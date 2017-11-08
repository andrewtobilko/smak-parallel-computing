package com.tobilko.lab4.barber;

import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@RequiredArgsConstructor
public final class Barber {

    private final long haircutTime;

    public void makeHaircut(Customer customer) throws InterruptedException {
        Thread.sleep(haircutTime * 1000);
    }

}
