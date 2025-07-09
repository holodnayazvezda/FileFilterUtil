package org.example.core;

import org.example.statistics.StatisticsCalculator;
import org.example.utils.FileWorker;

import java.util.ArrayList;
import java.util.List;

public class LinesProcessor {
    public static int classifyLine(String line) {
        // вернуть 0, если тип int; 1 - если тип float, 2 - если тип String; -1 если строка пустая или null
        if (line == null || line.isEmpty()) {
            return -1;
        }
        if (line.matches("^-?\\d+$")) {
            return 0;
        }
        if (line.matches("^-?\\d*\\.\\d*$") || line.matches("^-?\\d+(\\.\\d*)?[eE][+-]?\\d+$")) {
            return 1;
        }
        return 2;
    }

    public static void classifyLines(
            AppConfiguration appConfiguration
    ) {
        // получаем все строчки из файлов в требуемом порядке (сначала первая строка из первого, потом первая строка из второго, потом вторая строка из первого и так далее)
        List<String> lines = FileWorker.getAllLinesFromFiles(appConfiguration.getInputFiles());

        List<String> intLines = new ArrayList<>();
        List<String> floatLines = new ArrayList<>();
        List<String> stringLines = new ArrayList<>();

        // для подсчета статистики
        List<Long> intValues = new ArrayList<>();
        List<Double> floatValues = new ArrayList<>();

        for (String line : lines) {
            int type = classifyLine(line);

            if (type == 0) {
                intLines.add(line);

                try {
                    intValues.add(Long.parseLong(line));  // если число > 2^64 или < 2^(-64) то в статистике оно учитываться не будет
                } catch (NumberFormatException _) {}
            } else if (type == 1) {
                floatLines.add(line);

                try {
                    floatValues.add(Double.parseDouble(line));  // аналогично с вещественными числами
                } catch (NumberFormatException _) {}
            } else if (type == 2) {
                stringLines.add(line);
            }

        }

        if (!stringLines.isEmpty()) {
            String absolutePathToStringsFile = appConfiguration.absolutePathToFilesDirectory
                    .concat(appConfiguration.prefixForFilesNames.concat("strings.txt"));

            FileWorker.writeLinesToFile(
                    absolutePathToStringsFile,
                    appConfiguration.appendMode,
                    stringLines
            );
        }

        if (!intLines.isEmpty()) {
            String absolutePathToIntegersFile = appConfiguration.absolutePathToFilesDirectory
                    .concat(appConfiguration.prefixForFilesNames.concat("integers.txt"));

            FileWorker.writeLinesToFile(
                    absolutePathToIntegersFile,
                    appConfiguration.appendMode,
                    intLines
            );
        }

        if (!floatLines.isEmpty()) {
            String absolutePathToFloatsFile = appConfiguration.absolutePathToFilesDirectory
                    .concat(appConfiguration.prefixForFilesNames.concat("floats.txt"));

            FileWorker.writeLinesToFile(
                    absolutePathToFloatsFile,
                    appConfiguration.appendMode,
                    floatLines
            );
        }


        // вызываем функцию для вычисления и вывода статистики
        StatisticsCalculator.calculateStatistics(
                appConfiguration.statisticsOption,
                
                intLines, 
                floatLines, 
                stringLines,
                
                intValues,
                floatValues
        );
    }
}
