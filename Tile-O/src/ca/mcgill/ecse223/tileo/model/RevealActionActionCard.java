package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;
import java.util.List;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

public class RevealActionActionCard extends ActionCard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 555627727337114580L;

	public RevealActionActionCard(String aInstructions, Deck aDeck) {
		super(aInstructions, aDeck);
	}
	
//------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------


  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

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
	return 10;
	}

	public Mode getActionCardGameMode()
	{
  		return Mode.GAME_REVEALACTIONACTIONCARD; 
	}

	
}