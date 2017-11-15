package com.tobilko.lab4.writer.runnable;

import com.tobilko.lab4.writer.entity.Writer;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Semaphore;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public class WriterRunnable implements Runnable {

    private final Writer writer;
    private final Semaphore semaphore;

    @Override
    public void run() {
        System.out.println("WriterRunnable");
    }

}
