package shutthebox;

public class Players {
	
	private String name;
	private int[] tiles;
	private boolean puedeSeguir;
	
	public Players(String name) {
		this.name = name;
		this.puedeSeguir = true;
		this.tiles = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
	}
	
	//GETTERS
	public boolean getSeguir() {
		return puedeSeguir; 
	}
	
	public String getName () {
		return name;
	}
	
	public int[] getTiles () {
		return tiles;
	}
	
	
	//SETTERS
	public void setSeguir(boolean puedeSeguir) {
		this.puedeSeguir = puedeSeguir;
	}

	public void setName (String name) {
		if (!name.isBlank()) {
			this.name = name.trim();
		}
	}
	
	public void setTiles (int[] tiles) {
		this.tiles =tiles;
	}
}