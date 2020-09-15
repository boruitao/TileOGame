package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.util.HashMap;


public class UITile {
	
	private DesignModeResources.Type type;
	
	//Only used for action
	private int cooldown;
	
	private boolean Visited;
	//0 means no player visited. The number correspond to what player is visiting it.
	private int currentPlayer;
	
	public UITile(DesignModeResources.Type t, int player, int c, boolean visited){
		setType(t);
		setCurrentPlayer(player);
		setCooldown(c);
		setVisited(visited);
	}

	public boolean isVisited() {
		return Visited;
	}

	public void setVisited(boolean visited) {
		Visited = visited;
	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public DesignModeResources.Type getType() {
		return type;
	}

	public void setType(DesignModeResources.Type type) {
		this.type = type;
	}
	
	
}
