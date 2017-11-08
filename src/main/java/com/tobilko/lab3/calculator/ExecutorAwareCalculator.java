package com.tobilko.lab3.calculator;

import com.tobilko.lab3.stuff.Executable;
import com.tobilko.lab3.stuff.ExecutorAware;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.Executor;

/**
 * Created by Andrew Tobilko on 11/8/17.
 */
@RequiredArgsConstructor
public abstract class ExecutorAwareCalculator implements ExecutorAware, Executable, Runnable {

    @Getter
    private final Executor executor;

    @Override
    public final void execute() {
        executor.execute(this);
    }

}
