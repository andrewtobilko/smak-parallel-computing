package com.tobilko.lab4.barber.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor(staticName = "of")
public final class BarberWaitingRoom {

    @Getter
    private final BlockingQueue<BarberCustomer> line;

    public boolean isEmpty() {
        return line.isEmpty();
    }

}
