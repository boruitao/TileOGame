/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;
import java.util.List;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 200 "../../../../../TileO (updated Feb10).ump"
public class TeleportAllPlayersActionCard extends ActionCard implements Serializable
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 6485812874962318107L;

//------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TeleportAllPlayersActionCard(String aInstructions, Deck aDeck)
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

  // line 203 "../../../../../TileO (updated Feb10).ump"
   public void play(List<Player> otherPlayers, List<Tile> tiles){
    //land the first three players with landPlayer(player)
 		for(int i=0; i<tiles.size()-1; i++){
 			NormalTile tile = (NormalTile) tiles.get(i);
 			try {
				tile.landPlayer(otherPlayers.get(i));
			} catch (InvalidInputException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
 		
 		//land the last player on the last tile using land method as done with TeleportActionCard
 		//behavior should be the same as TeleportActionCard to teleport currentPlayer
 		NormalTile lastTile = (NormalTile) tiles.get(tiles.size()-1);
 		lastTile.land();
  }

   @Override
	public int type() {
	return 6;
	}

	public Mode getActionCardGameMode()
	{
  		return Mode.GAME_TELEALLPLAYERCARD; 
	}

}