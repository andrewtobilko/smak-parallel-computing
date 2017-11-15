package com.tobilko.lab4.writer;

import com.tobilko.lab4.philosopher.Folk;
import com.tobilko.lab4.philosopher.Philosopher;
import com.tobilko.lab4.philosopher.PhilosopherRunnable;
import javafx.util.Pair;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
public final class Application {

    public static final int SIZE = 5;

    public static void main(String[] args) {

        final Philosopher[] philosophers = new Philosopher[SIZE];
        final Folk[] folks = new Folk[SIZE];

        // initialise folks
        for (int i = 0; i < folks.length; ++i) {
            folks[i] = new Folk(getRandomId());
            System.out.printf("%s has been generated!\n", folks[i]);
        }

        // initialise philosophers
        for (int i = 0; i < philosophers.length; ++i) {
            philosophers[i] = new Philosopher(getRandomId(), calculateFolkPairForPhilosopherByIndex(i, folks));
            System.out.printf("%s has been generated!\n", philosophers[i]);
        }

        // start philosopher threads
        for (Philosopher philosopher : philosophers) {
            new Thread(new PhilosopherRunnable(philosopher)).start();
        }

    }

    private static Pair<Folk, Folk> calculateFolkPairForPhilosopherByIndex(int philosopherIndex, Folk[] folks) {
        final int i = (philosopherIndex + SIZE - 1) % SIZE;
        final int j = (philosopherIndex + 1) % SIZE;

        return philosopherIndex + 1 == SIZE ?
                new Pair<>(folks[i], folks[j]) :
                new Pair<>(folks[j], folks[i]);
    }

}

