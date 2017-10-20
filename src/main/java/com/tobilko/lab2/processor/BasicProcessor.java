package com.tobilko.lab2.processor;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.OutputUtil;
import lombok.Getter;

import static com.tobilko.lab2.util.OutputUtil.*;
import static com.tobilko.lab2.util.OutputUtil.OutputColour.*;
import static java.lang.String.format;

/**
 * Takes a process and emulates its execution by Thread.sleep.
 * <p>
 * Created by Andrew Tobilko on 10/16/17.
 */
@Getter
public final class BasicProcessor implements Processor {

    private final Integer id;
    private final int processingTime;

    public BasicProcessor(Integer id, int processingTime) {
        // set an id
        this.id = id;

        // set a processing time
        if (processingTime <= 0) {
            throw new IllegalArgumentException("A processor can't be created with processing time <= 0.");
        }
        this.processingTime = processingTime;
    }

    @Override
    public boolean process(Process process) {
        validateProcess(process);

        println(GREEN, "%s started to execute %s", this, process);

        try {
            Thread.sleep(processingTime);
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

    private void validateProcess(Process process) {
        if (process == null) {
            throw new IllegalArgumentException("");
        }
    }

    @Override
    public String toString() {
        return format("Processor #%d [%ds]", getId(), getProcessingTime());
    }

}
