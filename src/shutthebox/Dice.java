package shutthebox;

public class Dice {
	
    private int value = 1;  // default 1
    /*
     * Explicación ¿?
     * Al crear un objeto dado necesitariamos aletarorio el indice (Dice = {1,2,3,4,5,6})
     * Entonces creamos un objeto dado que directamente almacene un valor aleatorio 
     */
    // Roll this die (1-6) and update value
    public int roll() {
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
    public static int rollTwo(Dice die1, Dice die2) {
        die1.roll();
        die2.roll();
        return die1.getValue() + die2.getValue();
    }

    // Roll two dice and return individual values for display
    public static int[] rollTwoValues(Dice die1, Dice die2) {
        die1.roll();
        die2.roll();
        return new int[]{die1.getValue(), die2.getValue()};
    }
	
	
}
