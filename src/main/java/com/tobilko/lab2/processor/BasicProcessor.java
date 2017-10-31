package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
import lombok.Getter;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.GREEN;
import static com.tobilko.lab2.util.OutputUtil.println;
import static java.lang.String.format;

/**
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
public final class BasicProcessor implements Processor {

    private final Integer id;
    private final int processingTime;

    public BasicProcessor(Integer id, int processingTime) {
        this.id = id;

        validateProcessingTime(this.processingTime = processingTime);
    }

    private void validateProcessingTime(int processingTime) {
        if (processingTime <= 0) {
            throw new IllegalArgumentException("A processor can't be created with processing time <= 0.");
        }
    }

    @Override
    public void process(Process process) throws InterruptedException {
        validateProcess(process);
        logProcess(process);

        Thread.sleep(processingTime * 1000);
    }

    private void logProcess(Process process) {
        println(GREEN, "%s started to execute %s", this, process);
    }

    private void validateProcess(Process process) {
        if (process == null) {
            throw new IllegalArgumentException("A processor does not execute NULLs...");
        }
    }

    @Override
    public String toString() {
        return format("Processor #%d [%ds]", getId(), getProcessingTime());
    }

}
