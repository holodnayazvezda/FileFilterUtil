package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileWorker {
    private static List<String> getLinesFromFile(String fileName) {
        List<String> listOfLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    listOfLines.add(line);
                }
            }
        } catch (IOException e) {
            Logger.printError("Ошибка! Файл " + fileName + " не найден или не удалось прочитать :(");
        }
        return listOfLines;
    }


    public static List<String> getAllLinesFromFiles(String[] inputFiles) {
        // Формирует список строк из нескольких файлов, разбирая их по строкам.
        List<List<String>> allFilesLines = new ArrayList<>();
        int maxLines = 0;
        for (String fileName : inputFiles) {
            List<String> lines = getLinesFromFile(fileName);
            allFilesLines.add(lines);
            if (lines.size() > maxLines) {
                maxLines = lines.size();
            }
        }

        List<String> allLines = new ArrayList<>();

        for (int i = 0; i < maxLines; i++) {
            for (List<String> lines : allFilesLines) {
                if (i < lines.size()) {
                    allLines.add(lines.get(i));
                }
            }
        }

        return allLines;
    }

    public static void writeLinesToFile(
            String absolutePathToOutputFile,
            Boolean appendMode,
            List<String> linesToWrite
    ) {
        try (java.io.FileWriter writer = new java.io.FileWriter(absolutePathToOutputFile, appendMode)) {
            for (String line : linesToWrite) {
                writer.write(line + System.lineSeparator());
            }
        } catch (java.io.IOException e) {
            Logger.printError("Ошибка при записи в файл " + absolutePathToOutputFile + ": " + e.getMessage());
        }
    }

    public static boolean checkDirectory(String directory) {
        File directoryFile = new File(directory);
        return directoryFile.exists() && directoryFile.isDirectory();
    }
}
