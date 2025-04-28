package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameSettings {
    // track setup
    public static int laneCount;
    public static int trackLength;
    public static String trackShape;
    public static int emptyLanes;

    // weather and track condition
    public static String weather;
    public static String trackCondition;

    // horse customization
    public static String horseBreed;
    public static String horseCoatColor;
    public static String horseSymbol;
    public static String horseSaddle;
    public static String horseHorseshoes;

    // list of horse names (updated with your 10 horses)
    public static List<String> horseNames = Arrays.asList(
            "Ayo", "Bilal", "Charlie", "Dave", "Eric",
            "Friday", "Gregory", "Harry", "Ilyas", "Jeremy");

    // list of RaceResults
    public static List<RaceResult> raceHistory = new ArrayList<>();

    public static void reset() {
        laneCount = 0;
        trackLength = 0;
        trackShape = "";
        weather = "";
        trackCondition = "";
        horseBreed = "";
        horseCoatColor = "";
        horseSymbol = "";
        horseSaddle = "";
        horseHorseshoes = "";
    }

    // method to add a race result to the history
    public static void addRaceResult(RaceResult result) {
        raceHistory.add(result);
    }

    // method to get race history
    public static List<RaceResult> getRaceHistory() {
        return raceHistory;
    }
}
