package ca.mcgill.ecse223.tileo.model;


import java.io.Serializable;

import ca.mcgill.ecse223.tileo.model.Game.Mode;

// line 193 "../../../../../TileO (updated Feb10).ump"
public class SwapPositionActionCard extends ActionCard implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2168955965435212811L;

	public SwapPositionActionCard(String aInstructions, Deck aDeck){
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
		public void play(Player player){
			
		}

			  // line 206 "../../../../../TileO (updated Feb10).ump"
		public Mode getActionCardGameMode(){
		    return Mode.GAME_SWAPPOSITIONACTIONCARD;
		}
			  
		//------------------------
		// DEVELOPER CODE - PROVIDED AS-IS
		//------------------------
			  
		// line 199 ../../../../../TileO (updated Feb10).ump
		@Override
		public int type () 
		{
		    return 7;
		}

			  
		}
