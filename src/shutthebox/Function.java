package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Function {

	// Static objects to generate random numbers and read user input
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);

	// Check if the name is stored and read the name
	public static String isVoid(String name, Scanner sc) {

		try {
			name = sc.nextLine().trim();

			// Loop that repeats while the name is empty
			while (name.isBlank()) {
				System.out.println("Enter your name:");
				name = sc.nextLine().trim();
			}

		} catch (Exception e) {
			System.out.println("Error reading name.");
			name = "Player";
		}

		return name;
	}
	
	// Starts a player's turn by displaying their name
	public static int[] turn(int[] player, String playerName) {

		System.out.println("\nTurn of " + playerName);

		boolean canContinue = true;

		while (canContinue) {

			canContinue = dices(player);

			// Check if all tiles are closed
			boolean allClosed = true;
			for (int i = 0; i < player.length; i++) {
				if (player[i] != 0) {
					allClosed = false;
				}
			}

			if (allClosed) {
				canContinue = false;
			}
		}

		return player;
	}

	// Rolls two dices and processes the turn result
	public static boolean dices(int[] player) {

		int throw1 = random.nextInt(6) + 1;
		int throw2 = random.nextInt(6) + 1;
		int total = throw1 + throw2;

		System.out.println("You got " + throw1 + " and " + throw2 + " = " + total);

		boolean canPlay = checkTiles(player, total);

		if (canPlay) {
			select(player, total);
			return true;
		}

		return false;
	}

	// Checks if it is possible to make a valid move with the dice total
	public static boolean checkTiles(int[] player, int total) {

		missingTiles(player);
		System.out.println();

		// Check single number
		for (int i = 0; i < player.length; i++) {
			if (player[i] == total) {
				System.out.println("You can make a move!");
				return true;
			}
		}

		// Check sum of two numbers
		for (int i = 0; i < player.length; i++) {
			for (int j = i + 1; j < player.length; j++) {
				if (player[i] != 0 && player[j] != 0) {
					if (player[i] + player[j] == total) {
						System.out.println("You can make a move!");
						return true;
					}
				}
			}
		}

		System.out.println("You can't make a move!");
		return false;
	}

	// Displays the tiles that are still available for the player
	public static void missingTiles(int[] player) {

		System.out.print("Remaining tiles: ");

		for (int i = 0; i < player.length; i++) {
			if (player[i] != 0) {
				System.out.print(player[i] + " ");
			}
		}
	}

	// Allows the player to select which tiles they want to play
	public static void select(int[] player, int total) {

		int count = 0;
		boolean validInput = false;

		// Control how many numbers the player wants to use
		while (!validInput) {
			try {
				System.out.println("How many numbers do you want to use?");
				count = sc.nextInt();
				validInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Enter a number.");
				sc.nextLine(); // clear buffer
			}
		}

		int[] numbers = new int[count];
		int sum = 0;

		for (int i = 0; i < count; i++) {
			boolean correctNumber = false;

			while (!correctNumber) {
				try {
					System.out.println("Enter number:");
					numbers[i] = sc.nextInt();
					sum += numbers[i];
					correctNumber = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. Enter a number.");
					sc.nextLine(); // clear buffer
				}
			}
		}

		if (sum == total) {
			playTiles(player, numbers);
		} else {
			System.out.println("Wrong sum. Try again.");
			select(player, total);
		}
	}

	// Processes played tiles and updates the player's array
	public static void playTiles(int[] player, int[] numbers) {

		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < numbers.length; j++) {
				if (player[i] == numbers[j]) {
					player[i] = 0;
					numbers[j] = 0;
				}
			}
		}
	}

	// Calculates and displays the player's final points
	public static int endTurn(int[] player, String playerName) {

		int points = 0;

		// Adds all remaining unplayed tiles
		for (int i = 0; i < player.length; i++) {
			points += player[i];
		}

		System.out.println(playerName + " points = " + points);

		return points;
	}
}