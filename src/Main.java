import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // creates 10 pre-made horses
        Horse h1 = new Horse('a', "Ayo", 0.7);
        Horse h2 = new Horse('b', "Bilal", 0.6);
        Horse h3 = new Horse('c', "Charlie", 0.7);
        Horse h4 = new Horse('d', "Dave", 0.5);
        Horse h5 = new Horse('e', "Eric", 0.8);
        Horse h6 = new Horse('f', "Friday", 0.6);
        Horse h7 = new Horse('g', "Gregory", 0.4);
        Horse h8 = new Horse('h', "Harry", 0.4);
        Horse h9 = new Horse('i', "Ilyas", 0.6);
        Horse h10 = new Horse('j', "Jeremy", 0.8);

        Scanner scanner = new Scanner(System.in);

        // input validation for race distance
        int raceDistance = 0;
        while (raceDistance <= 0) {
            System.out.print("How long is the race? (in meters): ");
            if (scanner.hasNextInt()) {
                raceDistance = scanner.nextInt();
                if (raceDistance <= 0) {
                    System.out.println("Please enter a positive value for race distance.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for race distance.");
                scanner.next(); // consume the invalid input
            }
        }

        // input validation for number of lanes
        int numLanes = 0;
        while (numLanes <= 0) {
            System.out.print("How many lanes? ");
            if (scanner.hasNextInt()) {
                numLanes = scanner.nextInt();
                if (numLanes <= 0) {
                    System.out.println("Please enter a positive value for lanes.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for lanes.");
                scanner.next(); // consume the invalid input
            }
        }

        // input validation for number of races
        int numberOfRaces = 0;
        while (numberOfRaces <= 0) {
            System.out.print("How many races? ");
            if (scanner.hasNextInt()) {
                numberOfRaces = scanner.nextInt();
                if (numberOfRaces <= 0) {
                    System.out.println("Please enter a positive value for number of races.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number for races.");
                scanner.next(); // consume the invalid input
            }
        }


        for (int i = 1; i <= numberOfRaces; i++) {
            System.out.println("\nStarting race no. " + i + "...\n"); // prints line indicating the race is about to begin

            Race race = new Race(raceDistance, numLanes);

            // add horses to the race
            if (numLanes >= 1) race.addHorse(h1, 1);
            if (numLanes >= 2) race.addHorse(h2, 2);
            if (numLanes >= 3) race.addHorse(h3, 3);
            if (numLanes >= 4) race.addHorse(h4, 4);
            if (numLanes >= 5) race.addHorse(h5, 5);
            if (numLanes >= 6) race.addHorse(h6, 6);
            if (numLanes >= 7) race.addHorse(h7, 7);
            if (numLanes >= 8) race.addHorse(h8, 8);
            if (numLanes >= 9) race.addHorse(h9, 9);
            if (numLanes >= 10) race.addHorse(h10, 10);

            race.startRace(); // starts the race

            if (i < numberOfRaces) {
                System.out.print("\nAre you ready for the next race? (y/n): ");
                String response = scanner.next().toLowerCase();
                while (!response.equals("y") && !response.equals("n")) { // input validation
                    System.out.print("Invalid input. Please enter 'y' or 'n': ");
                    response = scanner.next().toLowerCase();
                }
                if (response.equals("n")) {
                    System.out.println("Leaving the races."); // terminates the program after a short message
                    break;
                }
            }

            System.out.println("\n----------------------------\n");
        }

        scanner.close();
    }
}
