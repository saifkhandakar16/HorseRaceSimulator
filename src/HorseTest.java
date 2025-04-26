public class HorseTest {
    public static void main(String[] args) {
        Horse testHorse = new Horse('t', "TestHorse", 0.8);


        // test 2: fall()
        System.out.println("Fallen =  " + testHorse.hasFallen() + ", Confidence = " + testHorse.getConfidence());
        testHorse.fall();
        System.out.println("Fallen =  " + testHorse.hasFallen() + ", Confidence = " + testHorse.getConfidence()); // expected output is true, 0.7

        // test 3: goBackToStart()
        System.out.println("Test 3: goBackToStart()");
        testHorse.goBackToStart();
        System.out.println("After: Distance = " + testHorse.getDistanceTravelled() + ", Fallen = " + testHorse.hasFallen()); // expected output is 0, false
        System.out.println();

        // test 4: increaseConfidence()
        System.out.println("Confidence before = " + testHorse.getConfidence());
        testHorse.increaseConfidence(0.3);
        System.out.println("Confidence after adding 0.3 = " + testHorse.getConfidence()); // expected output is 1.0 max
        testHorse.increaseConfidence(0.2);
        System.out.println("Confidence after adding 0.2 = " + testHorse.getConfidence()); // expected output is still 1.0

        // test 5: hasFallen()
        System.out.println("");
        System.out.println("Test 5: hasFallen()");
        testHorse.fall();
        System.out.println("Should be true: " + testHorse.hasFallen()); // expected output is true
        System.out.println();

        // test 6: getConfidence()
        System.out.println("Test 6: getConfidence()");
        System.out.println("Confidence is: " + testHorse.getConfidence()); // expected output should reflect post fall value
        System.out.println();

        // test 7: getDistanceTravelled()
        System.out.println("Test 7: getDistanceTravelled()");
        testHorse.goBackToStart();
        testHorse.moveForward();
        testHorse.moveForward();
        System.out.println("Distance = " + testHorse.getDistanceTravelled()); // expected output is 2
        System.out.println();

        // test 8: getSymbol()
        System.out.println("Test 8: getSymbol()");
        System.out.println("Symbol: " + testHorse.getSymbol()); // expected output is 't'
        System.out.println();

        // test 9: getName()
        System.out.println("Test 9: getName()");
        System.out.println("Name: " + testHorse.getName()); // expected output is "Test Horse"
        System.out.println();
    }
}
