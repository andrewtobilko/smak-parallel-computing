package com.tobilko.lab4.writer.runnable;

import com.tobilko.lab4.writer.entity.Reader;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Semaphore;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ReaderRunnable implements Runnable {

    private final Reader reader;
    private final Semaphore semaphore;

    @Override
    public void run() {
        System.out.println("ReaderRunnable");
    }

}
