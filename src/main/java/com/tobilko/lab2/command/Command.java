package com.tobilko.lab2.command;

import com.tobilko.lab2.command.type.CommandType;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
public interface Command<P, T extends CommandType> {

    T getType();

    P getPayload();

}
