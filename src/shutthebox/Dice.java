package shutthebox;

public class Dice {

	// Private variable that stores the current value of the die (visible face)
	// Initializes to 1 by default
    private int value = 1;  // default 1
    /*
	 * Explication: 
     * When creating a given object, we need to randomize the index (Dice = {1,2,3,4,5,6}). 
	 * Therefore, we create a given object that directly stores a random value.
     */
	
    // Roll this dice (1-6) and update value
    public int roll() {
		// Uses the static random number generator from the Main class
		// "nextInt(1,7)" generates numbers from 1 to 6 (the upper limit is exclusive)
        this.value = Main.random.nextInt(1,7);
        return value;
    }

    // Get current value (after roll)
    public int getValue() {
        return value;
    }
	
	//=====================================
	// 				FUNCTIONS
	//=====================================
	
    // Roll two dice and return sum
    public static int rollTwo(Dice dice1, Dice dice2) {
        // Throw the first die
		dice1.roll(); 
		// Throw the second die
        dice2.roll();
		// Return the sum of the values obtained 
        return dice1.getValue() + dice2.getValue();
    }

    // Roll two dice and return individual values for display
    public static int[] rollTwoValues(Dice dice1, Dice dice2) {
		// Throw the first die
        dice1.roll();
		// Throw the second die
        dice2.roll();
		// Creates and returns an array with the two values
        return new int[]{dice1.getValue(), dice2.getValue()};
    }
	
	
}

