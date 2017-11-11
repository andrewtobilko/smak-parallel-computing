package com.tobilko.lab4.barber.thread;

import com.tobilko.lab4.barber.entity.Barbershop;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.TimeUnit;

import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;
import static com.tobilko.lab4.barber.util.BarberUtil.TIME_TO_WAIT_FOR_A_NEW_CUSTOMER;

/**
 * barber ->
 *      is anybody in the waiting room ?
 *          take one and make a haircut
 *          sleep
 *
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberThread extends Thread { // TODO: 11/11/17 thread

    private final Barbershop barbershop;

    @Override
    public void run() {

        while (getNumberOfClientsExpected().get() > 0) {
            final BarberCustomer customer;
            try {

                customer = barbershop.getRoom().poll(TIME_TO_WAIT_FOR_A_NEW_CUSTOMER, TimeUnit.MINUTES);
                try {
                    barbershop.getBarber().makeHaircut(customer);
                } catch (InterruptedException e) {
                    System.out.println("someone interrupted the barber while he is working");
                }
            } catch (InterruptedException e) {
                System.out.println("someone interrupted the barber while he was waiting for a new customer");

            }
        }

    }

}
