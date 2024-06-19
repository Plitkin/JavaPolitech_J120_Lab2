package WordCalculator_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordFrequencyAnalyzer {
    private final Map<String, Integer> wordFrequencies = new HashMap<>();
    private int totalWords = 0;

    // Чтение файлов и сбор текста
    public void readFiles(List<String> filePaths) throws IOException {
        StringBuilder allText = new StringBuilder();
        for (String filePath : filePaths) {
            allText.append(new String(Files.readAllBytes(Paths.get(filePath)))).append(" ");
        }
        computeWordFrequencies(allText.toString());
    }

    // Вычисление частоты слов
    private void computeWordFrequencies(String text) {
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ\\-]+");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            String word = matcher.group();
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
            totalWords++;
        }
    }

    // Возвращает словарь найденных слов
    public Map<String, Integer> getWordFrequencies() {
        return Collections.unmodifiableMap(wordFrequencies);
    }

    // Возвращает общее количество слов
    public int getTotalWords() {
        return totalWords;
    }
}
