package com.tobilko.lab2.queue;

import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.util.Identifiable;

import java.util.concurrent.BlockingDeque;

/**
 * Created by Andrew Tobilko on 10/15/17.
 */
public interface ProcessQueue extends Identifiable {

    BlockingDeque<Process> getProcesses();

}
