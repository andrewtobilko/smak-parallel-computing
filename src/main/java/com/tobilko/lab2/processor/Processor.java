package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

import static com.tobilko.lab2.util.ColourUtil.Color.BLUE;
import static com.tobilko.lab2.util.ColourUtil.Color.CYAN;
import static com.tobilko.lab2.util.ColourUtil.println;
import static com.tobilko.lab2.util.Util.MAX_SECONDS;
import static com.tobilko.lab2.util.Util.generateRandomId;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 9/25/17.
 */
@Getter
@RequiredArgsConstructor
public final class Processor implements Comparable<Processor> {

    private final long id = generateRandomId();

    private final long processingTime;
    private final ProcessorQueue queue;
    private final Queue<Processor> adjacentProcessors = new LinkedList<>();

    @SneakyThrows
    public void process() {
        while (true) {

            synchronized (queue) {
                println(format("%s is waiting for its queue", this), CYAN);
                queue.wait(MAX_SECONDS * 1000);

                if (!queue.getProcesses().isEmpty()) {
                    Process processToExecute = queue.getProcesses().poll();

                    executeProcess(processToExecute);
                } else {

                    // todo: execute a process from adjacent queues
                    break;
                }
            }
        }
    }

    @SneakyThrows
    private void executeProcess(Process processToExecute) {
        println(format("%s is executing %s from %s", this, processToExecute, queue), BLUE);
        Thread.sleep(processingTime * 1000);
    }

    @Override
    public int compareTo(Processor another) {
        final int queueSizeComparisonResult = Integer.compare(
                getQueue().getProcesses().size(),
                another.getQueue().getProcesses().size()
        );

        return queueSizeComparisonResult == 0 ?
                Long.compare(getProcessingTime(), another.getProcessingTime()) :
                queueSizeComparisonResult;
    }

    @Override
    public String toString() {
        return format("Processor #%d [%ds]", id, processingTime);
    }

}
