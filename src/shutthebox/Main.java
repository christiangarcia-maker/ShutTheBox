package shutthebox;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// p = función para almacenar los nombres de los jugadores 
		String p ="";
		
		System.out.println("Player 1 introduce your name: ");
		p = Functions.isVoid(p,sc);
		Players player1 = new Players(p);
		
		p = ""; 
		
		System.out.println("Player 2 introduce your name: ");
		p = Functions.isVoid(p,sc);
		Players player2 = new Players(p);
		
		
		while(player1.)
		
		sc.close();
		
	}
	
	public static void playerEnd(int[] player, boolean playerGame) {
		while (playerGame) {
			
		}
		
	}
	


}
