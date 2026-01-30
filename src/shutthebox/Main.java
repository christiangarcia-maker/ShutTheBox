package shutthebox;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			String p = "";

			System.out.println("Player 1 introduce your name: ");
			p = Functions.isVoid(p, sc);
			Players player1 = new Players(p);

			p = "";

			System.out.println("Player 2 introduce your name: ");
			p = Functions.isVoid(p, sc);
			Players player2 = new Players(p);

			System.out.println("----SHUT THE BOX STARTS-----");

			// Turno del jugador 1
			if (player1.getSeguir()) {
				int[] updatedTiles1 = Functions.turn(player1.getTiles(), player1.getName());
				player1.setTiles(updatedTiles1);
			}
			
			// Turno del jugador 2
			if (player2.getSeguir()) {
				int[] updatedTiles2 = Functions.turn(player2.getTiles(), player2.getName());
				player2.setTiles(updatedTiles2);
			}

			// Calcular puntos finales
			int pointsP1 = Functions.endTurn(player1.getTiles(), player1.getName());
			int pointsP2 = Functions.endTurn(player2.getTiles(), player2.getName());

			// Determinar ganador
			System.out.println("\n----GAME OVER-----");
			if (pointsP1 == pointsP2) {
				System.out.println("¡Empate!");
			} else if (pointsP1 < pointsP2) {
				System.out.println("¡Gana " + player1.getName() + "!");
			} else {
				System.out.println("¡Gana " + player2.getName() + "!");
			}

		} catch (InputMismatchException e) {
			System.out.println("Error: Entrada inválida. El juego se cerrará.");
		} catch (NullPointerException e) {
			System.out.println("Error: Se produjo un error con los datos del jugador.");
		} catch (Exception e) {
			System.out.println("Error inesperado: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}