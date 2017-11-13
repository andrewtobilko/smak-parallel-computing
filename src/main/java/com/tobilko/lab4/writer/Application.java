package com.tobilko.lab4.writer;

import com.tobilko.lab4.philosopher.Folk;
import com.tobilko.lab4.philosopher.Philosopher;
import com.tobilko.lab4.philosopher.PhilosopherRunnable;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
public final class Application {

    public static void main(String[] args) {
        final int SIZE = 5;

        final Philosopher[] philosophers = new Philosopher[SIZE];
        final Folk[] folks = new Folk[SIZE];

        // initialise folks
        for (int i = 0; i < folks.length; ++i) {

        }

        // initialise philosophers
        for (int i = 0; i < philosophers.length; ++i) {

        }

        // start philosopher threads
        for (Philosopher philosopher : philosophers) {
            new Thread(new PhilosopherRunnable(philosopher)).start();
        }


    }

}
