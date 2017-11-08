package com.tobilko.lab3;

import com.tobilko.lab3.calculator.ChecksumCalculator;
import com.tobilko.lab3.calculator.MinMaxCalculator;
import com.tobilko.lab3.calculator.QuantityDoubleCalculator;
import com.tobilko.lab3.calculator.SumDoubleCalculator;
import com.tobilko.lab3.util.Lab3Util;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Lab3 {

    @SneakyThrows
    public static void main(String[] args) {

        final Predicate<Double> CONDITION = d -> d > 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(5);

        Stream.of(

                new SumDoubleCalculator(),                          // sum
                new QuantityDoubleCalculator(CONDITION),            // quantity of elements by condition
                new MinMaxCalculator(),                             // min and max with indices
                new ChecksumCalculator()                            // checksum using xor

        )
                .map(executorService::submit)
                .map(Lab3Util::sneakyThrows)
                .forEach(System.out::println);


        executorService.shutdown();
    }

}
