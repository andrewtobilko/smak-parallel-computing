package com.tobilko.lab2;

import com.tobilko.lab2.generator.Generator;
import com.tobilko.lab2.generator.ProcessGenerator;
import com.tobilko.lab2.generator.manager.ProcessGeneratorManager;
import com.tobilko.lab2.information.Information;
import com.tobilko.lab2.process.Process;
import com.tobilko.lab2.processor.BasicProcessor;
import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.processor.manager.BasicProcessorManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.tobilko.lab2.util.RandomUtil.getRandomId;
import static com.tobilko.lab2.util.RandomUtil.getRandomTimeInSeconds;

/**
 * Created by Andrew Tobilko on 10/29/17.
 */
public final class Lab2Initialiser {

    @SuppressWarnings("unchecked")
    public static void initialise(InputParameters parameters) {
        validateInputParameters(parameters);

        final int NUMBER_OF_PROCESSES = parameters.getNumberOfProcessors();
        final int[] NUMBERS_OF_PROCESSES_TO_GENERATE = parameters.getNumbersOfProcessesToGenerate();

        Information.RuntimeInformation.setProcessesRemaining(IntStream.of(NUMBERS_OF_PROCESSES_TO_GENERATE).sum());
        System.out.println("s = " + Information.RuntimeInformation.getProcessesRemaining());

        final ProcessGeneratorManager[] generatorManagers = new ProcessGeneratorManager[NUMBER_OF_PROCESSES];
        final BasicProcessorManager[] processorManagers = new BasicProcessorManager[NUMBER_OF_PROCESSES];

        // initialise managers
        for (int i = 0; i < NUMBER_OF_PROCESSES; ++i) {
            final Processor processor = new BasicProcessor(getRandomId(), getRandomTimeInSeconds());
            final Generator<Process> generator = new ProcessGenerator(getRandomId());
            final Deque<Process> deque = new LinkedList<>();

            generatorManagers[i] = new ProcessGeneratorManager(generator, NUMBERS_OF_PROCESSES_TO_GENERATE[i], deque);
            processorManagers[i] = new BasicProcessorManager(processor, deque, generator);
        }

        // fill processors' deques to steal from
        for (final BasicProcessorManager processorManager : processorManagers) {
            final List<Deque<Process>> list = new LinkedList<>();

            for (final BasicProcessorManager manager : processorManagers) {
                if (manager != processorManager) {
                    list.add(manager.getDeque());
                }
            }

            processorManager.setDeques(list.toArray(new Deque[list.size()]));
        }

        // start managers
        for (int i = 0; i < NUMBER_OF_PROCESSES; ++i) {
            new Thread(processorManagers[i]).start();
            new Thread(generatorManagers[i]).start();
        }

    }

    private static void validateInputParameters(InputParameters parameters) {
        if (parameters.numberOfProcessors != parameters.numbersOfProcessesToGenerate.length) {
            throw new IllegalArgumentException("Check the input parameters again...");
        }
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class InputParameters {
        private final int numberOfProcessors;
        private final int[] numbersOfProcessesToGenerate;
    }

}
