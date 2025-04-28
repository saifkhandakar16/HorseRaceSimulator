package game;

public class HorseResult {
    private String horseName;
    private double finishTime;
    private int position;
    private double confidenceChange;

    public HorseResult(String horseName, double finishTime, int position, double confidenceChange) {
        this.horseName = horseName;
        this.finishTime = finishTime;
        this.position = position;
        this.confidenceChange = confidenceChange;
    }

    public String getHorseName() {
        return horseName;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public int getPosition() {
        return position;
    }

    public double getConfidenceChange() {
        return confidenceChange;
    }
}
