package com.tobilko.lab4.writer.runnable;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.util.RandomUtil;
import com.tobilko.lab4.writer.entity.Book;
import com.tobilko.lab4.writer.entity.BookUnderControl;
import com.tobilko.lab4.writer.entity.Reader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import static com.tobilko.lab4.consumer.util.SleepUtil.sleepRandomSeconds;

/**
 * Created by Andrew Tobilko on 11/15/17.
 */
@RequiredArgsConstructor
public final class ReaderRunnable implements Runnable {

    private final ReaderGenerator readerGenerator = new ReaderGenerator();
    private final BookUnderControl bookUnderControl;

    @Override
    @SneakyThrows
    public void run() {

        final Book book = bookUnderControl.getBook();

        final Semaphore bookSemaphore = bookUnderControl.getBookSemaphore();
        final Semaphore readSemaphore = bookUnderControl.getReadSemaphore();
        final Semaphore tryReadSemaphore = bookUnderControl.getTryReadSemaphore();

        final AtomicInteger readCount = bookUnderControl.getReadCount();

        while(true) {
            final Reader reader = readerGenerator.generate();

            // ENTRY SECTION
            tryReadSemaphore.acquire();     // the reader is trying to enter
            readSemaphore.acquire();        // avoid race condition with other readers

            // check if you are the first reader
            if (readCount.incrementAndGet() == 1) {
                bookSemaphore.acquire();
            }
            readSemaphore.release();
            tryReadSemaphore.release();

            // CRITICAL SECTION
            reader.readFrom(book);
            System.out.printf("%s read the book.\n", reader);

            // EXIT SECTION
            // avoid race condition with readers
            readSemaphore.acquire();

            // check if you are the last reader leaving
            if (readCount.decrementAndGet() == 0) {
                bookSemaphore.release();
            }

            readSemaphore.release();

        }

    }

    private static class ReaderGenerator implements Generator<Reader> {

        @Override
        public Reader generate() {
            sleepRandomSeconds(this::logInterruption);
            return new Reader(RandomUtil.getRandomId());
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
