package com.tobilko.lab2a;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Deque;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
public final class Lab2 {
    public static void main(String[] args) {
        final int NUMBER_OF_GENERATORS = 2;
        final int[] PROCESS_NUMBERS_TO_GENERATE = {5, 5};

        for (int i = 0; i < NUMBER_OF_GENERATORS; i++) {
            final GeneratorRunnable<Process> generatorRunnable = new ProcessGeneratorRunnable(
                    new BasicProcessGenerator(),
                    PROCESS_NUMBERS_TO_GENERATE[i]
            );

            generatorRunnable.
        }
        final GeneratorRunnable<Process> generatorRunnable = new ProcessGeneratorRunnable(
                new BasicProcessGenerator(),
                100
        );
    }
}

class Processor {
    private int id;
    private int processingTime;
}
@RequiredArgsConstructor
class ProcessorRunnable implements Runnable {

    private final Processor processor;

    @Override
    public void run() {
        while (true) {
            System.out.println("The processor is running...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
class Queue {
    private Deque<Process> processes;
}

interface Generator<T> {
    T generate();
}
final class BasicProcessGenerator implements Generator<Process> {

    @Override
    public Process generate() {
        return new BasicProcess(
                RandomUtil.getRandomId(),
                RandomUtil.getRandomTimeInSeconds()
        );
    }

}

interface GeneratorRunnable<T extends Process> extends Runnable {
    Generator<T> getGenerator();
}
@Getter
@RequiredArgsConstructor
final class ProcessGeneratorRunnable implements GeneratorRunnable<Process> {

    private final Generator<Process> generator;
    private @NonNull int limit;

    @Override
    public void run() {
        while (true) {
            System.out.println("The generator is generating...");
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}


interface Identifiable<T extends Serializable> {
    T getId();
}

interface Process extends Identifiable<Integer> {
    int getTimeToNextGeneration();
}

@Getter
@RequiredArgsConstructor
final class BasicProcess implements Process {
    private final int id;
    private final int timeToNextGeneration;
}

class Unit {
    private Queue queue;
    private ThreadAware<Processor> processorThreadAware;
    private ThreadAware<Generator> generatorThreadAware;

}

@Getter
@RequiredArgsConstructor(staticName = "of")
class ThreadAware<T> {
    private final T instance;
    private final Thread thread;
}

interface UnitRunnable extends Runnable {

}

class RandomUtil {

    public static int getRandomId() {
        return getRandomIntBetween(0, 1000);
    }

    public static int getRandomTimeInSeconds() {
        return getRandomIntBetween(1, 11);
    }

    private static int getRandomIntBetween(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }

}
