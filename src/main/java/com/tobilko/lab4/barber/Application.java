package com.tobilko.lab4.barber;

import com.tobilko.lab4.barber.entity.Barbershop;
import com.tobilko.lab4.barber.runnable.BarberRunnable;
import com.tobilko.lab4.barber.runnable.ConsumerRunnable;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
public final class Application {

    public static void main(String[] args) {
        final Barbershop barbershop = Barbershop.createStandardBarbershop();

        new Thread(new BarberRunnable(barbershop)).start();
        new Thread(new ConsumerRunnable(barbershop)).start();
    }

}
