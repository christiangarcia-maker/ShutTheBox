package shutthebox;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			String p = "";

			System.out.println("Player 1 enter your name: ");
			p = Functions.isVoid(p, sc);
			Players player1 = new Players(p);

			p = "";

			System.out.println("Player 2 enter your name: ");
			p = Functions.isVoid(p, sc);
			Players player2 = new Players(p);

			System.out.println("----SHUT THE BOX STARTS-----");

			// Player 1 turn
			if (player1.getCanContinue()) {
				int[] updatedTiles1 = Functions.turn(player1.getTiles(), player1.getName());
				player1.setTiles(updatedTiles1);
			}
			
			// Player 2 turn
			if (player2.getCanContinue()) {
				int[] updatedTiles2 = Functions.turn(player2.getTiles(), player2.getName());
				player2.setTiles(updatedTiles2);
			}

			// Calculate final points
			int pointsP1 = Functions.endTurn(player1.getTiles(), player1.getName());
			int pointsP2 = Functions.endTurn(player2.getTiles(), player2.getName());

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
			sc.close();
		}
	}
}