/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;
import java.io.Serializable;
import java.util.*;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 1 "../../../../../ActionTileSM.ump"
// line 88 "../../../../../TileO (updated Feb10).ump"
public class ActionTile extends Tile implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = 2215102297040268109L;
//ActionTile Attributes
  private int inactivityPeriod;
  private int turnsUntilActive;

  //ActionTile State Machines
  public enum ActionTileStatus { Active, Inactive }
  private ActionTileStatus actionTileStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionTile(int aX, int aY, Game aGame, int aInactivityPeriod)
  {
    super(aX, aY, aGame);
    inactivityPeriod = aInactivityPeriod;
    turnsUntilActive = 0;
    setActionTileStatus(ActionTileStatus.Active);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTurnsUntilActive(int aTurnsUntilActive)
  {
    boolean wasSet = false;
    turnsUntilActive = aTurnsUntilActive;
    wasSet = true;
    return wasSet;
  }

  public int getInactivityPeriod()
  {
    return inactivityPeriod;
  }

  public int getTurnsUntilActive()
  {
    return turnsUntilActive;
  }

  public String getActionTileStatusFullName()
  {
    String answer = actionTileStatus.toString();
    return answer;
  }

  public ActionTileStatus getActionTileStatus()
  {
    return actionTileStatus;
  }

  public boolean deactivate()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Active:
        if (getInactivityPeriod()>0)
        {
        // line 4 "../../../../../ActionTileSM.ump"
          setTurnsUntilActive(getInactivityPeriod() + 1 );
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean takeTurn()
  {
    boolean wasEventProcessed = false;
    
    ActionTileStatus aActionTileStatus = actionTileStatus;
    switch (aActionTileStatus)
    {
      case Inactive:
        if (getTurnsUntilActive()>1)
        {
        // line 9 "../../../../../ActionTileSM.ump"
          setTurnsUntilActive(getTurnsUntilActive() - 1);
          setActionTileStatus(ActionTileStatus.Inactive);
          wasEventProcessed = true;
          break;
        }
        if (getTurnsUntilActive()<=1)
        {
        // line 12 "../../../../../ActionTileSM.ump"
          setTurnsUntilActive(0);
          setActionTileStatus(ActionTileStatus.Active);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public void setActionTileStatus(ActionTileStatus aActionTileStatus)
  {
    actionTileStatus = aActionTileStatus;
  }

  public void delete()
  {
    super.delete();
  }

  // line 92 "../../../../../TileO (updated Feb10).ump"
   public void land(){
    Game currentGame = this.getGame();
	  Player currentPlayer = currentGame.getCurrentPlayer();
      currentPlayer.setCurrentTile(this);
      this.setHasBeenVisited(true);
      /*
      Deck deck = currentGame.getDeck();
      ActionCard currentCard = deck.getCurrentCard();
      currentCardMode = currentCard.getActionCardGameMode();
      currentGame.setMode(currentCardMode);
      */
      currentGame.setMode(Mode.GAME_DRAWCARD);
  }

   public void setInactivityPeriod(int anInactivityPeriod)
   {
      this.inactivityPeriod = anInactivityPeriod;
   }

  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "inactivityPeriod" + ":" + getInactivityPeriod()+ "," +
            "turnsUntilActive" + ":" + getTurnsUntilActive()+ "]"
     + outputString;
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 90 ../../../../../TileO (updated Feb10).ump
  private Mode currentCardMode ;

  
}