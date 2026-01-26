package shutthebox;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Player 1 introduce your name: ");
		String p = sc.next();
		Players player1 = new Players(p);
		
		p = null; 
		
		System.out.println("Player 2 introduce your name: ");
		p = sc.next();
		Players player2 = new Players(p);
		
		while (playerGame) {
			
		}
		
		
		sc.close();
		
	}
	
	public static void playerEnd(int[] player, boolean playerGame) {
		while (playerGame) {
			
		}
		
	}
	
	public static String isVoid(String name, Scanner sc) {
		while (name.isEmpty()) {
			System.out.println("Introduce your name");
			name = sc.next();
		}
		return name; 
	}

}
