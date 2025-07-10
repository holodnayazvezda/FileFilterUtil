package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ReadWriteUtils {
    public static List<String> getLinesFromFiles(String[] inputFilePaths) {
        // Формирует список строк из нескольких файлов, разбирая их по строкам.
        List<List<String>> allFilesLines = new ArrayList<>();
        int maxLines = 0;
        for (String filePath : inputFilePaths) {
            List<String> lines = getLinesFromFile(filePath);
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
            String pathToOutputFile,
            boolean appendMode,
            List<String> linesToWrite
    ) {
        try (FileWriter writer = new java.io.FileWriter(pathToOutputFile, appendMode)) {
            for (String line : linesToWrite) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            Logger.printError(String.format("Ошибка при записи в файл %s: %s", pathToOutputFile, e.getMessage()));

        }
    }

    public static boolean checkDirectory(String pathToDirectory) {
        File directoryFile = new File(pathToDirectory);
        return directoryFile.exists() && directoryFile.isDirectory();
    }

    private static List<String> getLinesFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            Logger.printError(String.format("Ошибка! Файл %s не найден или не удалось прочитать :(", fileName));
        }
        return lines;
    }
}
