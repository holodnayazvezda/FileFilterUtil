package org.example.statistics;

import org.example.core.data.StatisticsOption;
import org.example.statistics.data.FloatStatistics;
import org.example.statistics.data.IntStatistics;
import org.example.statistics.data.ShortStatistics;
import org.example.statistics.data.StringStatistics;

import java.util.List;

public class Calculator {

    public static void calculateShortStatistics(
            List<String> intLines,
            List<String> floatLines,
            List<String> stringLines
    ) {
        ShortStatistics shortStatisticsData = new ShortStatistics(
                intLines.size(),
                floatLines.size(),
                stringLines.size()
        );

        Printer.printShortStatistics(shortStatisticsData);
    }

    public static void calculateFullStatisticsForIntegers(List<Long> intValues) {
        IntStatistics intStatisticsData = new IntStatistics(
                intValues.stream().min(Long::compareTo).orElse(0L),
                intValues.stream().max(Long::compareTo).orElse(0L),
                intValues.stream().mapToLong(Long::longValue).average().orElse(0.0),
                intValues.stream().mapToLong(Long::longValue).sum()
        );

        Printer.printFullStatisticsForIntegers(intStatisticsData);
    }

    public static void calculateFullStatisticsForFloats(List<Double> floatValues) {
        FloatStatistics floatStatisticsData = new FloatStatistics(
                floatValues.stream().min(Double::compareTo).orElse(0.0),
                floatValues.stream().max(Double::compareTo).orElse(0.0),
                floatValues.stream().mapToDouble(Double::doubleValue).average().orElse(0.0),
                floatValues.stream().mapToDouble(Double::doubleValue).sum()
        );

        Printer.printFullStatisticsForFloats(floatStatisticsData);
    }

    public static void calculateFullStatisticsForStrings(List<String> stringValues) {
        StringStatistics stringStatisticsData = new StringStatistics(
                stringValues.size(),
                stringValues.stream().mapToInt(String::length).max().orElse(0),
                stringValues.stream().mapToInt(String::length).min().orElse(0)
        );

        Printer.printFullStatisticsForStrings(stringStatisticsData);
    }

    public static void calculateStatistics(
            StatisticsOption statisticsOption,

            List<String> intLines,
            List<String> floatLines,
            List<String> stringLines,

            List<Long> intValues,
            List<Double> floatValues
    ) {
        if (statisticsOption == StatisticsOption.SHORT) {
            calculateShortStatistics(intLines, floatLines, stringLines);
        } else if (statisticsOption == StatisticsOption.FULL) {
            if (!intLines.isEmpty()) {
                calculateFullStatisticsForIntegers(intValues);
            }

            if (!floatLines.isEmpty()) {
                calculateFullStatisticsForFloats(floatValues);
            }

            if (!stringLines.isEmpty()) {
                calculateFullStatisticsForStrings(stringLines);
            }
        }
    }
}
