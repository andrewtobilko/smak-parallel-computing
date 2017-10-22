package com.tobilko.lab2.queue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor
public class BasicDeque implements Deque {

    private Integer id;
    private java.util.Deque processes;

}
