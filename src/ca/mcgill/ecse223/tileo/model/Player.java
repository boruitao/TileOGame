/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 1 "../../../../../PlayerSM.ump"
// line 25 "../../../../../TileO (updated Feb10).ump"
public class Player implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = -8865001940681263583L;

private static Map<Integer, Player> playersByNumber = new HashMap<Integer, Player>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private int number;
  private int turnsUntilActive;

  //Player State Machines
  public enum PlayerStatus { Active, Inactive }
  private PlayerStatus playerStatus;
  public enum Color { RED, BLUE, GREEN, YELLOW }
  private Color color;

  //Player Associations
  private Tile startingTile;
  private Tile currentTile;
  private Game game;
  
	// ADDITIONAL ATTRIBUTES, ADDED BY BIJAN
	private List<Tile> finalTiles = new ArrayList<Tile>();

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(int aNumber, Game aGame)
  {
    turnsUntilActive = 0;
    if (!setNumber(aNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate number");
    }
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create player due to game");
    }
    setPlayerStatus(PlayerStatus.Active);
    setColor(Color.RED);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    Integer anOldNumber = getNumber();
    if (hasWithNumber(aNumber)) {
      return wasSet;
    }
    number = aNumber;
    wasSet = true;
    if (anOldNumber != null) {
      playersByNumber.remove(anOldNumber);
    }
    playersByNumber.put(aNumber, this);
    return wasSet;
  }

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getNumber()
  {
    return number;
  }

  public static Player getWithNumber(int aNumber)
  {
    return playersByNumber.get(aNumber);
  }

  public static boolean hasWithNumber(int aNumber)
  {
    return getWithNumber(aNumber) != null;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getPlayerStatusFullName()
  {
    String answer = playerStatus.toString();
    return answer;
  }

  public String getColorFullName()
  {
    String answer = color.toString();
    return answer;
  }

  public PlayerStatus getPlayerStatus()
  {
    return playerStatus;
  }

  public Color getColor()
  {
    return color;
  }

  public boolean loseTurns(int n)
  {
    boolean wasEventProcessed = false;
    
    PlayerStatus aPlayerStatus = playerStatus;
    switch (aPlayerStatus)
    {
      case Active:
        if (n>0)
        {
        // line 4 "../../../../../PlayerSM.ump"
          setTurnsUntilActive(getTurnsUntilActive() + n);
          setPlayerStatus(PlayerStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        break;
      case Inactive:
        if (n>0)
        {
        // line 15 "../../../../../PlayerSM.ump"
          setTurnsUntilActive(getTurnsUntilActive() + n);
          setPlayerStatus(PlayerStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    PlayerStatus aPlayerStatus = playerStatus;
    switch (aPlayerStatus)
    {
      case Inactive:
        if (getTurnsUntilActive()>1)
        {
        // line 9 "../../../../../PlayerSM.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setPlayerStatus(PlayerStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 12 "../../../../../PlayerSM.ump"
          setTurnsUntilActive(0);
          setPlayerStatus(PlayerStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setPlayerStatus(PlayerStatus aPlayerStatus)
  {
    playerStatus = aPlayerStatus;
  }

  public boolean setColor(Color aColor)
  {
    color = aColor;
    return true;
  }

  public Tile getStartingTile()
  {
    return startingTile;
  }

  public boolean hasStartingTile()
  {
    boolean has = startingTile != null;
    return has;
  }

  public Tile getCurrentTile()
  {
    return currentTile;
  }

  public boolean hasCurrentTile()
  {
    boolean has = currentTile != null;
    return has;
  }

  public Game getGame()
  {
    return game;
  }

  public boolean setStartingTile(Tile aNewStartingTile)
  {
    boolean wasSet = false;
    startingTile = aNewStartingTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setCurrentTile(Tile aNewCurrentTile)
  {
    boolean wasSet = false;
    currentTile = aNewCurrentTile;
    wasSet = true;
    return wasSet;
  }

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    //Must provide game to player
    if (aGame == null)
    {
      return wasSet;
    }

    //game already at maximum (4)
    if (aGame.numberOfPlayers() >= Game.maximumNumberOfPlayers())
    {
      return wasSet;
    }
    
    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      boolean didRemove = existingGame.removePlayer(this);
      if (!didRemove)
      {
        game = existingGame;
        return wasSet;
      }
    }
    game.addPlayer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    playersByNumber.remove(getNumber());
    startingTile = null;
    currentTile = null;
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removePlayer(this);
  }


public void swappedPlayerLand(Tile tile){
	  
	  Game currentGame=this.getGame();
	  this.setCurrentTile(tile);
      tile.setHasBeenVisited(true);
      
  }
  
  public void removeTile(){
	  this.currentTile = null;
  }
  /**
   * Bijan Sadeghi
   * return list of all possible Tiles a player can move to with a given die
   * roll
   */
  // line 36 "../../../../../TileO (updated Feb10).ump"
   public List<Tile> getPossibleMoves(Tile currentTile, Tile previousTile, int rollNumber){
    // BASE CASE (ONE TILE MOVE)
		if (rollNumber == 1) {
			for (Tile neighbor : currentTile.getNeighbors()) {
				if (neighbor != previousTile && !finalTiles.contains(neighbor))
					finalTiles.add(neighbor);
			}
		}

		// RECURSIVE STEP
		else {
			for (Tile neighbor : currentTile.getNeighbors()) {
				if (neighbor != previousTile)
					getPossibleMoves(neighbor, currentTile, rollNumber - 1);
			}
		}

		return finalTiles;
  }


  /**
   * Bijan Sadeghi
   */
  // line 57 "../../../../../TileO (updated Feb10).ump"
   public void resetFinalTiles(){
    finalTiles.clear();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "startingTile = "+(getStartingTile()!=null?Integer.toHexString(System.identityHashCode(getStartingTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "currentTile = "+(getCurrentTile()!=null?Integer.toHexString(System.identityHashCode(getCurrentTile())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "game = "+(getGame()!=null?Integer.toHexString(System.identityHashCode(getGame())):"null")
     + outputString;
  }
}