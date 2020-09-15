package ca.mcgill.ecse223.tileo.ui;

public class UIConnection {
	
	// From leftmost, uppermost Tile of the connection
	private int fromx;
	private int fromy;
	
	// Horizontal(true) or vertical(false) Tile.
	private boolean H_V;
	
	public UIConnection(int x, int y, boolean h_v){
		setFromx(x);
		setFromy(y);
		setH_V(h_v);
	}

	public int getFromx() {
		return fromx;
	}

	public void setFromx(int fromx) {
		this.fromx = fromx;
	}

	public int getFromy() {
		return fromy;
	}

	public void setFromy(int fromy) {
		this.fromy = fromy;
	}

	public boolean isH_V() {
		return H_V;
	}

	public void setH_V(boolean h_V) {
		H_V = h_V;
	}
}
