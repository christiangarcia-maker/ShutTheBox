package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

//Random number generator shared by the entire application. 
public static Random random = new Random();

// User input reader shared by the entire application.
public static Scanner reader = new Scanner(System.in);


/*
 * The primary method that initiates and controls the flow of the game "Shut the Box". 
 * Manages player creation, turn execution, and winner determination.
 * 
 */
public static void main(String[] args) {
	
	// Try-catch block to handle possible errors during execution
	try {
		
		// Request and create Player 1
		System.out.println("Player 1 enter your name: ");
		Player player1 = new Player(Player.isVoid()); //  isVoid() validates that the name is not empty

		// Request and create Player 2
		System.out.println("Player 2 enter your name: ");
		Player player2 = new Player(Player.isVoid());
		
		// Game start message
		System.out.println("----SHUT THE BOX STARTS-----");
		
		
		//=====================================
		// 				PLAYER 1'S TURN
		//=====================================
		
		// Executes Player 1's turn and updates their board with the result 
		player1.setTiles(Function.turn(player1.getTiles(), player1.getName(), player1.getDice()));
		
		// Check if player 1 has won immediately
		if (Function.getScore(player1.getTiles()) == 0) {
			System.out.println(player1.getName().toUpperCase()+" WIN!");
			return; // Exits the main method, ending the game
		}
		
		
		//=====================================
		// 				PLAYER 2'S TURN
		//=====================================
		
		// Execute player 2's turn and update their board with the result
		player2.setTiles(Function.turn(player2.getTiles(), player2.getName(), player2.getDice()));
		
		// Check if player 2 has won immediately
		if (Function.getScore(player2.getTiles()) == 0) {
			System.out.println(player2.getName().toUpperCase()+" WIN!");
			return; // Exits the main method, ending the game 
		}
		
		
		//=====================================
		// 		CALCULATION OF FINAL SCORES
		//=====================================
		
		// If no player won immediately, the scores are calculated 
		int scorePlayer1 = Function.endTurn(player1.getTiles(), player1.getName());
		int scorePlayer2 = Function.endTurn(player2.getTiles(), player2.getName());
		
		
		//=====================================
		// 			DETERMINE THE WINNER
		//=====================================
		
		// Tie in case - same scores
		System.out.println("\n----GAME OVER-----");
		if (scorePlayer1 == scorePlayer2) {
			System.out.println("Draw!");
			
		// Player 1 win
		} else if (scorePlayer1 < scorePlayer2) {
			System.out.println(player1.getName() + " wins!");
			
		// Player 2 win
		} else if (scorePlayer1 > scorePlayer2){
			System.out.println(player2.getName() + " wins!");
		}
		

		//=====================================
		// 				CATCH
		//=====================================
		
		// Specific error when the user enters an incorrect data type
		} catch (InputMismatchException e) {
			System.out.println("Error: Invalid input. The game will close.");
			
		// Error when attempting to access an object that has not been initialized
		} catch (NullPointerException e) {
			System.out.println("Error: A problem occurred with the player data.");
			
		// Captures any other type of exception not specifically covered
		} catch (Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
			} finally {
				
				// Block that ALWAYS runs, regardless of whether there is an error or not
				// Important: close the Scanner to free up system resources
				reader.close();
			}
			
	}

}