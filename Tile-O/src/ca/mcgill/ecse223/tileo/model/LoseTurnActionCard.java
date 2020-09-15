/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 228 "../../../../../TileO (updated Feb10).ump"
public class LoseTurnActionCard extends ActionCard implements Serializable
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
	private static final long serialVersionUID = 318619482022490252L;

public LoseTurnActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }
  
  public void play(){ //Added by Borui
	  Game game = TileOApplication.getCurrentGame();
	  Player currentPlayer = game.getCurrentPlayer();
	  currentPlayer.loseTurns(1);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 237 "../../../../../TileO (updated Feb10).ump"
   public Mode getActionCardGameMode(){
    return Mode.GAME_LOSETURNACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 230 ../../../../../TileO (updated Feb10).ump
  @Override
public int type () 
  {
    return 4;
  }

  
}