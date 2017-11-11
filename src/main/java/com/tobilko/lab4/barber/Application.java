package com.tobilko.lab4.barber;

import com.tobilko.lab4.barber.entity.Barbershop;
import com.tobilko.lab4.barber.thread.BarberThread;
import com.tobilko.lab4.barber.thread.ConsumerThread;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
public final class Application {

    public static void main(String[] args) {
        final Barbershop barbershop = Barbershop.createStandardBarbershop();

        new BarberThread(barbershop).start();
        new ConsumerThread(barbershop).start();
    }

}
