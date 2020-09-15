/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 193 "../../../../../TileO (updated Feb10).ump"
public class RemoveConnectionActionCard extends ActionCard implements Serializable
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
	private static final long serialVersionUID = -697997939564923990L;

public RemoveConnectionActionCard(String aInstructions, Deck aDeck)
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

  // line 195 "../../../../../TileO (updated Feb10).ump"
   public void play(Connection connection){
    //Added by Bijan
	  connection.delete();
  }

  // line 206 "../../../../../TileO (updated Feb10).ump"
   public Mode getActionCardGameMode(){
    return Mode.GAME_REMOVECONNECTIONACTIONCARD;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 199 ../../../../../TileO (updated Feb10).ump
  @Override
public int type () 
  {
    return 2;
  }

  
}