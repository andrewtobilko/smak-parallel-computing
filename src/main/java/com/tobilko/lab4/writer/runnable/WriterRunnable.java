package com.tobilko.lab4.writer.runnable;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.util.RandomUtil;
import com.tobilko.lab4.writer.entity.Book;
import com.tobilko.lab4.writer.entity.BookUnderControl;
import com.tobilko.lab4.writer.entity.Writer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public class WriterRunnable implements Runnable {

    private final WriterGenerator writerGenerator = new WriterGenerator();
    private final AtomicInteger writeCount = new AtomicInteger();

    private final BookUnderControl bookUnderControl;

    @Override
    @SneakyThrows
    public void run() {

        final Book book = bookUnderControl.getBook();

        final Semaphore bookSemaphore = bookUnderControl.getBookSemaphore();
        final Semaphore writeSemaphore = bookUnderControl.getWriteSemaphore();
        final Semaphore tryReadSemaphore = bookUnderControl.getTryReadSemaphore();

        while (true) {
            // ENTRY SECTION
            // avoid race conditions between writers
            writeSemaphore.acquire();

            // check if you are the first writer
            if (writeCount.incrementAndGet() == 1) {
                tryReadSemaphore.acquire(); // lock readers
            }
            writeSemaphore.release();


            // CRITICAL SECTION
            // acquire only for yourself
            bookSemaphore.acquire();
            writerGenerator.generate().writeTo(book);
            bookSemaphore.release();


            // EXIT SECTION
            // avoid race conditions between writers
            writeSemaphore.acquire();

            // check if you are the last writer
            if (writeCount.decrementAndGet() == 0) {
                tryReadSemaphore.release(); // unlock readers
            }
            writeSemaphore.release();
        }

    }

    private static class WriterGenerator implements Generator<Writer> {

        @Override
        public Writer generate() {
            return new Writer(RandomUtil.getRandomId());
        }

        @Override
        public Integer getId() {
            return RandomUtil.getRandomId();
        }

    }

}
