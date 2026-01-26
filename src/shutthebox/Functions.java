package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	Random random = new Random();
	
	public void turno(int[] jugador) {
		
		int tirada1 = random.nextInt(1,7);
		int tirada2 = random.nextInt(1,7);
		int tirada = tirada1+tirada2;
		
		System.out.println("Has sacado un " + tirada1 + " y un " + tirada2 + ", tienes un " + tirada);
		
		
		jugar(jugador, tirada);
		
	}
	
	public void jugar(int[] jugador, int tirada) {
		
		System.out.print("Te quedan las casillas: ");

		boolean primeraCasilla = true;
		
		for (int i = 0; i < jugador.length; i++) {
			if (jugador[i] != 0) {
				if (primeraCasilla) {
					System.out.print(jugador[i]);
					primeraCasilla = false;
				} else {
					System.out.print(", " + jugador[i]);
				}
			}
		}
		
		try {
			boolean valorCorrecto = false;
			try {
				
			} catch (InputMismatchException e) {
				
			}
			do {
				System.out.println("¿Quieres tirar la casilla con el numero o quieres hacer una combinacion? (1/2): ");
				
			} while (!valorCorrecto);
			
		} catch (AssertionError e){
			
		}
		
	}

}
