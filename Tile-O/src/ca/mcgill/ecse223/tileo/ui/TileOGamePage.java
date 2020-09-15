package ca.mcgill.ecse223.tileo.ui;

import java.awt.*;

import javax.swing.*;

public abstract class TileOGamePage extends JFrame {
	
	public TileOGamePage(){
		initialize();
		refresh();
	}

	public abstract void refresh();

	public abstract void initialize();
	
}
