package ScriptLanguage_2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        // Путь к файлу скрипта
        String scriptFile = "script/script.txt";

        ScriptExecutor executor = new ScriptExecutor();

        try {
            // Чтение файла скрипта
            Files.lines(Paths.get(scriptFile)).forEach(executor::executeLine);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}


