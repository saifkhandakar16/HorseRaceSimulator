package game;

public class Horse {
    private char symbol;
    private String name;
    private double confidence;

    public Horse(char symbol, String name, double confidence) {
        this.symbol = symbol;
        this.name = name;
        this.confidence = confidence;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
