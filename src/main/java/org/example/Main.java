package org.example;

import org.apache.commons.cli.*;
import org.example.core.AppConfiguration;
import org.example.utils.FileWorker;
import org.example.utils.Logger;
import org.example.core.LinesProcessor;


public class Main {
    private static final AppConfiguration appConfiguration = new AppConfiguration();

    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("o", true, "Абсолютный путь к папке выходных файлы");
        options.addOption("p", true, "Префикс в названии выходных файлов");
        options.addOption("a", false, "Добавлять информацию в выходные файлы вместо перезаписи");

        // параметры статистики в отдельную группу (чтобы нельзя было задать два параметра одновременно)
        OptionGroup statisticsOptionGroup = new OptionGroup();
        statisticsOptionGroup
                .addOption(new Option("s", false, "Флаг, если передан, необходимо собрать краткую статистику"))
                .addOption(new Option("f", false, "Флаг, если передан, необходимо собрать полную статистику"));
        options.addOptionGroup(statisticsOptionGroup);


        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            // получаем переданные аргументы (в нашем случае это пути к файлам для обработки)
            String[] inputFiles = cmd.getArgs();
            if (inputFiles.length > 0) {
                appConfiguration.setInputFiles(inputFiles);
            } else {
                throw new ParseException("Не был передан ни один входной файл для обработки!");
            }

            // проверяем, был ли передан путь папки, в которую сохранять результаты выходных файлов
            if (cmd.hasOption("o")) {
                String parameterValue = cmd.getOptionValue("o");

                if (!parameterValue.endsWith("/")) {
                    parameterValue += "/";
                }

                // ! если передан не существующий каталог результаты будут сохранены в текущую директорию
                if (FileWorker.checkDirectory(parameterValue)) {
                    appConfiguration.setAbsolutePathToFilesDirectory(parameterValue);
                } else {
                    Logger.printError("Ошибка! Передан не существующий каталог. Выходные файлы будут сохранены в текущую директорию!");
                    appConfiguration.setAbsolutePathToFilesDirectory(System.getProperty("user.dir").concat("/"));
                }
            } else {
                appConfiguration.setAbsolutePathToFilesDirectory(System.getProperty("user.dir").concat("/"));
            }

            if (cmd.hasOption("p")) {
                String parameterValue = cmd.getOptionValue("p");
                appConfiguration.setPrefixForFilesNames(parameterValue);
            } else {
                appConfiguration.setPrefixForFilesNames("");
            }

            appConfiguration.setAppendMode(cmd.hasOption("a"));

            if (cmd.hasOption("s")) {
                appConfiguration.setStatisticsOption(0);
            } else if (cmd.hasOption("f")) {
                appConfiguration.setStatisticsOption(1);
            } else {
                appConfiguration.setStatisticsOption(-1);
                Logger.printYELLOW("Не был передан флаг сбора статистики, статистика не будет собрана!");
            }

        } catch (ParseException e) {  // перехватываем ошибку в случае, если параметры записка были указаны неправильно (например было передано сразу два параметра о статистике (и -s и -f))
            Logger.printError("Некорректные параметры запуска: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar util.jar [options]", options, true);
            return;
        }

        // работаем с переданными данными
        LinesProcessor.classifyLines(appConfiguration);
    }
}