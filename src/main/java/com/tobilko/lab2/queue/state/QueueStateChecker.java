package com.tobilko.lab2.queue.state;

import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.ColourUtil.Color.RED;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/13/17.
 */
@Deprecated
public final class QueueStateChecker extends Thread {

    private final ProcessorQueue queue;

    public QueueStateChecker(ProcessorQueue queue) {
        this.queue = queue;
        setDaemon(true);
    }

    @Override
    public void run() {
        checkQueueState();
    }

    @SneakyThrows
    private void checkQueueState() {
        while (true) {
            int size = queue.getProcesses().size();

            if (size == 0) {
                println(format("%s of %s is empty!", queue, queue.getProcessor()), RED);
            }

            Thread.sleep(5000L);
        }
    }

}
