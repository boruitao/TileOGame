/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 88 "../../../../../TileO (updated Feb10).ump"
public class LoseRandomTurnsActionCard extends ActionCard implements Serializable
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
	private static final long serialVersionUID = -1124485359872490903L;

public LoseRandomTurnsActionCard(String aInstructions, Deck aDeck)
  {
    super(aInstructions, aDeck);
  }

  public Mode getActionCardGameMode(){
	    return Mode.GAME_LOSERANDOMTURNSCARD;
	  }
	  
	  //------------------------
	  // DEVELOPER CODE - PROVIDED AS-IS
	  //------------------------
	  
	  // line 199 ../../../../../TileO (updated Feb10).ump
	  @Override
	public int type () 
	  {
	    return 11;
	  }
  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}