package com.tobilko.lab2.processor.thread;

import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.ColourUtil.Color.BLACK;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
@RequiredArgsConstructor
public final class ProcessorThread extends Thread {

    @Getter
    private final Processor processor;

    @Override
    public void run() {
        println(format("%s is gonna start...", processor), BLACK);
        processor.process();
    }

}
