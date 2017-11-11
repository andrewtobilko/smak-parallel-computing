package com.tobilko.lab4.barber.entity;

import com.tobilko.lab4.barber.util.LockWithCondition;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.locks.Lock;

/**
 * Created by Andrew Tobilko on 11/11/17.
 */
@RequiredArgsConstructor
public final class BarberChair {

    private volatile BarberCustomer currentCustomer;
    private final LockWithCondition lockWithCondition;

    public boolean isFree() {
        return currentCustomer == null;
    }

    public void setCurrentCustomer(BarberCustomer currentCustomer) throws InterruptedException {
        this.currentCustomer = currentCustomer;
        notifyBarberAboutCondition();
    }

    private void notifyBarberAboutCondition() throws InterruptedException {
        final Lock lock = lockWithCondition.getLock();

        lock.lockInterruptibly();
        try {
            lockWithCondition.getCondition().signal();
        } finally {
            lock.unlock();
        }
    }

}
