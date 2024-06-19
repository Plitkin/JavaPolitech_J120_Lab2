package WordCalculator_1;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Пути к файлам
        List<String> filePaths = List.of("text/j120-lab2_пример файла для обработки.txt", "text/text.txt");

        WordFrequencyAnalyzer analyzer = new WordFrequencyAnalyzer();
        try {
            analyzer.readFiles(filePaths);
            ReportGenerator reportGenerator = new ReportGenerator(analyzer.getWordFrequencies(), analyzer.getTotalWords());
            reportGenerator.generateReports();
            System.out.println("Отчеты созданы");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файлов: " + e.getMessage());
        }
    }
}
