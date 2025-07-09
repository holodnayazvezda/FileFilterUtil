package org.example.statistics;

import org.example.statistics.data.FloatStatistics;
import org.example.statistics.data.IntStatistics;
import org.example.statistics.data.ShortStatistics;
import org.example.statistics.data.StringStatistics;
import org.example.utils.Logger;

public class StatisticsPrinter {
    public static void printShortStatistics(ShortStatistics shortStatisticsData) {
        Logger.printOk("Краткая статистика по работе утилиты:\n");
        Logger.printBLUE("Количество строк типа Integer: " + shortStatisticsData.numberOfInts());
        Logger.printPURPLE("Количество строк типа Float: " + shortStatisticsData.numberOfFloats());
        Logger.printYELLOW("Количество строк типа String: " + shortStatisticsData.numberOfStrings());
    }

    public static void printFullStatisticsForIntegers(IntStatistics intStatisticsData) {
        Logger.printOk("Полная статистика по Integer:");
        Logger.printBLUE("Минимальное значение: " + intStatisticsData.min());
        Logger.printPURPLE("Максимальное значение: " + intStatisticsData.max());
        Logger.printYELLOW("Среднее значение: " + intStatisticsData.average());
        Logger.printRED("Сумма всех значений: " + intStatisticsData.sum() + "\n");
    }

    public static void printFullStatisticsForFloats(FloatStatistics floatStatisticsData) {
        Logger.printOk("Полная статистика по Float:");
        Logger.printBLUE("Минимальное значение: " + floatStatisticsData.min());
        Logger.printPURPLE("Максимальное значение: " + floatStatisticsData.max());
        Logger.printYELLOW("Среднее значение: " + floatStatisticsData.average());
        Logger.printRED("Сумма всех значений: " + floatStatisticsData.sum() + "\n");
    }

    public static void printFullStatisticsForStrings(StringStatistics stringStatisticsData) {
        Logger.printOk("Полная статистика по String:");
        Logger.printBLUE("Количество строк: " + stringStatisticsData.amountOfLines());
        Logger.printPURPLE("Максимальная длина строки: " + stringStatisticsData.maxLineLength());
        Logger.printYELLOW("Минимальная длина строки: " + stringStatisticsData.minLineLength() + "\n");
    }
}
