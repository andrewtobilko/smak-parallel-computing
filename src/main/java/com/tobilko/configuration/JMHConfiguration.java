package com.tobilko.configuration;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * Created by Andrew Tobilko on 9/16/17.
 */
public class JMHConfiguration {

    public static void startWithDefaultBenchmarkConfigurationByClass(Class<?> c) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(c.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();
    }

}
