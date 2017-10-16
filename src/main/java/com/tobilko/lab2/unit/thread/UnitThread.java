package com.tobilko.lab2.unit.thread;

import com.tobilko.lab2.unit.UnitDec;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.ColourUtil.Color.BLACK;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
@RequiredArgsConstructor
public final class UnitThread extends Thread {

    @Getter
    private final UnitDec unit;

    @Override
    public void run() {
        println(format("%s is gonna start...", unit), BLACK);
        unit.process();
    }

}
