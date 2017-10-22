package com.tobilko.lab2.processor.runnable;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.queue.Deque;
import com.tobilko.lab2.util.DequeAware;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.GREEN;
import static com.tobilko.lab2.util.OutputUtil.println;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@RequiredArgsConstructor
public final class ProcessorRunnable implements Runnable, DequeAware {

    private final Processor processor;

    @Getter
    @Setter
    private Deque deque;

    @Override
    public void run() {
        println(GREEN, "%s with %s started running...", Thread.currentThread().getName(), processor);

        while (true) { // TODO: 10/20/17
            Process process = deque.getProcesses().pollFirst();

            try {
                processor.process(process);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.currentThread().interrupt();
        }

    }

}
