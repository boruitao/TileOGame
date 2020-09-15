package ca.mcgill.ecse223.tileo.ui;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import ca.mcgill.ecse223.tileo.application.*;
import ca.mcgill.ecse223.tileo.controller.*;

/* CONTRIBUTOR: Hieu Chau Nguyen
 * This is the start page. Instead of just starting straight from the design mode 
 * or from the previous mode, we want to give the player a choice instead.
 */
public class MainMenuPage extends TileOGamePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel page;
	
	private JLabel Title;
	private TileOGamePage this_page = this;
	private JPanel bottom;
		private JLabel DesignMode;
		private JLabel PlayMode;
		private JButton NewDesign;
		private JPanel PlayersNumber;
			private JLabel PlayersNumber_Text;
			private JTextField Enter_Number;
		private JButton LoadDesign;
		private JButton NewGame;
		private JButton LoadGame;
	
	private PlayController pc;
	private DesignController dc;
	private MainMenuResources resource;

	@Override
	public void refresh() {
		//No need to refresh in title page
	}

	@Override
	public void initialize() {
		
		//Window settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700,700);
		//end Window settings
		
		//Features setting.
		resource = new MainMenuResources();
		
		//Create a padding around the screen
		pc = new PlayController();
		dc = new DesignController();
		page = new JPanel();
		page.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
		page.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		add(page);
		
		//Big Title on top
		c.gridx=0;c.gridy=0;
		c.weightx=1.0;c.weighty=5.0;
		c.gridwidth=1;
		c.fill = GridBagConstraints.BOTH;
		
		Title = new JLabel(resource.Title,SwingConstants.CENTER);
		Title.setFont(resource.TitleFont);
		Title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		page.add(Title,c);
		
		
		//Bottom Part
		c.gridx=0;c.gridy=1;
		c.weightx=1.0;c.weighty=1.0;
		c.gridwidth=1;
		bottom = new JPanel();
		bottom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		bottom.setLayout(new GridBagLayout());
		page.add(bottom,c);
		
		//Design Mode Text
		c.fill = GridBagConstraints.NONE;
		c.gridwidth=2;
		c.gridx=0;c.gridy=0;
		c.weightx=1.0;c.weighty=1.5;
		DesignMode = new JLabel(resource.DesignMode);
		DesignMode.setFont(resource.ModeFont);
		bottom.add(DesignMode,c);
		
		//Number of Player's : Text
		c.gridwidth=2;
		c.gridx=0;c.gridy=1;
		c.weightx=0.0;c.weighty=0.5;
		c.fill = GridBagConstraints.BOTH;
		
		PlayersNumber = new JPanel();
		bottom.add(PlayersNumber,c);
		PlayersNumber_Text=new JLabel(resource.NumberOfPlayer);
		PlayersNumber_Text.setFont(resource.ButtonFont);
		PlayersNumber.add(PlayersNumber_Text);
		Enter_Number=new JTextField(resource.PlayerTextField);
		Enter_Number.setEditable(true);
		Enter_Number.setFont(resource.ButtonFont);
		PlayersNumber.add(Enter_Number);
		
		c.fill = GridBagConstraints.NONE;
				
		//New Design Button
		c.gridwidth=1;
		c.gridx=0;c.gridy=2;
		c.weightx=1.0;c.weighty=1.5;
		NewDesign = new JButton(resource.NewDesign);
		NewDesign.setFont(resource.ButtonFont);
		NewDesign.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					dc.createDesign(Enter_Number.getText());
				}catch(InvalidInputException e){
					JOptionPane.showMessageDialog(this_page, e.getMessage());
				};
			}
			
		});
		bottom.add(NewDesign,c);
		
		//Load Design Button
		c.gridx=1;c.gridy=2;
		c.weightx=1.0;c.weighty=1.5;
		LoadDesign = new JButton(resource.LoadDesign);
		LoadDesign.setFont(resource.ButtonFont);;
		LoadDesign.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dc.loadDesign(this_page);
			}
			
		});
		bottom.add(LoadDesign,c);
		
		//Play Mode Text
		c.gridwidth=2;
		c.gridx=0;c.gridy=3;
		c.weightx=1.0;c.weighty=1.5;
		PlayMode = new JLabel(resource.PlayMode);
		PlayMode.setFont(resource.ModeFont);
		bottom.add(PlayMode,c);
		
		//New Button
		c.gridwidth=1;
		c.gridx=0;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		NewGame = new JButton(resource.NewGame);
		NewGame.setFont(resource.ButtonFont);
		NewGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pc.NewGame(this_page);
			}
			
		});
		bottom.add(NewGame,c);
		
		
		//Load Game Button
		c.gridx=1;c.gridy=4;
		c.weightx=1.0;c.weighty=1.5;
		LoadGame = new JButton(resource.LoadGame);
		LoadGame.setFont(resource.ButtonFont);
		LoadGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				pc.LoadGame(this_page);
			}
			
		});
		bottom.add(LoadGame,c);
		
		
		//Finalize
		setVisible(true);
		
	}


}
