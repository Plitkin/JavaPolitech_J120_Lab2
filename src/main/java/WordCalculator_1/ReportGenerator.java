package WordCalculator_1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ReportGenerator {
    private final Map<String, Integer> wordFrequencies;
    private final int totalWords;

    public ReportGenerator(Map<String, Integer> wordFrequencies, int totalWords) {
        this.wordFrequencies = wordFrequencies;
        this.totalWords = totalWords;
    }

    // Генерация всех отчетов
    public void generateReports() throws IOException {
        List<WordFrequency> wordFrequencyList = wordFrequencies.entrySet().stream()
                .map(entry -> new WordFrequency(entry.getKey(), entry.getValue(), (double) entry.getValue() / totalWords))
                .collect(Collectors.toList());

        writeReportByAlphabet(wordFrequencyList);
        writeReportByAlphabetReversed(wordFrequencyList);
        writeReportByFrequency(wordFrequencyList);
    }

    // Генерация отчета по алфавиту
    private void writeReportByAlphabet(List<WordFrequency> wordFrequencyList) throws IOException {
        List<WordFrequency> sortedList = new ArrayList<>(wordFrequencyList);
        sortedList.sort(Comparator.comparing(WordFrequency::getWord));
        writeReport("text/report-by-alph.txt", sortedList);
    }

    // Генерация отчета по алфавиту в обратном порядке
    private void writeReportByAlphabetReversed(List<WordFrequency> wordFrequencyList) throws IOException {
        List<WordFrequency> sortedList = new ArrayList<>(wordFrequencyList);
        sortedList.sort(Comparator.comparing((WordFrequency wf) -> new StringBuilder(wf.getWord()).reverse().toString()));
        writeReport("text/report-by-alph-rev.txt", sortedList);
    }

    // Генерация отчета по частоте
    private void writeReportByFrequency(List<WordFrequency> wordFrequencyList) throws IOException {
        List<WordFrequency> sortedList = new ArrayList<>(wordFrequencyList);
        sortedList.sort(Comparator.comparing(WordFrequency::getFrequency).reversed()
                .thenComparing(WordFrequency::getWord));
        writeReport("text/report-by-freq.txt", sortedList);
    }

    // Запись отчета в файл
    private void writeReport(String fileName, List<WordFrequency> wordFrequencyList) throws IOException {
        List<String> lines = wordFrequencyList.stream()
                .map(wf -> wf.getWord() + " | " + wf.getFrequency() + " | " + wf.getRelativeFrequency())
                .collect(Collectors.toList());
        Files.write(Paths.get(fileName), lines);
    }
}
