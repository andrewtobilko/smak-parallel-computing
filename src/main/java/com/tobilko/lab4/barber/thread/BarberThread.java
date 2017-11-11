package com.tobilko.lab4.barber.thread;

import com.tobilko.lab4.barber.entity.Barbershop;
import com.tobilko.lab4.barber.entity.BarberCustomer;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab4.barber.util.BarberUtil.ClientCounter.getNumberOfClientsExpected;

/**
 * barber ->
 *      is anybody in the waiting room ?
 *          take one and make a haircut
 *          sleep
 *
 * Created by Andrew Tobilko on 11/11/17.
 */
// TODO: 11/11/17 thread
// TODO: 11/11/17 exceptions
@RequiredArgsConstructor
public final class BarberThread extends Thread {

    private final Barbershop barbershop;

    @Override
    public void run() {

        while (getNumberOfClientsExpected().get() > 0) {
            final BarberCustomer customer;
            try {

                if (barbershop.getRoom().isEmpty()) {
                    // TODO: 11/11/17  get a lock from barbershop and subscribe on the condition
                }
                customer = barbershop.getBarber().tryToCallInCustomerFromWaitingRoom(barbershop.getRoom());
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
