package game;

public class GameSettings {
    // Track setup
    public static int laneCount;
    public static int trackLength;
    public static String trackShape;

    // Weather and track condition
    public static String weather;
    public static String trackCondition;

    // Horse customization
    public static String horseBreed;
    public static String horseCoatColor;
    public static String horseSymbol;
    public static String horseSaddle;
    public static String horseHorseshoes;

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
}
