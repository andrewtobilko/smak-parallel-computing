package com.tobilko.lab2a.queue;

import com.tobilko.lab2a.process.Process;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Deque;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public class BasicQueue implements Queue {

    private int id;
    private Deque<Process> processes;

}
