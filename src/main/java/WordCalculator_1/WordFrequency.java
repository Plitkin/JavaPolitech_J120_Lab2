package WordCalculator_1;

public class WordFrequency {
    private final String word;
    private final int frequency;
    private final double relativeFrequency;

    public WordFrequency(String word, int frequency, double relativeFrequency) {
        this.word = word;
        this.frequency = frequency;
        this.relativeFrequency = relativeFrequency;
    }

    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getRelativeFrequency() {
        return relativeFrequency;
    }
}

