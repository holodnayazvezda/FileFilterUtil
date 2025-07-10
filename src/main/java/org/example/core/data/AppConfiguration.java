package org.example.core.data;

public record AppConfiguration(
        String absolutePathToFilesDirectory,
        String prefixForFilesNames,
        boolean appendMode,
        StatisticsOption statisticsOption,
        String[] inputFiles
) {}
