package shutthebox;

public class Dice {
	
    private int value = 1;  // default 1
    /*
     * Explicación ¿?
     * Al crear un objeto dado necesitariamos aletarorio el indice (Dice = {1,2,3,4,5,6})
     * Entonces creamos un objeto dado que directamente almacene un valor aleatorio 
     */
    // Roll this dice (1-6) and update value
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
    public static int rollTwo(Dice dice1, Dice dice2) {
        dice1.roll();
        dice2.roll();
        return dice1.getValue() + dice2.getValue();
    }

    // Roll two dice and return individual values for display
    public static int[] rollTwoValues(Dice dice1, Dice dice2) {
        dice1.roll();
        dice2.roll();
        return new int[]{dice1.getValue(), dice2.getValue()};
    }
	
	
}
