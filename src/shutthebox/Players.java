package shutthebox;

public class Players {

	// Atributos privados de la clase
	private String name; // Nombre del jugador
	private int[] tiles; // Array que representa las 12 fichas del jugador
	private boolean puedeSeguir; // Indica si el jugador puede continuar jugando

	// Constructor que recibe el nombre del jugador
	public Players(String name) {
		try {
			this.name = name;
			this.puedeSeguir = true; // Al inicio, el jugador siempre puede seguir
			// Inicializa el array con las 12 fichas numeradas del 1 al 12
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		} catch (Exception e) {
			// En caso de error, inicializa con valores por defecto
			this.name = "Player";
			this.puedeSeguir = true;
			this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
		}
	}

	//GETTERS
	
	// Devuelve si el jugador puede seguir jugando
	public boolean getSeguir() {
		return puedeSeguir;
	}

	// Devuelve el nombre del jugador
	public String getName () {
		return name;
	}

	// Devuelve el array de fichas del jugador
	public int[] getTiles () {
		return tiles;
	}


	//SETTERS
	
	// Establece si el jugador puede seguir jugando
	public void setSeguir(boolean puedeSeguir) {
		this.puedeSeguir = puedeSeguir;
	}

	// Establece el nombre del jugador
	// Solo lo cambia si el nombre no está en blanco
	public void setName (String name) {
		try {
			if (!name.isBlank()) {
				// Elimina espacios en blanco al inicio y final
				this.name = name.trim();
			}
		} catch (Exception e) {
			// Si hay error, no modifica el nombre
			System.out.println("Error setting name");
		}
	}

	// Establece el array de fichas del jugador
	public void setTiles (int[] tiles) {
		try {
			this.tiles = tiles;
		} catch (Exception e) {
			// Si hay error, mantiene el array actual
			System.out.println("Error setting tiles");
		}
	}
}