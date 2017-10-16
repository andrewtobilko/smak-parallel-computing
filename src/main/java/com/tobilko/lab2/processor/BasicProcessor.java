package com.tobilko.lab2.processor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcessor implements Processor {

    private final int id;
    private final int processingTime;

}
