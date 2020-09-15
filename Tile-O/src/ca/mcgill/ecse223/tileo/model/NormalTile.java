/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.controller.InvalidInputException;
import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 106 "../../../../../TileO (updated Feb10).ump"
public class NormalTile extends Tile implements Serializable
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
	private static final long serialVersionUID = -4242199115754805670L;

public NormalTile(int aX, int aY, Game aGame)
  {
    super(aX, aY, aGame);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 109 "../../../../../TileO (updated Feb10).ump"
   public void land(){
    Game currentGame = this.getGame();
	Player currentPlayer = currentGame.getCurrentPlayer();
	currentPlayer.setCurrentTile(this);
    this.setHasBeenVisited(true);
    currentGame.setMode(Mode.GAME);
    currentGame.determineNextPlayer();
  }
   
   public void landPlayer(Player player) throws InvalidInputException{
	    Game currentGame = this.getGame();
	    boolean playerInGame = false;
	    for(Player p : currentGame.getPlayers()){
	    	if(p == player)
	    		playerInGame = true;
	    }
	    if(playerInGame == false){
	    	throw new InvalidInputException("The player is not in the game.");
	    }
	    	
		player.setCurrentTile(this);
	    this.setHasBeenVisited(true);
 }

}