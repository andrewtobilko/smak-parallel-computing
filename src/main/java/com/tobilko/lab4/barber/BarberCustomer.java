package com.tobilko.lab4.barber;

import com.tobilko.lab2.util.Identifiable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class BarberCustomer implements Identifiable<Integer> {

    private final Integer id;

    public boolean tryToSitOnChair(BarberChair chair) {
        if (chair.isFree()) {
            chair.setCurrentCustomer(this);
            return true;
        }

        return false;
    }

}
