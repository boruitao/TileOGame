package ca.mcgill.ecse223.tileo.controller;

import java.util.ArrayList;
import java.util.List;


import ca.mcgill.ecse223.tileo.application.TileOApplication;
import ca.mcgill.ecse223.tileo.model.*;
import ca.mcgill.ecse223.tileo.ui.*;

public class DesignController {
	int standardDistance = 1;
	//not sure where we define the distance, but we need one indeed//
	String instruction1,
	instruction2, 
	instruction3, 
	instruction4, 
	instruction5,
	instruction9;
	private List<Tile> neighbors = new ArrayList<Tile>();// ADDED BY BIJAN, USED IN GETNEIGHBORS() METHOD

	//Tile Associations
	private List<Connection> connections;
	
	
	public DesignController() {
	}
	
	//Wenhao Geng parts start//
	public void createDesign(String number_input) throws InvalidInputException{
		try{
			int number = Integer.parseInt(number_input);
			if (number < 2 || number > 4) {
				throw new InvalidInputException("Invalid number of players (Only 2 to 4 players allowed)"); 
			}
			TileO tileO = new TileO();
			Game game =  new Game(32, tileO);
			game.setMode(Game.Mode.DESIGN);
			Player.Color pc[] = {Player.Color.RED, Player.Color.BLUE, Player.Color.GREEN, Player.Color.YELLOW};
			for(int i = 0; i < number; i++){
				game.addPlayer(i);
				game.getPlayer(i).setColor(pc[i]);
			}
			TileOApplication.setCurrentGame(game);
			TileOApplication.DesignPage();
			
		}catch(NumberFormatException e){
			throw new InvalidInputException("Cannot read the number of Players");
		}catch(RuntimeException e){
			e.printStackTrace();
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	// renamed placeAtile() >> placeTile()
	  public void placeNormalTile (int x , int y)  throws InvalidInputException{
		  Game theGame = TileOApplication.getCurrentGame();
		  removeTile(x,y);
		  new NormalTile(x,y,theGame);
	  }
	  
	  public void removeTile(int x, int y) throws InvalidInputException{
		  Game theGame = TileOApplication.getCurrentGame();
			try {
				List<Tile> tilelist = theGame.getTiles();
				boolean tileFound = false;
				int tileFoundIndex = 0;
				for(Tile t: tilelist){
					if(t.getX() == x && t.getY() == y){
						tileFound = true;
						tileFoundIndex=tilelist.indexOf(t);
					}
				}
				if(tileFound)
					theGame.getTile(tileFoundIndex).delete();
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		  
	  }


	public void putConnection(int tile1x, int tile1y, int tile2x, int tile2y) throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		//how to set mode???//
		//check if the tiles are adjacent, if not, directly end the method//
		if(Math.abs(tile1y - tile2y) == standardDistance && tile1x == tile2x);
		else if(Math.abs(tile1x - tile2x) == standardDistance && tile1y == tile2y);
		else{
			throw new InvalidInputException("Connection can not be created between non-adjacent tiles"); 
		}
		try {
			int index1 =0, index2 =0;
			boolean foundTile1 = false;
			boolean foundTile2 = false;
			List<Tile> tiles = game.getTiles();
			for(Tile t: tiles){
				if(t.getX() == tile1x && t.getY() == tile1y){
					foundTile1 = true;
					index1 = game.indexOfTile(t);
				}
				if(t.getX() == tile2x && t.getY() == tile2y){
					foundTile2 = true;
					index2 = game.indexOfTile(t);
				}
			}
			if(foundTile1 && foundTile2){
				for(Connection c:game.getConnections()){
					if(c.getTiles().contains(tiles.get(index1)) && c.getTiles().contains(tiles.get(index2))){
						throw new InvalidInputException("Connection already exist.");
					}
				}
				Connection connection = new Connection(game);
				connection.addTile(tiles.get(index1));
				connection.addTile(tiles.get(index2));
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
			
	}

	public void deleteConnection(int x1, int y1, int x2, int y2){
		Game theGame = TileOApplication.getCurrentGame();
		List<Connection> clist = theGame.getConnections();
		boolean tileFound1 = false;
		boolean tileFound2 = false;
		for(Connection c: clist){
			if((c.getTile(0).getX()==x1 && c.getTile(0).getY()==y1)||(c.getTile(0).getX()==x2 && c.getTile(0).getY()==y2)) tileFound1 = true;
			if((c.getTile(1).getX()==x1 && c.getTile(1).getY()==y1)||(c.getTile(1).getX()==x2 && c.getTile(1).getY()==y2)) tileFound2 = true;
			if(tileFound1 && tileFound2){
				c.delete();
				break;
			}
			else{
				tileFound1 = false; tileFound2 = false;
			}
		}
	}
	
	//loseTurnActionCard//
	public void addLoseTurnActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			LoseTurnActionCard loseTurnActionCard = new LoseTurnActionCard(instruction5, deck);
			deck.addCard(loseTurnActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeLoseTurnActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof LoseTurnActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//connectTilesActionCard//
	public void addConnectTilesActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			ConnectTilesActionCard connectTilesActionCard = new ConnectTilesActionCard(instruction2, deck);
			deck.addCard(connectTilesActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeConnectTilesActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof ConnectTilesActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//removeConnectionActionCard//
	public void addRemoveConnectionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RemoveConnectionActionCard removeConnectionActionCard = new RemoveConnectionActionCard(instruction3, deck);
			deck.addCard(removeConnectionActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRemoveConnectionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RemoveConnectionActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//RollDieActionCard//
	public void addRollDieActionCard() throws InvalidInputException {
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RollDieActionCard rollDieActionCard = new RollDieActionCard(instruction1, deck);
			deck.addCard(rollDieActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRollDieActionCard() throws InvalidInputException {
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RollDieActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();;
				//deck.removeCard(deck.getCard(cardFoundIndex));
				
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	//TeleportActionCard//
	public void addTeleportActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			TeleportActionCard teleportActionCard = new TeleportActionCard(instruction4, deck);
			deck.addCard(teleportActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeTeleportActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof TeleportActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void addWinTileHintActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			WinTileHintActionCard winTileHintActionCard = new WinTileHintActionCard(instruction9, deck);
			deck.addCard(winTileHintActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeWinTileHintActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof WinTileHintActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	//Wenhao Geng parts end//
	
	
	public void addTeleportAllPlayersActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			TeleportAllPlayersActionCard teleportAllPlayersActionCard = new TeleportAllPlayersActionCard(instruction9, deck);
			deck.addCard(teleportAllPlayersActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeTeleportAllPlayersActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof TeleportAllPlayersActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void addSwapPositionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			SwapPositionActionCard swapPositionActionCard = new SwapPositionActionCard(instruction9, deck);
			deck.addCard(swapPositionActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeSwapPositionActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof SwapPositionActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void addRevealTileActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RevealTileActionCard revealTileActionCard = new RevealTileActionCard(instruction9, deck);
			deck.addCard(revealTileActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRevealTileActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RevealTileActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeRevealActionTileActionCard() throws InvalidInputException{
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof RevealActionActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}

	public void addRevealActionTileActionCard() throws InvalidInputException{
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			RevealActionActionCard revealActionTileActionCard = new RevealActionActionCard("", deck);
			deck.addCard(revealActionTileActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
		
	}
	
	public void addTurnIntoActionTileActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			TurnIntoActionTile turnIntoActionTile = new TurnIntoActionTile(instruction9, deck);
			deck.addCard(turnIntoActionTile);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeTurnIntoActionTileActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof TurnIntoActionTile){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void addLoseRandomTurnsActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			LoseRandomTurnsActionCard loseRandomTurnsActionCard = new LoseRandomTurnsActionCard(instruction9, deck);
			deck.addCard(loseRandomTurnsActionCard);
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void removeLoseRandomTurnsActionCard() throws InvalidInputException {
		String error = "";
		Game game = TileOApplication.getCurrentGame();
		Deck deck = game.getDeck();
		try {
			List<ActionCard> cards = deck.getCards();
			boolean cardFound = false;
			int cardFoundIndex = 0;
			for(ActionCard card : cards){
				if(card instanceof LoseRandomTurnsActionCard){
					cardFound = true;
					cardFoundIndex=cards.indexOf(card);
				}
			}
			if(cardFound)
				deck.getCard(cardFoundIndex).delete();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void setLocationHiddenTile(int x, int y) throws InvalidInputException{ // DESIGN 6: IMPLEMENTED BY BIJAN
		Game game = TileOApplication.getCurrentGame();

		//catches invalid input exception
		try {

			//first check if x,y associated with an existing Tile in game. If so, delete that tile.
			removeTile(x,y);
			//No Tile at x,y anymore

			//check if game already has a winTile
			boolean hasWinTile = game.hasWinTile();

			//condition where there is no winTile
			if(hasWinTile==false){
				WinTile updatedWinTile = new WinTile(x,y,game);
				game.setWinTile(updatedWinTile);
			}

			//condition where there is a winTile
			else{
				WinTile existingWinTile = game.getWinTile();
				existingWinTile.delete();
				//Tile updatedTile = new Tile(existingWinTile.getX(), existingWinTile.getY(), game);//BORUI NOT SURE WHAT THIS IS FOR
				WinTile updatedWinTile = new WinTile(x,y,game);
				game.setWinTile(updatedWinTile);
			}
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public boolean hasPlayer(int i) {
		Game currentGame = TileOApplication.getCurrentGame();
		return (currentGame.getPlayers().size() >= i);
	}

	public UITile[][] updateTiles(UITile uitile[][]) {
		Game currentGame = TileOApplication.getCurrentGame();
		List<Player> playerlist = currentGame.getPlayers();
		for(Player p: playerlist){
			if(p.hasStartingTile()){
				uitile[p.getStartingTile().getX()][p.getStartingTile().getY()].setCurrentPlayer(p.getNumber()+1);
			}
		}
		for(Tile tile: currentGame.getTiles()){
			if(tile instanceof NormalTile){
				uitile[tile.getX()][tile.getY()].setVisited(true);
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Normal);
			}
			else if(tile instanceof WinTile){
				uitile[tile.getX()][tile.getY()].setVisited(true);
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Win);
			}
			else if(tile instanceof ActionTile){
				uitile[tile.getX()][tile.getY()].setVisited(true);
				uitile[tile.getX()][tile.getY()].setType(DesignModeResources.Type.Action);
				uitile[tile.getX()][tile.getY()].setCooldown(((ActionTile)tile).getInactivityPeriod());
			}
		}
		return uitile;
	}

	public int[] updateCards(){
		Game currentGame = TileOApplication.getCurrentGame();
		int cardlist[] = new int[12];
		for(int i = 0; i <12; i++) cardlist[i]=0;
		for(ActionCard a: currentGame.getDeck().getCards()){
			cardlist[a.type()]++;
		}
		return cardlist;
	}
	
	public List<UIConnection> updateConnection() {
		List<UIConnection> uiconnect = new ArrayList<UIConnection>();
		Game currentGame = TileOApplication.getCurrentGame();
		for(Connection c: currentGame.getConnections()){
			if(c.getTile(0).getX() == c.getTile(1).getX()){
				uiconnect.add(new UIConnection(c.getTile(0).getX(), Math.min(c.getTile(0).getY(),c.getTile(1).getY()), false));
			}
			else if(c.getTile(0).getY() == c.getTile(1).getY()){
				uiconnect.add(new UIConnection(Math.min(c.getTile(0).getX(),c.getTile(1).getX()), c.getTile(0).getY(), true));
			}
		}
		return uiconnect;
	}	
		
	//Borui Tao
	public void setStartTile(int x, int y, int player) throws InvalidInputException {
		Game currentGame = TileOApplication.getCurrentGame();
		String error = "";
		if (currentGame.getPlayers().size() < player) {
			 error = error + "The current player does not exist in the current game";
		}
	 
		List<Tile> tilelist = currentGame.getTiles();
		boolean tileFound = false;
		int tileFoundIndex = 0;
		for(Tile t: tilelist){
			if(t.getX() == x && t.getY() == y){
				tileFound = true;
				tileFoundIndex=tilelist.indexOf(t);
			}
		}
		if(!tileFound){
			error = error + "The startTile does not exist in the current game";
		}
		
		if (error.length() > 0){
			 throw new InvalidInputException(error.trim());
		}
		 
		try{
			List<Player> playerlist = currentGame.getPlayers();
			for(Player p: playerlist){
				if(p.getStartingTile()==tilelist.get(tileFoundIndex)) p.setStartingTile(null);
			}
		    currentGame.getPlayer(player).setStartingTile(tilelist.get(tileFoundIndex));
		} catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public void SaveandExitGame(TileOGamePage parent){
		//parent is needed for filechooser.
		try{
			TileOApplication.saveDesign(TileOApplication.getCurrentGame(),parent); 
			TileOApplication.setCurrentGame(null);
			TileOApplication.MainMenu();
		}catch(RuntimeException e){
			
		}
	}

	public void loadDesign(TileOGamePage parent) {
		TileOApplication.setCurrentGame(TileOApplication.loadDesign(parent));
		if(TileOApplication.getCurrentGame() != null)TileOApplication.DesignPage();
	}
	
	//updated in deliverable 4//
	public void turnIntoActionTile(int x , int y) throws InvalidInputException{
    	String error = " ";
    	Game game= TileOApplication.getCurrentGame();
    	try {
			if (game.hasTile(x,y)){
				Tile aTile = game.getTile(x, y);
				if (aTile instanceof ActionTile){
					ActionTile actionTile = (ActionTile) aTile;
					int p = actionTile.getInactivityPeriod();
					if (p<5)
						actionTile.setInactivityPeriod(p+1);
					if (p==5)
						actionTile.setInactivityPeriod(1);
				}
				
				else{
					aTile.delete();
					ActionTile newActionTile = new ActionTile(x,y,game,1);
					game.addTile(newActionTile);
					
				}
			}
			
			else{
				ActionTile newActionTile = new ActionTile(x,y,game,1);
				game.addTile(newActionTile);
			}
			
		}
    	catch (RuntimeException e) {
			error = e.getMessage();
			throw new InvalidInputException(error);
		}
    
 }
	    	/*
	    	try {
	    	Game game= TileOApplication.getCurrentGame();
	    	
	    	if(game.addTile(newTile)){
	    		System.out.println("Sucess");
	    	}else{
	    	  throw new InvalidInputException("Unable to add action tile");	
	    	}
	    	} catch (RuntimeException e) {
				error = e.getMessage();
				throw new InvalidInputException(error);
				*/
}
	 
