package game;

import java.util.List;

public class RaceResult {
    private String trackName;
    private String date;
    private List<HorseResult> horseResults;

    public RaceResult(String trackName, String date, List<HorseResult> horseResults) {
        this.trackName = trackName;
        this.date = date;
        this.horseResults = horseResults;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getDate() {
        return date;
    }

    public List<HorseResult> getHorseResults() {
        return horseResults;
    }
}
