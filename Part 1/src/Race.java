import java.util.ArrayList;
import java.util.List;

public class Race {
    private int raceLength;
    private List<Horse> lanes;

    public Race(int distance, int numLanes) {
        raceLength = distance;
        lanes = new ArrayList<>(numLanes);

        // initialise the lanes with up to 10 horses
        for (int i = 0; i < numLanes; i++) {
            if (i < 10) {
                lanes.add(null); // lanes are initially null, horses are added in main
            } else {
                lanes.add(null); // extra lanes remain empty using null
            }
        }
    }

    public void addHorse(Horse theHorse, int laneNumber) {
        if (laneNumber > 0 && laneNumber <= lanes.size()) {
            lanes.set(laneNumber - 1, theHorse);
        } else {
            System.out.println("Invalid lane number.");
        }
    }

    public void startRace() {
        boolean finished = false;

        // Reset all horses before starting a new race
        for (Horse horse : lanes) {
            if (horse != null) {
                horse.goBackToStart();
            }
        }

        while (!finished) {
            boolean allHorsesFallen = true;

            // move the horses that haven't fallen
            for (Horse horse : lanes) {
                if (horse != null && !horse.hasFallen()) {
                    allHorsesFallen = false; // check to see if at least one horse is still moving
                    moveHorse(horse);
                }
            }

            // if all horses fall, end the race
            if (allHorsesFallen) {
                System.out.println("All horses have fallen. The race is over.");
                break;
            }

            printRace();

            // checks if any horses have finished
            List<Horse> winners = new ArrayList<>();
            for (Horse horse : lanes) {
                if (horse != null && raceWonBy(horse)) { // checks if the lane has a horse and uses another method
                    winners.add(horse);                 // to see if the horse has finished
                }
            }

            if (!winners.isEmpty()) {
                finished = true;
                if (winners.size() == 1) { // checks to see if there's one winner
                    Horse soleWinner = winners.get(0); // retrieves the first and only index from winners (i.e. the one winner)
                    System.out.println("And the winner is " + soleWinner.getName());
                    soleWinner.increaseConfidence(0.1);
                } else {
                    System.out.print("It's a tie between: ");
                    for (int i = 0; i < winners.size(); i++) { // loop to return all names in the winners list
                        System.out.print(winners.get(i).getName());
                        if (i < winners.size() - 1) {
                            System.out.print(", ");
                        }
                        winners.get(i).increaseConfidence(0.1); // all tied winners gain confidence
                    }
                    System.out.println("!");
                }
            }

            try {
                Thread.sleep(100);  // delays the race display, making the race look seamless
            } catch (InterruptedException e) { // handles the exception occurring if the thread is interrupted during the sleep
                e.printStackTrace(); // prints stack trace of exception helping with debugging by showing where the exception occurred
            }
        }
    }

    private void moveHorse(Horse theHorse) {
        if (!theHorse.hasFallen()) {
            if (Math.random() < theHorse.getConfidence()) { // random chance of moving forward based on confidence
                theHorse.moveForward();
            }

            // random chance to fall based on confidence
            if (Math.random() < (0.025 * theHorse.getConfidence() * theHorse.getConfidence())) {
                theHorse.fall();  // decreases confidence by 0.1
            }
        }
    }

    private boolean raceWonBy(Horse theHorse) {
        return theHorse.getDistanceTravelled() == raceLength;
    }

    private void printRace() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }

        // prints race border
        for (int i = 0; i < raceLength + 3; i++) {
            System.out.print("=");
        }
        System.out.println();

        // print lanes and horses
        for (Horse horse : lanes) {
            printLane(horse);
        }

        // prints race border
        for (int i = 0; i < raceLength + 3; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    private void printLane(Horse horse) {
        if (horse == null) {
            System.out.print("|");
            for (int i = 0; i < raceLength; i++) {
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
            return;
        }

        int spacesBefore = horse.getDistanceTravelled();
        int spacesAfter = raceLength - horse.getDistanceTravelled();

        System.out.print("|");
        for (int i = 0; i < spacesBefore; i++) {
            System.out.print(" ");
        }

        if (horse.hasFallen()) {
            System.out.print("x");
        } else {
            System.out.print(horse.getSymbol());
        }


        for (int i = 0; i < spacesAfter; i++) {
            System.out.print(" ");
        }

        System.out.print("|");
        System.out.print("  " + horse.getName() + " (Current confidence " + String.format("%.1f", horse.getConfidence()) + ")");
        System.out.println();
    }
}
