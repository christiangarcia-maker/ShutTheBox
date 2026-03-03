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
			
			//P1 
			player1.setTiles(Function.turn(player1.getTiles(), player1.getName(), player1.getDice()));
			if (Function.getScore(player1.getTiles()) == 0) {
				System.out.println(player1.getName().toUpperCase()+" WIN!");
				return; //Sale del metodo 
			}
			
			
			//P2
			player2.setTiles(Function.turn(player2.getTiles(), player2.getName(), player2.getDice()));
			if (Function.getScore(player2.getTiles()) == 0) {
				System.out.println(player2.getName().toUpperCase()+" WIN!");
				return; //Sale del metodo 
			}
			
			//Socre 
			int scorePlayer1 = Function.endTurn(player1.getTiles(), player1.getName());
			int scorePlayer2 = Function.endTurn(player2.getTiles(), player2.getName());
			
			// Determine winner
			System.out.println("\n----GAME OVER-----");
			if (scorePlayer1 == scorePlayer2) {
				System.out.println("Draw!");
			} else if (scorePlayer1 < scorePlayer2) {
				System.out.println(player1.getName() + " wins!");
			} else if (scorePlayer1 > scorePlayer2){
				System.out.println(player2.getName() + " wins!");
			}else {
				System.out.println("Empate!");
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