package org.example;

import org.apache.commons.cli.*;
import org.example.core.data.AppConfiguration;
import org.example.core.data.StatisticsOption;
import org.example.utils.ReadWriteUtils;
import org.example.utils.Logger;
import org.example.core.LinesProcessor;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("o", true, "Абсолютный путь к папке выходных файлы");
        options.addOption("p", true, "Префикс в названии выходных файлов");
        options.addOption("a", false, "Добавлять информацию в выходные файлы вместо перезаписи");

        OptionGroup statisticsOptionGroup = new OptionGroup();
        statisticsOptionGroup
                .addOption(new Option("s", false, "Флаг, если передан, необходимо собрать краткую статистику"))
                .addOption(new Option("f", false, "Флаг, если передан, необходимо собрать полную статистику"));
        options.addOptionGroup(statisticsOptionGroup);

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            String[] inputFiles = cmd.getArgs();
            if (inputFiles.length == 0) {
                throw new ParseException("Не был передан ни один входной файл для обработки!");
            }

            String outputDir = cmd.getOptionValue("o", System.getProperty("user.dir") + "/");
            if (!outputDir.endsWith("/")) {
                outputDir += "/";
            }
            if (!ReadWriteUtils.checkDirectory(outputDir)) {
                Logger.printError("Ошибка! Передан не существующий каталог. Выходные файлы будут сохранены в текущую директорию!");
                outputDir = System.getProperty("user.dir") + "/";
            }

            String prefix = cmd.getOptionValue("p", "");
            boolean append = cmd.hasOption("a");

            StatisticsOption statsOpt = StatisticsOption.NONE;
            if (cmd.hasOption("s")) {
                statsOpt = StatisticsOption.SHORT;
            } else if (cmd.hasOption("f")) {
                statsOpt = StatisticsOption.FULL;
            } else {
                Logger.printYELLOW("Не был передан флаг сбора статистики, статистика не будет собрана!");
            }

            AppConfiguration appConfiguration = new AppConfiguration(
                    outputDir,
                    prefix,
                    append,
                    statsOpt,
                    inputFiles
            );

            LinesProcessor.classifyLines(appConfiguration);

        } catch (ParseException e) {
            Logger.printError("Некорректные параметры запуска: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar util.jar [options]", options, true);
        }
    }
}
