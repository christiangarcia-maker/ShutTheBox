package shutthebox;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Functions {
	
	// Objetos estáticos para generar números aleatorios y leer entrada del usuario
	static Random random = new Random();
	static Scanner sc = new Scanner(System.in);
	
	// Comprobar si el nombre esta almacenado y leer el nombre
	public static String isVoid(String name, Scanner sc) {
		
		try {
			name = sc.nextLine();
			// Bucle que se repite mientras el nombre esté vacío o sea nulo
			while (name.isEmpty() || name == null) {
				System.out.println("Introduce your name");
				name = sc.nextLine();
				sc.nextLine(); //Limpia buffer por si vuelve a leer 
			}
			return name;
		} catch (Exception e) {
			System.out.println("Error reading name");
			return "Player";
		}
	}
	

	// Inicia el turno de un jugador mostrando su nombre
	public static int[] turn(int[] player, String playername) {
		
		System.out.println("Turn of " + playername);
		System.out.println();
		
		// Llama a la función de lanzamiento de dados
		return dices(player);
	}
	
	// Lanza dos dados y procesa el resultado del turno
	public static int[] dices(int[] player) {

		// Genera dos números aleatorios entre 1 y 6 (simula lanzar dos dados)
		int throw1 = random.nextInt(1,7);
		int throw2 = random.nextInt(1,7);
		int throwsTotal = throw1+throw2;
		
		System.out.println("You got " + throw1 + " and " + throw2 + ", total is " + throwsTotal);
		
		// Verifica si el jugador puede hacer un movimiento con este total
		boolean canPlay = checkTiles(player, throwsTotal);
		if (canPlay) {
			// Si puede jugar, permite seleccionar fichas
			return select(player, throwsTotal);
		}
		// Si no puede jugar, termina el turno y devuelve el array sin cambios
		return player;
	}
	
	// Verifica si es posible hacer un movimiento válido con el total de los dados
	public static boolean checkTiles(int[] player, int throwTotal) {		
		boolean possibleMove = false;
		// Recorre todas las combinaciones posibles de dos fichas
		for (int i = 0; i < player.length; i++) {
			for (int j = 0; j < player.length; j++) {
				// Si dos fichas diferentes suman el total de los dados, hay movimiento posible
				if (player[i] != player[j] && player[i] + player[j] == throwTotal) {
					possibleMove = true;
				}
			}
		}
		// Muestra las fichas que le quedan al jugador
		missingTiles(player);
		System.out.println("");
		if (possibleMove) {
			System.out.println("\nYou can make a move!");
			return true;
		} else {
			System.out.println("\nYou can't make a move!");
			return false;
		}
	}
	
	// Muestra las fichas que aún están disponibles para el jugador
	public static void missingTiles(int[] player) {
		System.out.print("You missing the tiles: ");
		boolean firstTile = true;
		// Recorre el array y muestra solo las fichas que no son 0 (las disponibles)
		for (int i = 0; i < player.length; i++) {
			if (player[i] != 0) {
				if (firstTile) {
					System.out.print(player[i]);
					firstTile = false;
				} else {
					// Añade comas entre los números
					System.out.print(", " + player[i]);
				}
			}
		}
	}
	
	// Permite al jugador seleccionar qué fichas quiere jugar
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
						System.out.println("\nSelect how many numbers to play: ");
						election = sc.nextInt();
						// Verifica que la elección esté entre 1 y 4 fichas
						assert (election>0&&election<=4);
						correctValue = true;
					} catch (AssertionError e) {
						System.out.println("Incorrect input, try again");
						sc.nextLine(); // Limpia el buffer
					}
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again");
					sc.nextLine(); // Limpia el buffer
				}
			} while (!correctValue);
			
			// Crea un array para almacenar los números seleccionados
			numbers = new int[election];
			int num = 0;
			
			// Lee cada número que el jugador quiere jugar
			try {
				for (int i = 1; i <= election; i++) {
					System.out.println("Introduce number " + i + ": ");
					numbers[i-1] = sc.nextInt();
					num += numbers[i-1];
				}
				// Verifica que la suma de los números sea igual al total de los dados
				if (num != throwTotal) {
					System.out.println("Not possible");
					possible = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input, please enter numbers only");
				sc.nextLine(); // Limpia el buffer
				possible = false;
			}
		} while (!possible);
		
		// Procesa las fichas seleccionadas
		return playTiles(player, numbers, throwTotal);
		
	}
	
	// Procesa las fichas jugadas y actualiza el array del jugador
	public static int[] playTiles(int[] player, int[] numbers, int throwTotal) {
		// Crea una copia del array del jugador
		int[] playerCopy = new int[player.length];
		for (int i = 0; i < player.length; i++) {
			playerCopy[i] = player[i];
			// Busca coincidencias entre las fichas del jugador y los números seleccionados
			for (int j = 0; j < numbers.length; j++) {
				if (player[i] == numbers[j]) {
					// Marca la ficha como jugada (pone 0)
					playerCopy[i] = 0;
					numbers[j] = 0;
				}
			}
		}
		// Verifica que todos los números seleccionados existan en las fichas del jugador
		int rest = 0;
		for (int num : numbers) {
			rest += num;
		}
		// Si quedan números sin usar, la combinación es incorrecta
		if (rest != 0) {
			System.out.println("Wrong combination!");
			// Vuelve a pedir la selección
			return select(player, throwTotal);
		} else {
			// Si la combinación es correcta, actualiza el array del jugador
			for (int i = 0; i < player.length; i++) {
				player[i] = playerCopy[i];
			}
		}
		// Continúa con el siguiente lanzamiento de dados
		return dices(player);
	}
	
	// Calcula y muestra los puntos finales del jugador
	public static int endTurn(int[] player, String playerName) {
		int points = 0;
		// Suma todas las fichas que quedan sin jugar
		for (int i = 0; i < player.length; i++) {
			points += player[i];
		}
		System.out.println(playerName + "'s turn has ended, points = " + points);
		
		return points;
	}

}