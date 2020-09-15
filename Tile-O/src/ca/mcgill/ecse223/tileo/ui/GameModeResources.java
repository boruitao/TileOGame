package ca.mcgill.ecse223.tileo.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.mcgill.ecse223.tileo.controller.PlayController.PlayMode;
import ca.mcgill.ecse223.tileo.model.Game.Mode;
import ca.mcgill.ecse223.tileo.ui.DesignModeResources.State;

public class GameModeResources {
	
	final int xTiles = 18;
	final int yTiles = 10;
	final int block_Size = 18;
	final int inner_Size = 14;
	
	final Font ActionTileFont = new Font("Arial",Font.BOLD,12);
	
	enum GameState {Nothing, PlayerSelectingTile}
	
	GameState state;
	
	PlayMode currentmode;
	Mode gamemode;
	List<UIConnection> uiconnect = new ArrayList<UIConnection>();;
	UITile uitile[][];
	
	Color background = Color.WHITE;
	
	HashMap<Boolean, Color> outerTile= new HashMap<Boolean, Color>();
	
	
	HashMap<Integer, Color> playercolor = new HashMap<Integer, Color>();
	
	public GameModeResources(){
		state = GameState.Nothing;
		currentmode = PlayMode.Ready;
		gamemode = Mode.GAME;
		outerTile.put(true, Color.BLACK);
		outerTile.put(false, Color.LIGHT_GRAY);

		playercolor.put(1, new Color(255,0,0,128));
		playercolor.put(2, new Color(0,0,255,128));
		playercolor.put(3, new Color(0,255,0,128));
		playercolor.put(4, new Color(255,255,0,128));
	}
}