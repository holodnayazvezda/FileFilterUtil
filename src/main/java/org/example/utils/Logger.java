package org.example.utils;

public class Logger {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static void printOk(String message) {
        System.out.println(ANSI_GREEN + message + ANSI_GREEN);
    }

    public static void printError(String message) {
        System.out.println(ANSI_RED + message + ANSI_RED);
    }

    public static void printYELLOW(String message) {
        System.out.println(ANSI_YELLOW + message + ANSI_YELLOW);
    }

    public static void printRED(String message) {
        printError(message);
    }

    public static void printBLUE(String message) {
        System.out.println(ANSI_BLUE + message + ANSI_BLUE);
    }

    public static void printPURPLE(String message) {
        System.out.println(ANSI_PURPLE + message + ANSI_PURPLE);
    }
}
