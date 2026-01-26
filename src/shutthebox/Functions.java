package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	Random random = new Random();
	Scanner sc = new Scanner(System.in);
	
	public void turn(int[] player) {
		
		int throw1 = random.nextInt(1,7);
		int throw2 = random.nextInt(1,7);
		int throwsTotal = throw1+throw2;
		
		System.out.println("You got a " + throw1 + " and a " + throw2 + ", total is " + throwsTotal);
		
		play(player, throwsTotal);
		
	}
	
	public void play(int[] player, int throwTotal) {
		
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
		
		try {
			boolean correctValue = false;
			do {
				try {
					try {
						System.out.println("¿Do you want to use the whole number or a combination? (1/2): ");
						int election = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Incorrect input, try again");
					}
				} catch (AssertionError e) {
					System.out.println("Incorrect input, try again");
				}
					
			} while (!correctValue);
			
		} catch (AssertionError e){
			
		}
		
	}

}
