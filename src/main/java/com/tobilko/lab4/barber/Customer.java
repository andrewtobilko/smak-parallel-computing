package com.tobilko.lab4.barber;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 11/5/17.
 */
@Getter
@RequiredArgsConstructor
public final class Customer {

    private final Integer id;

    /*
        customer ->
        is the barber sleeping ?
            wake him up and get a haircut
            is the waiting room full ?
                leave
                sit and wait
     */

}
