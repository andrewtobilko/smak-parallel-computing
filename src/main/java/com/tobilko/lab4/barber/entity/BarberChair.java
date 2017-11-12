package com.tobilko.lab4.barber.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberChair {

    @Getter
    private volatile BarberCustomer currentCustomer;
    private final BarberLock lock;

    public boolean isFree() {
        return currentCustomer == null;
    }

    public void setCurrentCustomer(BarberCustomer currentCustomer) {
        this.currentCustomer = currentCustomer;
        notifyBarberAboutCondition();
    }

    private void notifyBarberAboutCondition() {
        final Lock lock = this.lock.getLock();

        lock.lock();
        try {
            this.lock.getCondition().signal();
        } finally {
            lock.unlock();
        }
    }

    public void getChairFree() {
        currentCustomer = null;
    }

}
