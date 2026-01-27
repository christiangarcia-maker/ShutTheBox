package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);
	
	// Comprobar si el nombre esta almacenado y leer el nombre
		public static String isVoid(String name, Scanner sc) {
			name = sc.next();
			while (name.isEmpty() || name == null) {
				System.out.println("Introduce your name");
				name = sc.next();
			}
			return name; 
		}
	
		
		
	public static void turn(int[] player) {
		
		int throw1 = random.nextInt(1,7);
		int throw2 = random.nextInt(1,7);
		int throwsTotal = throw1+throw2;
		
		System.out.println("You got " + throw1 + " and " + throw2 + ", total is " + throwsTotal);
		
		checkTiles(player, throwsTotal);
				
	}
	
	public static void checkTiles(int[] player, int throwTotal) {		
		boolean possibleMove = false;
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player.length; j++) {
				if (player[i] != player[j] && player[i] + player[j] == throwTotal) {
					possibleMove = true;
				}
			}
		}
		if (possibleMove) {
			System.out.println("You can make a move!");
			select(player, throwTotal);
		} else {
			System.out.println("You can't make a move!");
			endTurn(player);
		}
	}
	
	public static void select(int[] player, int throwTotal) {
		System.out.print("You missing the tiles: ");
		boolean firstTile = true;
		for (int i = 0; i < player.length; i++) {
			if (player[i] != 0) {
				if (firstTile) {
					System.out.print(player[i]);
					firstTile = false;
				} else {
					System.out.print(", " + player[i]);
				}
			}
		}
		
		boolean correctValue = false;
		int election = 0;
		do {
			try {
				try {
					System.out.println("\n¿Do you want to use just the number or a combination? (1/2): ");
					election = sc.nextInt();
					assert (election>0&&election<=2);
					correctValue = true;
				} catch (AssertionError e) {
					System.out.println("Incorrect input, try again");
				}
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input, try again");
			}
		} while (!correctValue);
		
		
	}
	
	
	
	public static void endTurn(int[] player) {
		
	}

}
