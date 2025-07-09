package org.example.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppConfiguration {  // объект для хранения параметров запуска утилиты
    public String absolutePathToFilesDirectory;
    public String prefixForFilesNames;
    public boolean appendMode;
    public int statisticsOption;  // -1 - не выдавать статистику, 0 - краткая, 1 - полная

    public String[] inputFiles;
}
