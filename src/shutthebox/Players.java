package shutthebox;

public class Players {

	// Private class attributes
	private String name; // Player's name
	private int[] tiles; // Array representing the player's 12 tiles
	private boolean canContinue; // Indicates if the player can continue playing

	// Constructor that receives the player's name
	public Players(String name) {
		try {
			this.name = name;
			this.canContinue = true; // At the beginning, the player can always continue
			// Initializes the array with the 12 numbered tiles from 1 to 12
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		} catch (Exception e) {
			// In case of error, initialize with default values
			this.name = "Player";
			this.canContinue = true;
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		}
	}

	// GETTERS
	
	// Returns whether the player can continue playing
	public boolean getCanContinue() {
		return canContinue;
	}

	// Returns the player's name
	public String getName () {
		return name;
	}

	// Returns the player's tiles array
	public int[] getTiles () {
		return tiles;
	}

	// SETTERS
	
	// Sets whether the player can continue playing
	public void setCanContinue(boolean canContinue) {
		this.canContinue = canContinue;
	}

	// Sets the player's name
	// Only changes it if the name is not blank
	public void setName (String name) {
			if (!name.isBlank()) {
				// Removes leading and trailing spaces
				this.name = name.trim();
			}

	}

	// Sets the player's tiles array
	public void setTiles (int[] tiles) {
			this.tiles = tiles;

	}
}