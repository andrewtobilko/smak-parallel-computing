package com.tobilko.lab4.barber;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@RequiredArgsConstructor
public final class WaitingRoom {

    private final BlockingQueue queue;

}
