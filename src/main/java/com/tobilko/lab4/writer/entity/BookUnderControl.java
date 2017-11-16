package com.tobilko.lab4.writer.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Andrew Tobilko on 11/16/17.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public final class BookUnderControl {

    private final Book book;

    private final Semaphore bookSemaphore;

    private final Semaphore readSemaphore;
    private final Semaphore writeSemaphore;
    private final Semaphore tryReadSemaphore;

    private final AtomicInteger readCount = new AtomicInteger();
    private final AtomicInteger writeCount = new AtomicInteger();

}
