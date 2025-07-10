package org.example.utils;

public class Logger {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String RESET_COLOR = "\u001B[0m";

    private static void print(String color, String message) {
        System.out.printf("%s %s %s%n", color, message, RESET_COLOR);
    }

    public static void printOk(String message) {
        print(ANSI_GREEN, message);
    }

    public static void printError(String message) {
        print(ANSI_RED, message);
    }

    public static void printYELLOW(String message) {
        print(ANSI_YELLOW, message);
    }

    public static void printRED(String message) {
        printError(message);
    }

    public static void printBLUE(String message) {
        print(ANSI_BLUE, message);
    }

    public static void printPURPLE(String message) {
        print(ANSI_PURPLE, message);
    }
}
