package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);
	
	/*// Comprobar si el nombre esta almacenado y leer el nombre
		public static String isVoid(String name, Scanner sc) {
			name = sc.next();
			while (name.isEmpty() || name == null) {
				System.out.println("Introduce your name");
				name = sc.next();
			}
			return name; 
		}*/
	
	// ====================================================
	// = He hecho este main para probar, todo funciona :) =
	// ====================================================
	
	/*public static void main(String[] args) {
		
		String j1n = "Jugador 1";
		String j2n = "Jugador 2";
		
		int[] j1 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		int[] j2 = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
		
		j1 = turn(j1, j1n);
		int pointsJ1 = endTurn(j1, j1n);
		
		j2 = turn(j2, j2n);
		int pointsJ2 = endTurn(j2, j2n);
		
		if (pointsJ1 == pointsJ2) {
			System.out.println("Empate");
		} else if (pointsJ1 < pointsJ2) {
			System.out.println("Gana " + j1n );
		} else {
			System.out.println("Gana " + j2n );
		}
		
	}*/
		
	public static int[] turn(int[] player, String playername) {
		
		System.out.println("Turn of " + playername);
		System.out.println();
		
		return dices(player);
	}
	
	public static int[] dices(int[] player) {

		int throw1 = random.nextInt(1,7);
		int throw2 = random.nextInt(1,7);
		int throwsTotal = throw1+throw2;
		
		System.out.println("You got " + throw1 + " and " + throw2 + ", total is " + throwsTotal);
		
		boolean canPlay = checkTiles(player, throwsTotal);
		if (canPlay) {
			return select(player, throwsTotal);
		}
		return player;
	}
	
	public static boolean checkTiles(int[] player, int throwTotal) {		
		boolean possibleMove = false;
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player.length; j++) {
				if (player[i] != player[j] && player[i] + player[j] == throwTotal) {
					possibleMove = true;
				}
			}
		}
		missingTiles(player);
		System.out.println("");
		if (possibleMove) {
			System.out.println("You can make a move!");
			return true;
		} else {
			System.out.println("You can't make a move!");
			return false;
		}
	}
	
	public static void missingTiles(int[] player) {
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
	}
	
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
						System.out.println("Select how many numbers to play: ");
						election = sc.nextInt();
						assert (election>0&&election<=4);
						correctValue = true;
					} catch (AssertionError e) {
						System.out.println("Incorrect input, try again");
					}
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again");
				}
			} while (!correctValue);
			numbers = new int[election];
			int num = 0;
			for (int i = 1; i <= election; i++) {
				System.out.println("Introduce number " + i + ": ");
				numbers[i-1] = sc.nextInt();
				num += numbers[i-1];
			}
			if (num != throwTotal) {
				System.out.println("Not possible");
				possible = false;
			}
		} while (!possible);
		
		return playTiles(player, numbers, throwTotal);
		
	}
	
	public static int[] playTiles(int[] player, int[] numbers, int throwTotal) {
		int[] playerCopy = new int[player.length];
		for (int i = 0; i < player.length; i++) {
			playerCopy[i] = player[i];
			for (int j = 0; j < numbers.length; j++) {
				if (player[i] == numbers[j]) {
					playerCopy[i] = 0;
					numbers[j] = 0;
				}
			}
		}
		int rest = 0;
		for (int num : numbers) {
			rest += num;
		}
		if (rest != 0) {
			System.out.println("Wrong combination!");
			return select(player, throwTotal);
		} else {
			for (int i = 0; i < player.length; i++) {
				player[i] = playerCopy[i];
			}
		}
		return dices(player);
	}
	
	
	public static int endTurn(int[] player, String playerName) {
		int points = 0;
		for (int i = 0; i < player.length; i++) {
			points += player[i];
		}
		System.out.println(playerName + "'s turn has ended, points = " + points);
		
		return points;
	}

}
