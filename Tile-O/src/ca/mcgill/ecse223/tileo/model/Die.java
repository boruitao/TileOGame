/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;
import java.util.Random;

// line 241 "../../../../../TileO (updated Feb10).ump"
public class Die implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = -2819346463911992740L;
//Die Associations
  private Game game;
  private int latest_roll = 0;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Die(Game aGame)
  {
    if (aGame == null || aGame.getDie() != null)
    {
      throw new RuntimeException("Unable to create Die due to aGame");
    }
    game = aGame;
  }

  public Die(int aCurrentConnectionPiecesForGame, Deck aDeckForGame, TileO aTileOForGame)
  {
    game = new Game(aCurrentConnectionPiecesForGame, aDeckForGame, this, aTileOForGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Game getGame()
  {
    return game;
  }

  public void delete()
  {
    Game existingGame = game;
    game = null;
    if (existingGame != null)
    {
      existingGame.delete();
    }
  }

  // line 242 "../../../../../TileO (updated Feb10).ump"
   public int roll(){
    // IMPLEMENTED BY BIJAN
	  Random rand = new Random();
	  latest_roll = rand.nextInt(6)+1;
	  return latest_roll;
  }
  
  public int getLatest_roll(){
	  return latest_roll;
  }
}