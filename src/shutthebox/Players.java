package shutthebox;

public class Players {
	
	public Players(String name) {
		this.name = name;
		puedeSeguir = true;
		int[] tiles = {1,2,3,4,5,6,7,8,9,10,11,12};
	}
	
	private String name;
	private int[] tiles;
	private boolean puedeSeguir;

	public void setName (String name) {
		if (!name.isBlank()) {
			this.name = name.trim();
		}
	}
	
	public void setTiles (int[] tiles) {
		
	}
	
	public String getName (String name) {
		return name;
	}
	
	public int[] getTiles (int[] tiles) {
		return tiles;
	}
}