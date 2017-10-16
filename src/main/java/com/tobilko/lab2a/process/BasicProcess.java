package com.tobilko.lab2a.process;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public final class BasicProcess implements Process {

    private final int id;
    private final int timeToNextGeneration;

}