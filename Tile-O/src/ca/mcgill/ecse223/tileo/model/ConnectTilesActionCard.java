/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 167 "../../../../../TileO (updated Feb10).ump"
public class ConnectTilesActionCard extends ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = 7940206554459043999L;

public ConnectTilesActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 170 "../../../../../TileO (updated Feb10).ump"
   public void play(Tile tile1, Tile tile2) throws InvalidInputException{
    Game game = TileOApplication.getCurrentGame();
	  try {
			Connection connection = new Connection(game);
			connection.addTile(tile1);
			connection.addTile(tile2);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
  }

  // line 189 "../../../../../TileO (updated Feb10).ump"
   public Mode getActionCardGameMode(){
    return Mode.GAME_CONNECTTILESACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 182 ../../../../../TileO (updated Feb10).ump
  @Override
	public int type () 
  {
    return 1;
  }

  
}