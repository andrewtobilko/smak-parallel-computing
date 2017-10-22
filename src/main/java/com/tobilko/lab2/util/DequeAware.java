package com.tobilko.lab2.util;

import com.tobilko.lab2.queue.Deque;

/**
 * Created by Andrew Tobilko on 10/20/17.
 */
public interface DequeAware {

    Deque getDeque();

    void setDeque(Deque deque);

}
