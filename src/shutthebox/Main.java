package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static Random random = new Random();
	public static Scanner reader = new Scanner(System.in);

	public static void main(String[] args) {

		try {

			System.out.println("Player 1 enter your name: ");
			Player player1 = new Player(Player.isVoid());

			System.out.println("Player 2 enter your name: ");
			Player player2 = new Player(Player.isVoid());

			System.out.println("----SHUT THE BOX STARTS-----");

			// Player 1 turn
			int[] updatedTiles1 = Function.turn(player1.getTiles(), player1.getName());
			player1.setTiles(updatedTiles1);

			// Player 2 turn
			int[] updatedTiles2 = Function.turn(player2.getTiles(), player2.getName());
			player2.setTiles(updatedTiles2);

			// Calculate final points
			int pointsP1 = Function.endTurn(player1.getTiles(), player1.getName());
			int pointsP2 = Function.endTurn(player2.getTiles(), player2.getName());

			// Determine winner
			System.out.println("\n----GAME OVER-----");

			if (pointsP1 == pointsP2) {
				System.out.println("Draw!");
			} else if (pointsP1 < pointsP2) {
				System.out.println(player1.getName() + " wins!");
			} else {
				System.out.println(player2.getName() + " wins!");
			}

		} catch (InputMismatchException e) {
			System.out.println("Error: Invalid input. The game will close.");
		} catch (NullPointerException e) {
			System.out.println("Error: A problem occurred with the player data.");
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		} finally {
			reader.close();
		}
	}

}