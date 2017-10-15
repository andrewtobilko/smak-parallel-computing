package com.tobilko.lab2.processor.thread;

import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
@Getter
@RequiredArgsConstructor
public final class ProcessorThread extends Thread {

    private final Processor processor;



}
