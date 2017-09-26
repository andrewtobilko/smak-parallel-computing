package com.tobilko.lab2;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Andrew Tobilko on 9/26/17.
 */
@RequiredArgsConstructor
public final class ProcessorAwareThread extends Thread implements ProcessorAware {

    @Setter
    @Getter
    @NonNull
    private Processor processor;



}
