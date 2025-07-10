package org.example.core.data;

public enum StatisticsOption {
    NONE(-1),
    SHORT(0),
    FULL(1);

    private final int value;

    StatisticsOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
