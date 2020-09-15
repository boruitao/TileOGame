/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.22.0.5146 modeling language!*/

package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 146 "../../../../../TileO (updated Feb10).ump"
public abstract class ActionCard implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  /**
	 * 
	 */
	private static final long serialVersionUID = 3927345256179087829L;

//ActionCard Attributes
  private String instructions;

  //ActionCard Associations
  private Deck deck;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ActionCard(String aInstructions, Deck aDeck)
  {
    instructions = aInstructions;
    boolean didAddDeck = setDeck(aDeck);
    if (!didAddDeck)
    {
      throw new RuntimeException("Unable to create card due to deck");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getInstructions()
  {
    return instructions;
  }

  public Deck getDeck()
  {
    return deck;
  }

  public boolean setDeck(Deck aDeck)
  {
    boolean wasSet = false;
    //Must provide deck to card
    if (aDeck == null)
    {
      return wasSet;
    }

    //deck already at maximum (32)
    if (aDeck.numberOfCards() >= Deck.maximumNumberOfCards())
    {
      return wasSet;
    }
    
    Deck existingDeck = deck;
    deck = aDeck;
    if (existingDeck != null && !existingDeck.equals(aDeck))
    {
      boolean didRemove = existingDeck.removeCard(this);
      if (!didRemove)
      {
        deck = existingDeck;
        return wasSet;
      }
    }
    deck.addCard(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Deck placeholderDeck = deck;
    this.deck = null;
    placeholderDeck.removeCard(this);
  }

   public abstract Mode getActionCardGameMode();
   public abstract int type();

  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "instructions" + ":" + getInstructions()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deck = "+(getDeck()!=null?Integer.toHexString(System.identityHashCode(getDeck())):"null")
     + outputString;
  }
}