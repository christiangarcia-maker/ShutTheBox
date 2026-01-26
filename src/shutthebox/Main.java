package shutthebox;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String p ="";
		
		System.out.println("Player 1 introduce your name: ");
		p = isVoid(p,sc);
		Players player1 = new Players(p);
		
		p = ""; 
		
		System.out.println("Player 2 introduce your name: ");
		p = isVoid(p,sc);
		Players player2 = new Players(p);
		
		
		sc.close();
		
	}
	
	public static void playerEnd(int[] player, boolean playerGame) {
		while (playerGame) {
			
		}
		
	}
	
	public static String isVoid(String name, Scanner sc) {
		name = sc.next();
		while (name.isEmpty() || name == null) {
			System.out.println("Introduce your name");
			name = sc.next();
		}
		return name; 
	}

}
