package com.tobilko.lab2.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tobilko.lab2.util.OutputUtil.OutputColour.RESET;
import static java.lang.String.format;
import static java.lang.String.join;

/**
 * Created by Andrew Tobilko on 10/20/17.
 */
public final class OutputUtil {

    @RequiredArgsConstructor
    public enum OutputColour {

        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        @Getter
        private final String ANSICode;

    }

    public static void println(OutputColour colour, String format, Object... formatArguments) {
        System.out.println(join("", colour.getANSICode(), format(format, formatArguments), RESET.getANSICode()));
    }

    private OutputUtil() {}

}
