package ca.mcgill.ecse223.tileo.model;

import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

public class TurnIntoActionTile extends ActionCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7427318401439301981L;

	public TurnIntoActionTile(String aInstructions, Deck aDeck) {
		super(aInstructions, aDeck);
	}

	@Override
	public Mode getActionCardGameMode() {
		return Mode.GAME_TURNINTOACTIONTILE;
	}

	@Override
	public int type() {
		// TODO Auto-generated method stub
		return 9;
	}

}