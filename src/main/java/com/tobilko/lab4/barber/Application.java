package com.tobilko.lab4.barber;

import com.tobilko.lab4.barber.entity.Barbershop;
import com.tobilko.lab4.barber.manager.BarberManager;
import com.tobilko.lab4.barber.manager.ConsumerManager;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
public final class Application {

    public static void main(String[] args) {
        final Barbershop barbershop = Barbershop.createStandardBarbershop();

        new Thread(new BarberManager(barbershop)).start();
        new Thread(new ConsumerManager(barbershop)).start();
    }

}
