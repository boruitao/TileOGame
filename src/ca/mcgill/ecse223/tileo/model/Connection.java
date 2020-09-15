/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

// line 134 "../../../../../TileO (updated Feb10).ump"
public class Connection implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = -4706969448162468811L;
//Connection Associations
  private Game game;
  private List<Tile> tiles;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Connection(Game aGame)
  {
    boolean didAddGame = setGame(aGame);
    if (!didAddGame)
    {
      throw new RuntimeException("Unable to create connection due to game");
    }
    tiles = new ArrayList<Tile>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game getGame()
  {
    return game;
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

  public boolean setGame(Game aGame)
  {
    boolean wasSet = false;
    if (aGame == null)
    {
      return wasSet;
    }

    Game existingGame = game;
    game = aGame;
    if (existingGame != null && !existingGame.equals(aGame))
    {
      existingGame.removeConnection(this);
    }
    game.addConnection(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfTilesValid()
  {
    boolean isValid = numberOfTiles() >= minimumNumberOfTiles() && numberOfTiles() <= maximumNumberOfTiles();
    return isValid;
  }

  public static int requiredNumberOfTiles()
  {
    return 2;
  }

  public static int minimumNumberOfTiles()
  {
    return 2;
  }

  public static int maximumNumberOfTiles()
  {
    return 2;
  }

  public boolean addTile(Tile aTile)
  {
    boolean wasAdded = false;
    if (tiles.contains(aTile)) { return false; }
    if (numberOfTiles() >= maximumNumberOfTiles())
    {
      return wasAdded;
    }

    tiles.add(aTile);
    if (aTile.indexOfConnection(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aTile.addConnection(this);
      if (!wasAdded)
      {
        tiles.remove(aTile);
      }
    }
    return wasAdded;
  }

  public boolean removeTile(Tile aTile)
  {
    boolean wasRemoved = false;
    if (!tiles.contains(aTile))
    {
      return wasRemoved;
    }

    if (numberOfTiles() <= minimumNumberOfTiles())
    {
      return wasRemoved;
    }

    int oldIndex = tiles.indexOf(aTile);
    tiles.remove(oldIndex);
    if (aTile.indexOfConnection(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aTile.removeConnection(this);
      if (!wasRemoved)
      {
        tiles.add(oldIndex,aTile);
      }
    }
    return wasRemoved;
  }

  public void delete()
  {
    Game placeholderGame = game;
    this.game = null;
    placeholderGame.removeConnection(this);
    ArrayList<Tile> copyOfTiles = new ArrayList<Tile>(tiles);
    tiles.clear();
    for(Tile aTile : copyOfTiles)
    {
      aTile.removeConnection(this);
    }
  }

}