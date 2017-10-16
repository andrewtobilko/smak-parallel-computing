package com.tobilko.lab2.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcess implements Process {

    private final Integer id;
    private final int timeToNextGeneration;

}