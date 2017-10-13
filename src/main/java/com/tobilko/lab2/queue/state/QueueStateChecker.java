package com.tobilko.lab2.queue.state;

import com.tobilko.lab2.queue.ProcessorQueue;
import lombok.SneakyThrows;

import static com.tobilko.lab2.util.ColourUtil.Color.BLACK;
import static com.tobilko.lab2.util.ColourUtil.Color.GREEN;
import static com.tobilko.lab2.util.ColourUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/13/17.
 */
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
                println(format("%s of %s is empty!\n", queue, queue.getProcessor()), GREEN);

                synchronized (queue) {
                    println(format("%s waiting for %s...\n", queue.getProcessor(), queue), GREEN);
                    queue.wait();
                }
            } else {
                println(format("%s of %s has %d processors in line.\n", queue, queue.getProcessor(), size), BLACK);
            }

            Thread.sleep(5000L);
        }
    }

}
