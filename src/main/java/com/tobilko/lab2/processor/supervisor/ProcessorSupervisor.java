package com.tobilko.lab2.processor.supervisor;

import com.tobilko.lab2.Stealer;
import com.tobilko.lab2.StolenItem;
import com.tobilko.lab2.StolenProcess;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
@Setter
@RequiredArgsConstructor
public final class ProcessorSupervisor implements Runnable, Stealer<StolenItem<Process, Deque<Process>>> {

    private final Processor processor;

    private Deque<Process> deque;
    private List<Deque<Process>> deques;

    @Override
    public void run() {

        while (true) {
            if (!deque.isEmpty()) {
                // todo: execute own task

                handleExceptionTODO(deque.pollFirst(), Throwable::printStackTrace); // todo : can't happen
            } else {
                // todo : try to steal
                StolenItem<Process, Deque<Process>> stolenProcess = steal();
                Process item = stolenProcess.getItem();
                Deque<Process> stolenFrom = stolenProcess.getStolenFrom();

                handleExceptionTODO(item, e -> {
                    stolenFrom.addFirst(item);
                });
            }
        }

    }

    private void handleExceptionTODO(Process process, Consumer<InterruptedException> consumer) {
        try {
            processor.process(process);
        } catch (InterruptedException e) {
            consumer.accept(e);
        }

    }


    @Override
    public StolenItem<Process, Deque<Process>> steal() {
        for (Deque<Process> deque : deques) {
            if (!deque.isEmpty()) {
                return StolenProcess.of(deque, deque.pollFirst());
            }
        }
        throw new NoSuchElementException("All the deques are empty...");
    }

}
