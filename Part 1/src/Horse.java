public class Horse {
    private char symbol;
    private String name;
    private double confidence;
    private int distanceTravelled;
    private boolean hasFallen;

    public Horse(char symbol, String name, double confidence) {
        this.symbol = symbol;
        this.name = name;
        this.confidence = confidence;
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }

    public void moveForward() {
        if (!hasFallen) {
            distanceTravelled++; // distance increases by 1
        }
    }

    public void fall() {
        this.hasFallen = true;
        this.confidence = Math.max(0.0, this.confidence - 0.1); // decreases confidence by 0.1 after a fall
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    public void increaseConfidence(double amount) {
        this.confidence = Math.min(1.0, this.confidence + amount); // caps confidence at 1
    }

    public boolean hasFallen() {
        return this.hasFallen;
    }

    public double getConfidence() {
        return this.confidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }
}
