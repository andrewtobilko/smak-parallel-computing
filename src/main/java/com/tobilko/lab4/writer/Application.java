package com.tobilko.lab4.writer;

import com.tobilko.lab4.writer.entity.Reader;
import com.tobilko.lab4.writer.entity.Writer;
import com.tobilko.lab4.writer.runnable.ReaderRunnable;
import com.tobilko.lab4.writer.runnable.WriterRunnable;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;

/**
 * Created by Andrew Tobilko on 11/10/17.
 */
public final class Application {

    public static final int PERMITS = 5;
    public static final int THREADS = 2;


    @SneakyThrows
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(PERMITS);

        final ExecutorService service = Executors.newFixedThreadPool(THREADS);

        service.execute(new ReaderRunnable(new Reader(getRandomId()), semaphore));
        service.execute(new WriterRunnable(new Writer(getRandomId()), semaphore));

        service.shutdown();
        service.awaitTermination(1, TimeUnit.SECONDS);
    }

}

