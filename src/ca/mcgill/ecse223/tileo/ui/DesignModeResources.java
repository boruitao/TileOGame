package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;

import java.util.List;
import java.util.*;

public class DesignModeResources {
	
	final Font DesignModeFont = new Font("Arial",Font.BOLD,25);
	final Font ActionCardsFont = new Font("Arial",Font.BOLD,20);
	final Font ActionFont = new Font("Arial",Font.PLAIN,15);
	final Font PlusMinusFont = new Font("Arial",Font.PLAIN,15);
	
	final Font ActionTileFont = new Font("Arial",Font.BOLD,12);
	
	final int xTiles = 18;
	final int yTiles = 10;
	final int block_Size = 18;
	final int inner_Size = 14;
	
	enum State {Nothing, PlaceTile, PlaceWinTile, PlaceActionTile, AddConnection1, AddConnection2 , RemoveConnection1, RemoveConnection2, RemoveTile, Player1, Player2, Player3, Player4}
	State state;
	List<UIConnection> uiconnect = new ArrayList<UIConnection>();;
	UITile uitile[][];
	
	String ExtraTurnText = "0";
	String AddConnectionText= "0";
	String RemoveConnectionText="0";
	String MovePlayerText="0";
	String LoseTurnText="0";
	String SwapPositionText = "0";
	String WinHintText = "0";
	String TeleAllText = "0";

	HashMap<Integer, String> actioncardnumber = new HashMap<Integer, String>();
	
	Color background = Color.WHITE;
	
	HashMap<Boolean, Color> outerTile= new HashMap<Boolean, Color>();
	
	public enum Type{Empty, Normal, Win, Action}
	
	HashMap<Integer, Color> playercolor = new HashMap<Integer, Color>();
	
	public DesignModeResources(){
		state = State.Nothing;
		outerTile.put(true, Color.BLACK);
		outerTile.put(false, Color.LIGHT_GRAY);
		
		actioncardnumber.put(0, ExtraTurnText);
		actioncardnumber.put(1, AddConnectionText);
		actioncardnumber.put(2, RemoveConnectionText);
		actioncardnumber.put(3, MovePlayerText);
		actioncardnumber.put(4, LoseTurnText);
		
		playercolor.put(1, new Color(255,0,0,128));
		playercolor.put(2, new Color(0,0,255,128));
		playercolor.put(3, new Color(0,255,0,128));
		playercolor.put(4, new Color(255,255,0,128));
	}
}
