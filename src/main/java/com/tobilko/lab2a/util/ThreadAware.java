package com.tobilko.lab2a.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@RequiredArgsConstructor(staticName = "of")
public class ThreadAware<T> {

    private final T instance;
    private final Thread thread;
    
}
