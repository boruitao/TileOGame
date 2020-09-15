/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 183 "../../../../../TileO (updated Feb10).ump"
public class WinTileHintActionCard extends ActionCard implements Serializable
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
	private static final long serialVersionUID = -7742473634954380002L;

public WinTileHintActionCard(String aInstructions, Deck aDeck)
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

  // line 192 "../../../../../TileO (updated Feb10).ump"
   public Mode getActionCardGameMode(){
    return Mode.GAME_WINHINTACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 185 ../../../../../TileO (updated Feb10).ump
  @Override
	public int type () 
  {
    return 5;
  }

  
}