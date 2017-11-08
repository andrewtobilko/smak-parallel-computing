package com.tobilko.lab3.stuff;

import java.util.concurrent.Executor;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
public interface ExecutorAware {

    Executor getExecutor();

}
