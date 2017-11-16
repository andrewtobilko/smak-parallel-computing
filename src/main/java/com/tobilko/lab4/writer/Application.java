package com.tobilko.lab4.writer;

import com.tobilko.lab4.writer.entity.Book;
import com.tobilko.lab4.writer.entity.BookUnderControl;
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

    public static final int PERMITS = 1;
    public static final int THREADS = 8;

    public static void main(String[] args) {
        // resource
        final Book book = new Book(getRandomId());

        // semaphores
        final Semaphore readSemaphore = new Semaphore(PERMITS);
        final Semaphore writeSemaphore = new Semaphore(PERMITS);
        final Semaphore tryReadSemaphore = new Semaphore(PERMITS);
        final Semaphore bookSemaphore = new Semaphore(PERMITS);

        // a wrapper over semaphores and a book
        final BookUnderControl bookUnderControl = BookUnderControl.of(book, bookSemaphore, readSemaphore, writeSemaphore, tryReadSemaphore);

        // start threads
        final ExecutorService service = Executors.newFixedThreadPool(THREADS);

        for (int i = 0; i < THREADS / 2; i++) {
            service.execute(new ReaderRunnable(bookUnderControl));
            service.execute(new WriterRunnable(bookUnderControl));
        }

        service.shutdown();

    }

}
