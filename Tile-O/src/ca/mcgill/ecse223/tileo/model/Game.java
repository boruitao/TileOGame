/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Player.PlayerStatus;

// line 11 "../../../../../TileO (updated Feb10).ump"
public class Game implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = -2627296689646846175L;
public static final int SpareConnectionPieces = 32;
  public static final int NumberOfActionCards = 32;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes
  private int currentConnectionPieces;

  //Game State Machines
  public enum Mode { DESIGN, GAME, GAME_WON, GAME_ROLLDIEACTIONCARD, GAME_CONNECTTILESACTIONCARD, GAME_REMOVECONNECTIONACTIONCARD, GAME_TELEPORTACTIONCARD, GAME_LOSETURNACTIONCARD, GAME_DRAWCARD, GAME_TAKETURN, GAME_WINHINTACTIONCARD, GAME_TELEALLPLAYERCARD, GAME_SWAPPOSITIONACTIONCARD, GAME_REVEALTILECARD, GAME_REVEALACTIONACTIONCARD, GAME_TURNINTOACTIONTILE, GAME_LOSERANDOMTURNSCARD}
  private Mode mode;

  //Game Associations
  private List<Player> players;
  private List<Tile> tiles;
  private List<Connection> connections;
  private Deck deck;
  private Die die;
  private Player currentPlayer;
  private WinTile winTile;
  private TileO tileO;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Game(int aCurrentConnectionPieces, Deck aDeck, Die aDie, TileO aTileO)
  {
    currentConnectionPieces = aCurrentConnectionPieces;
    players = new ArrayList<Player>();
    tiles = new ArrayList<Tile>();
    connections = new ArrayList<Connection>();
    if (aDeck == null || aDeck.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDeck");
    }
    deck = aDeck;
    if (aDie == null || aDie.getGame() != null)
    {
      throw new RuntimeException("Unable to create Game due to aDie");
    }
    die = aDie;
    boolean didAddTileO = setTileO(aTileO);
    if (!didAddTileO)
    {
      throw new RuntimeException("Unable to create game due to tileO");
    }
    setMode(Mode.DESIGN);
  }

  public Game(int aCurrentConnectionPieces, TileO aTileO)
  {
    currentConnectionPieces = aCurrentConnectionPieces;
    players = new ArrayList<Player>();
    tiles = new ArrayList<Tile>();
    connections = new ArrayList<Connection>();
    deck = new Deck(this);
    die = new Die(this);
    boolean didAddTileO = setTileO(aTileO);
    if (!didAddTileO)
    {
      throw new RuntimeException("Unable to create game due to tileO");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCurrentConnectionPieces(int aCurrentConnectionPieces)
  {
    boolean wasSet = false;
    currentConnectionPieces = aCurrentConnectionPieces;
    wasSet = true;
    return wasSet;
  }

  public int getCurrentConnectionPieces()
  {
    return currentConnectionPieces;
  }

  public String getModeFullName()
  {
    String answer = mode.toString();
    return answer;
  }

  public Mode getMode()
  {
    return mode;
  }

  public boolean setMode(Mode aMode)
  {
    mode = aMode;
    return true;
  }

  public Player getPlayer(int index)
  {
    Player aPlayer = players.get(index);
    return aPlayer;
  }

  public List<Player> getPlayers()
  {
    List<Player> newPlayers = Collections.unmodifiableList(players);
    return newPlayers;
  }

  public int numberOfPlayers()
  {
    int number = players.size();
    return number;
  }

  public boolean hasPlayers()
  {
    boolean has = players.size() > 0;
    return has;
  }

  public int indexOfPlayer(Player aPlayer)
  {
    int index = players.indexOf(aPlayer);
    return index;
  }

  public Tile getTile(int index)
  {
    Tile aTile = tiles.get(index);
    return aTile;
  }

  public List<Tile> getTiles()
  {
    List<Tile> newTiles = Collections.unmodifiableList(tiles);
    return newTiles;
  }

  public int numberOfTiles()
  {
    int number = tiles.size();
    return number;
  }

  public boolean hasTiles()
  {
    boolean has = tiles.size() > 0;
    return has;
  }

  public int indexOfTile(Tile aTile)
  {
    int index = tiles.indexOf(aTile);
    return index;
  }

  public Connection getConnection(int index)
  {
    Connection aConnection = connections.get(index);
    return aConnection;
  }

  public List<Connection> getConnections()
  {
    List<Connection> newConnections = Collections.unmodifiableList(connections);
    return newConnections;
  }

  public int numberOfConnections()
  {
    int number = connections.size();
    return number;
  }

  public boolean hasConnections()
  {
    boolean has = connections.size() > 0;
    return has;
  }

  public int indexOfConnection(Connection aConnection)
  {
    int index = connections.indexOf(aConnection);
    return index;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public Die getDie()
  {
    return die;
  }

  public Player getCurrentPlayer()
  {
    return currentPlayer;
  }

  public boolean hasCurrentPlayer()
  {
    boolean has = currentPlayer != null;
    return has;
  }

  public WinTile getWinTile()
  {
    return winTile;
  }

  public boolean hasWinTile()
  {
    boolean has = winTile != null;
    return has;
  }

  public TileO getTileO()
  {
    return tileO;
  }

  public boolean isNumberOfPlayersValid()
  {
    boolean isValid = numberOfPlayers() >= minimumNumberOfPlayers() && numberOfPlayers() <= maximumNumberOfPlayers();
    return isValid;
  }

  public static int minimumNumberOfPlayers()
  {
    return 2;
  }

  public static int maximumNumberOfPlayers()
  {
    return 4;
  }

  public Player addPlayer(int aNumber)
  {
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return null;
    }
    else
    {
      return new Player(aNumber, this);
    }
  }

  public boolean addPlayer(Player aPlayer)
  {
    boolean wasAdded = false;
    if (players.contains(aPlayer)) { return false; }
    if (numberOfPlayers() >= maximumNumberOfPlayers())
    {
      return wasAdded;
    }

    Game existingGame = aPlayer.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);

    if (isNewGame && existingGame.numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasAdded;
    }

    if (isNewGame)
    {
      aPlayer.setGame(this);
    }
    else
    {
      players.add(aPlayer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePlayer(Player aPlayer)
  {
    boolean wasRemoved = false;
    //Unable to remove aPlayer, as it must always have a game
    if (this.equals(aPlayer.getGame()))
    {
      return wasRemoved;
    }

    //game already at minimum (2)
    if (numberOfPlayers() <= minimumNumberOfPlayers())
    {
      return wasRemoved;
    }
    players.remove(aPlayer);
    wasRemoved = true;
    return wasRemoved;
  }

  public boolean addPlayerAt(Player aPlayer, int index)
  {  
    boolean wasAdded = false;
    if(addPlayer(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePlayerAt(Player aPlayer, int index)
  {
    boolean wasAdded = false;
    if(players.contains(aPlayer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPlayers()) { index = numberOfPlayers() - 1; }
      players.remove(aPlayer);
      players.add(index, aPlayer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPlayerAt(aPlayer, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTiles()
  {
    return 0;
  }

  /*public Tile addTile(int aX, int aY)
  {
    return new Tile(aX, aY, this);
  }*/

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    Game existingGame = aTile.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aTile.setGame(this);
    }
    else
    {
      tiles.add(aTile);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    //Unable to remove aTile, as it must always have a game
    if (!this.equals(aTile.getGame()))
    {
      tiles.remove(aTile);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTileAt(Tile aTile, int index)
  {  
    boolean wasAdded = false;
    if(addTile(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTileAt(Tile aTile, int index)
  {
    boolean wasAdded = false;
    if(tiles.contains(aTile))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTiles()) { index = numberOfTiles() - 1; }
      tiles.remove(aTile);
      tiles.add(index, aTile);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTileAt(aTile, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfConnections()
  {
    return 0;
  }

  public Connection addConnection()
  {
    return new Connection(this);
  }

  public boolean addConnection(Connection aConnection)
  {
    boolean wasAdded = false;
    if (connections.contains(aConnection)) { return false; }
    Game existingGame = aConnection.getGame();
    boolean isNewGame = existingGame != null && !this.equals(existingGame);
    if (isNewGame)
    {
      aConnection.setGame(this);
    }
    else
    {
      connections.add(aConnection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConnection(Connection aConnection)
  {
    boolean wasRemoved = false;
    //Unable to remove aConnection, as it must always have a game
    if (!this.equals(aConnection.getGame()))
    {
      connections.remove(aConnection);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addConnectionAt(Connection aConnection, int index)
  {  
    boolean wasAdded = false;
    if(addConnection(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConnectionAt(Connection aConnection, int index)
  {
    boolean wasAdded = false;
    if(connections.contains(aConnection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConnections()) { index = numberOfConnections() - 1; }
      connections.remove(aConnection);
      connections.add(index, aConnection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConnectionAt(aConnection, index);
    }
    return wasAdded;
  }

  public boolean setCurrentPlayer(Player aNewCurrentPlayer)
  {
    boolean wasSet = false;
    currentPlayer = aNewCurrentPlayer;
    wasSet = true;
    return wasSet;
  }

  public boolean setWinTile(WinTile aNewWinTile)
  {
    boolean wasSet = false;
    winTile = aNewWinTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setTileO(TileO aTileO)
  {
    boolean wasSet = false;
    if (aTileO == null)
    {
      return wasSet;
    }

    TileO existingTileO = tileO;
    tileO = aTileO;
    if (existingTileO != null && !existingTileO.equals(aTileO))
    {
      existingTileO.removeGame(this);
    }
    tileO.addGame(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (players.size() > 0)
    {
      Player aPlayer = players.get(players.size() - 1);
      aPlayer.delete();
      players.remove(aPlayer);
    }
    
      
    while (tiles.size() > 0)
    {
      Tile aTile = tiles.get(tiles.size() - 1);
      aTile.delete();
      tiles.remove(aTile);
    }
    
      
    while (connections.size() > 0)
    {
      Connection aConnection = connections.get(connections.size() - 1);
      aConnection.delete();
      connections.remove(aConnection);
    }
    
      
    Deck existingDeck = deck;
    deck = null;
    if (existingDeck != null)
    {
      existingDeck.delete();
    }
    Die existingDie = die;
    die = null;
    if (existingDie != null)
    {
      existingDie.delete();
    }
    currentPlayer = null;
    winTile = null;
    TileO placeholderTileO = tileO;
    this.tileO = null;
    placeholderTileO.removeGame(this);
  }

  public boolean hasTile(int x, int y){
      List<Tile> existTiles = this.getTiles();
      for (Tile tile: existTiles){
    	  if ((tile.getX() == x) && (tile.getY() == y)){
    		  return true;
    	  }
      }
      return false;
  }
  
  public Tile getTile(int x, int y){
      List<Tile> existTiles = this.getTiles();
      for (Tile tile: existTiles){
    	  if ((tile.getX() == x) && (tile.getY() == y))
                return tile;
      }
      return null;
  }
  
  public void determineNextPlayer()
  {
	 Player currentPlayer = this.getCurrentPlayer();
	 currentPlayer.loseTurns(numberOfPlayers());
	 
	 for(Tile t:getTiles()){
		 if(t instanceof ActionTile){
			 ((ActionTile)t).takeTurn();
		 }
	 }
	 
	 while(true){
		 for(Player p: getPlayers()){
			 p.takeTurn();
		 }
		 for(Player p: getPlayers()){
			 if(p.getPlayerStatus() == PlayerStatus.Active){
				 setCurrentPlayer(p);
				 return;
			 }
		 }
	 }
  }

  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "currentConnectionPieces" + ":" + getCurrentConnectionPieces()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "die = "+(getDie()!=null?Integer.toHexString(System.identityHashCode(getDie())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentPlayer = "+(getCurrentPlayer()!=null?Integer.toHexString(System.identityHashCode(getCurrentPlayer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "winTile = "+(getWinTile()!=null?Integer.toHexString(System.identityHashCode(getWinTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "tileO = "+(getTileO()!=null?Integer.toHexString(System.identityHashCode(getTileO())):"null")
     + outputString;
  }

public void selectnextCard() {
	Deck deck = getDeck();
	ActionCard currentCard = deck.getCurrentCard();
	int index = deck.indexOfCard(currentCard);
	if(index == (deck.numberOfCards()-1))
		deck.setCurrentCard(deck.getCard(0));
	else deck.setCurrentCard(deck.getCard(index+1));
	
}
}