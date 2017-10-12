package com.tobilko.lab2;

import com.tobilko.lab2.processor.Processor;
import com.tobilko.lab2.queue.ProcessorQueue;
import com.tobilko.lab2.thread.ProcessorThread;

import static com.tobilko.lab2.util.Util.generateRandomProcessorWorkTime;

/**
 * В программах
 * класc CPUQueue описывает очередь,
 * класс CPUProcess моделирует поток процессов,
 * а класс CPU – поток обслуживания процесса центральным процессором.
 *
 *
 *
 * Очереди, потоки процессов и обслуживание процесса моделируются с помощью объектов
 * соответствующего класса. Параметры очереди моделируются с помощью алгоритмов вставки
 * и извлечения процесса из очереди. Параметром  процесса является интервал времени
 * между двумя последовательными генерациями процессов. Параметром процессора является
 * время обслуживания процесса.
 * Случайные времена для интервалов между моментами генерации процессов и для времен
 * обслуживания распределены по равномерному закону   с заданными верхней и нижней
 * границами (см. метод random() в классе Math). Исходными данными для моделирования
 * являются количество процессов, которые должны быть сгенерированы (для каждого потока
 * процессов), а также нижние и верхние границы для потоков.
 * <p>
 * Программа моделирует обслуживание двух потоков процессов с разными
 * параметрами двумя центральными процессорами и двумя очередями. Каждый процесс
 * поступает в свою очередь и обслуживается своим процессором. Если первый процессор
 * свободен и очередь первого потока процессов пуста, процессор выбирает процесс из
 * второй очереди, однако, если во время обработки процесса, генерируется процесс первого
 * потока, обработка процесса прерывается и он возвращается в свою очередь как первый в
 * очереди. Определите максимальные длины очередей и количество прерванных процессов
 * второго потока.
 * <p>
 * Created by Andrew Tobilko on 9/25/17.
 */


public final class Lab2 {

    public static void main(String[] args) {

        final int PROCESSOR_NUMBER = 2;
        final int[] PROCESS_NUMBERS = {12, 10};

        final ProcessorThread[] threads = new ProcessorThread[PROCESSOR_NUMBER];

        for (int i = 0; i < PROCESSOR_NUMBER; ++i) {
            Processor processor = new Processor(
                    generateRandomProcessorWorkTime(),
                    new ProcessorQueue(PROCESS_NUMBERS[i])
            );
            threads[i] = new ProcessorThread(processor);
        }

        for (ProcessorThread thread : threads) {
            thread.start();
        }

    }

}
