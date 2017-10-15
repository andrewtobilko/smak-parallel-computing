package com.tobilko.lab2.processor.command;

import com.tobilko.lab2.command.Command;
import com.tobilko.lab2.process.BasicProcess;
import com.tobilko.lab2.processor.command.type.ProcessorCommandType;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
public final class ProcessorCommand implements Command<BasicProcess, ProcessorCommandType> {

    @Override
    public ProcessorCommandType getType() {
        return null;
    }

    @Override
    public BasicProcess getPayload() {
        return null;
    }

}
