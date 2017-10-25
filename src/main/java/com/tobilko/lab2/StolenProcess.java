package com.tobilko.lab2;

import com.tobilko.lab2.process.Process;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/25/17.
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class StolenProcess implements StolenItem<Process, Deque<Process>> {

    private final Deque<Process> stolenFrom;
    private final Process item;

    public static StolenItem<Process, Deque<Process>> of(Deque<Process> deque, Process item) {
        return new StolenProcess(deque, item);
    }

}
