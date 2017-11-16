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

import static com.tobilko.lab4.consumer.util.SleepUtil.sleepRandomSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public class WriterRunnable implements Runnable {

    private final WriterGenerator writerGenerator = new WriterGenerator();
    private final BookUnderControl bookUnderControl;

    @Override
    @SneakyThrows
    public void run() {

        final Book book = bookUnderControl.getBook();

        final Semaphore bookSemaphore = bookUnderControl.getBookSemaphore();
        final Semaphore writeSemaphore = bookUnderControl.getWriteSemaphore();
        final Semaphore tryReadSemaphore = bookUnderControl.getTryReadSemaphore();

        final AtomicInteger writeCount = bookUnderControl.getWriteCount();

        while (true) {
            final Writer writer = writerGenerator.generate();
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

            writer.writeTo(book);
            System.out.printf("%s wrote something to the book.\n", writer);

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
            sleepRandomSeconds(this::logInterruption);
            return new Writer(RandomUtil.getRandomId());
        }

        private void logInterruption() {
            System.out.println("Someone interrupted the generator while he was generating...");
        }

        @Override
        public Integer getId() {
            return RandomUtil.getRandomId();
        }

    }

}
