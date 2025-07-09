package org.example.utils;

public class Logger {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String RESET_COLOR = "\u001B[0m";

    public static void printOk(String message) {
        System.out.println(ANSI_GREEN + message + RESET_COLOR);
    }

    public static void printError(String message) {
        System.out.println(ANSI_RED + message + RESET_COLOR);
    }

    public static void printYELLOW(String message) {
        System.out.println(ANSI_YELLOW + message + RESET_COLOR);
    }

    public static void printRED(String message) {
        printError(message);
    }

    public static void printBLUE(String message) {
        System.out.println(ANSI_BLUE + message + RESET_COLOR);
    }

    public static void printPURPLE(String message) {
        System.out.println(ANSI_PURPLE + message + RESET_COLOR);
    }
}
