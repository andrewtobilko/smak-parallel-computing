package com.tobilko.lab2.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.lang.String.join;

/**
 * Created by Andrew Tobilko on 10/12/17.
 */
public final class ColourUtil {

    @RequiredArgsConstructor
    public enum Color {
        BLACK("\u001B[30m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m");

        @Getter
        private final String ANSICode;

    }

    public static void println(String value, Color color) {
        final String code = color.getANSICode();
        System.out.println(join("", code, value, code, Color.BLACK.getANSICode(), "\n"));
    }

}
