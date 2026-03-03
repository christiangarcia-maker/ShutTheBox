package shutthebox;

public class Player {

	// Private class attributes
	private String name; // Player's name
	private int[] tiles; // Array representing the player's 12 tiles


	// Constructor that receives the player's name
	public Player(String name) {
		try {
			this.name = (name == null || name.isBlank()) ? "Player" : name.trim();
			// Initializes the array with the 12 numbered tiles from 1 to 12
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		} catch (Exception e) {
			// In case of error, initialize with default values
			this.name = "Default Player";
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		}
	}
	//=====================================
	// 				GETTERS
	//=====================================

	// Returns the player's name
	public String getName () {
		return name;
	}

	// Returns the player's tiles array
	public int[] getTiles () {
		return tiles;
	}

	// Returns the sum of all remaining tiles (the player's score). Lower is better.
	public int getScore() {
		int sum = 0;
		for (int t : tiles) sum += t;
		return sum;
	}

	// Returns true if all tiles have been closed
	public boolean hasShutTheBox() {
		return getScore() == 0;
	}

	//=====================================
	// 				SETTERS
	//=====================================
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