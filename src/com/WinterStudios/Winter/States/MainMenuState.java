package com.WinterStudios.Winter.States;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.WinterStudios.Winter.Main.GameScreen;
import com.WinterStudios.Winter.Manager.Keys;
import com.WinterStudios.Winter.Manager.StateManager;

public class MainMenuState extends GameState{

	private BufferedImage background;
	private final String background_location = "/Images/mmbackground.gif";
	
	private final Font selectedFont = new Font("Trajan Pro 3", Font.PLAIN, 18);
	private final Font unselectedFont = new Font("Trajan Pro 3", Font.PLAIN, 14);
	
	private int Menu_Y;

	private String[] Options = new String[]{
			"New Game",
			"Load Game",
			"Options",
			"Quit" 
	};

	private int selected;
/*--------------------------------------------------------------------------------------------------------------------------------------------------------*/	

	//Constructor
	public MainMenuState(StateManager SM){
		super(SM);
		 Menu_Y = 150;
	}//endConstructor


	//Init
	public void init(){
		selected = 0;
		
		try{
			background = ImageIO.read(getClass().getResourceAsStream(background_location));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}//end Init


	//Game_Update
	public void Game_Update(){
		Handle_Input();
	}//end Game_Update


	//Game_Render
	public void Game_Render(Graphics2D g2d) 
	{		
		Menu_Y = 150;
		
		g2d.drawImage(background, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, null);


		for(int i = 0; i < Options.length ; i++)
		{
			if(selected == i)
			{
				g2d.setFont(selectedFont); 
				g2d.setColor(Color.WHITE);
				g2d.drawString(Options[i], 10, Menu_Y += 20);
			}
			else
			{
				g2d.setFont(unselectedFont); 
				g2d.setColor(Color.GRAY);
				g2d.drawString(Options[i], 10, Menu_Y += 20);
			}
		}
	}//end Game_Render


	//Handle_Input
	public void Handle_Input(){
		if(Keys.Is_Pressed(Keys.UP) && selected >0)
		{
			selected--;
		}
		else if (Keys.Is_Pressed(Keys.DOWN)&& selected < Options.length -1)
		{
			selected++;
		}
		else if (Keys.Is_Pressed(Keys.ENTER))
		{
			switch(selected)
			{
			case 0:
				SM.loadState(SM.PLAY);
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				System.exit(0);
				break;
			}
		}
	}//end handle_Input

}
