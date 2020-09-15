/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 155 "../../../../../TileO (updated Feb10).ump"
public class RollDieActionCard extends ActionCard implements Serializable
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
	private static final long serialVersionUID = -6994583459555554289L;

public RollDieActionCard(String aInstructions, Deck aDeck)
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

  // line 163 "../../../../../TileO (updated Feb10).ump"
   public Mode getActionCardGameMode(){
    return Mode.GAME_ROLLDIEACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 157 ../../../../../TileO (updated Feb10).ump
  @Override
public int type () 
  {
    return 0;
  }

  
}