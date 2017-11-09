package com.tobilko.lab3;

import com.tobilko.lab3.calculator.*;
import com.tobilko.lab3.util.Lab3Util;
import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class Lab3 {

    @SneakyThrows
    public static void main(String[] args) {

        final int DEFAULT_POOL_SIZE = 5;
        final Predicate<Integer> CONDITION = d -> d > 0;

        final ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_POOL_SIZE);

        Stream.of(

                new SumCalculator(DEFAULT_POOL_SIZE),                       // sum
                new QuantityCalculator(DEFAULT_POOL_SIZE, CONDITION),       // quantity of elements by condition
                new MinCalculator(DEFAULT_POOL_SIZE),                       // min with index
                new MaxCalculator(DEFAULT_POOL_SIZE),                       // max with index
                new ChecksumCalculator(DEFAULT_POOL_SIZE)                   // checksum using xor

        )
                .map(executorService::submit)
                .map(Lab3Util::sneakyThrows)
                .forEach(System.out::println);


        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
    }

}
