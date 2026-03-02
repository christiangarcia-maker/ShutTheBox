package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	// Static objects to generate random numbers and read user input
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);
	
	// Check if the name is stored and read the name
	public static String isVoid(String name, Scanner sc) {
		
		try {
			name = sc.nextLine();
			// Loop that repeats while the name is empty or null
			while (name == null || name.isEmpty()) {
				System.out.println("Enter your name");
				name = sc.nextLine();
				sc.nextLine(); // Clear buffer in case it reads again
			}
			return name;
		} catch (Exception e) {
			System.out.println("Error reading name");
			return "Player";
		}
	}
	

	// Starts a player's turn by displaying their name
	public static int[] turn(int[] player, String playername) {
		
		System.out.println("Turn of " + playername);
		System.out.println();
		
		// Calls the dice rolling function
		return dices(player);
	}
	
	// Rolls two dice and processes the turn result
	public static int[] dices(int[] player) {

		// Generates two random numbers between 1 and 6 (simulates rolling two dice)
		int throw1 = random.nextInt(1,7);
		int throw2 = random.nextInt(1,7);
		int throwsTotal = throw1 + throw2;
		
		System.out.println("You got " + throw1 + " and " + throw2 + ", total is " + throwsTotal);
		
		// Checks if the player can make a move with this total
		boolean canPlay = checkTiles(player, throwsTotal);
		if (canPlay) {
			// If they can play, allow tile selection
			return select(player, throwsTotal);
		}
		// If they cannot play, end the turn and return the array unchanged
		return player;
	}
	
	// Checks if it is possible to make a valid move with the dice total
	public static boolean checkTiles(int[] player, int throwTotal) {		
		boolean possibleMove = false;
		// Loops through all possible combinations of two tiles
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player.length; j++) {
				// If two different tiles add up to the dice total, a move is possible
				if (player[i] != player[j] && player[i] + player[j] == throwTotal) {
					possibleMove = true;
				}
			}
		}
		// Shows the tiles the player still has
		missingTiles(player);
		System.out.println("");
		if (possibleMove) {
			System.out.println("\nYou can make a move!");
			return true;
		} else {
			System.out.println("\nYou can't make a move!");
			return false;
		}
	}
	
	// Displays the tiles that are still available for the player
	public static void missingTiles(int[] player) {
		System.out.print("Remaining tiles: ");
		boolean firstTile = true;
		// Loops through the array and shows only tiles that are not 0 (available ones)
		for (int i = 0; i < player.length; i++) {
			if (player[i] != 0) {
				if (firstTile) {
					System.out.print(player[i]);
					firstTile = false;
				} else {
					// Adds commas between numbers
					System.out.print(", " + player[i]);
				}
			}
		}
	}
	
	// Allows the player to select which tiles they want to play
	public static int[] select(int[] player, int throwTotal) {
		boolean correctValue = false;
		int election = 0;
		boolean possible;
		int[] numbers;
		do {
			possible = true;
			do {
				try {
					try {
						System.out.println("\nSelect how many numbers to play: ");
						election = sc.nextInt();
						// Checks that the selection is between 1 and 4 tiles
						assert (election > 0 && election <= 4);
						correctValue = true;
					} catch (AssertionError e) {
						System.out.println("Incorrect input, try again");
						sc.nextLine(); 
					}
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again");
					sc.nextLine(); 
				}
			} while (!correctValue);
			
			// Creates an array to store selected numbers
			numbers = new int[election];
			int num = 0;
			
			// Reads each number the player wants to play
			try {
				for (int i = 1; i <= election; i++) {
					System.out.println("Enter number " + i + ": ");
					numbers[i-1] = sc.nextInt();
					num += numbers[i-1];
				}
				// Checks that the sum of the numbers equals the dice total
				if (num != throwTotal) {
					System.out.println("Not possible");
					possible = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter numbers only");
				sc.nextLine(); 
				possible = false;
			}
		} while (!possible);
		
		// Process the selected tiles
		return playTiles(player, numbers, throwTotal);
		
	}
	
	// Processes played tiles and updates the player's array
	public static int[] playTiles(int[] player, int[] numbers, int throwTotal) {
		// Creates a copy of the player's array
		int[] playerCopy = new int[player.length];
		for (int i = 0; i < player.length; i++) {
			playerCopy[i] = player[i];
			// Looks for matches between player tiles and selected numbers
			for (int j = 0; j < numbers.length; j++) {
				if (player[i] == numbers[j]) {
					// Marks the tile as played (sets to 0)
					playerCopy[i] = 0;
					numbers[j] = 0;
				}
			}
		}
		// Checks that all selected numbers exist in the player's tiles
		int rest = 0;
		for (int num : numbers) {
			rest += num;
		}
		// If there are unused numbers left, the combination is incorrect
		if (rest != 0) {
			System.out.println("Wrong combination!");
			// Ask for selection again
			return select(player, throwTotal);
		} else {
			// If the combination is correct, update the player's array
			for (int i = 0; i < player.length; i++) {
				player[i] = playerCopy[i];
			}
		}
		// Continue with the next dice roll
		return dices(player);
	}
	
	// Calculates and displays the player's final points
	public static int endTurn(int[] player, String playerName) {
		int points = 0;
		// Adds all remaining unplayed tiles
		for (int i = 0; i < player.length; i++) {
			points += player[i];
		}
		System.out.println(playerName + "'s turn has ended, points = " + points);
		
		return points;
	}

}