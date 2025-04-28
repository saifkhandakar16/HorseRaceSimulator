public class Horse {
    private char horseSymbol;
    private String horseName;
    private double horseConfidence;
    private int distanceTravelled;
    private boolean hasFallen;

    public Horse(char horseSymbol, String horseName, double horseConfidence) {
        this.horseSymbol = horseSymbol;
        this.horseName = horseName;
        this.horseConfidence = horseConfidence;
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
        this.horseConfidence = Math.max(0.0, this.horseConfidence - 0.1); // decreases confidence by 0.1 after a fall
    }

    public void goBackToStart() {
        distanceTravelled = 0;
        hasFallen = false;
    }

    public void increaseConfidence(double amount) {
        this.horseConfidence = Math.min(1.0, this.horseConfidence + amount); // caps confidence at 1
    }

    public boolean hasFallen() {
        return this.hasFallen;
    }

    public double getConfidence() {
        return this.horseConfidence;
    }

    public int getDistanceTravelled() {
        return this.distanceTravelled;
    }

    public char getSymbol() {
        return this.horseSymbol;
    }

    public String getName() {
        return this.horseName;
    }

    public void setConfidence(double newConfidence) {
        this.horseConfidence = Math.max(0.0, Math.min(1.0, newConfidence)); // keeps confidence between 0.0 and 1.0
    }

    public void setSymbol(char newSymbol) {
        this.horseSymbol = newSymbol;
    }
}
