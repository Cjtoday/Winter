package com.WinterStudios.Winter.States;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.WinterStudios.Winter.Main.GameScreen;
import com.WinterStudios.Winter.Manager.Keys;
import com.WinterStudios.Winter.Manager.StateManager;

public class IntroState extends GameState{


	private BufferedImage Intro;
	private final String intro_location = "/Images/intro.gif";

	//Fade_In Fade_Out 
	private int tick;
	private float Alpha_Lv;
	private int Target;

	//Constructor
	public IntroState(StateManager SM) {
		super(SM);
		tick = 0;
		Alpha_Lv = 1f;
		Target = 300;
	}//end Constructor

	//Init
	public void init() {                          
		try {
			Intro = ImageIO.read(getClass().getResourceAsStream(intro_location));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}//end Init                                             


	//Game_Update
	public void Game_Update(){  
		Handle_Input();

		if(tick < Target/2){
			Alpha_Lv -= .0065f;
			tick ++;
		}
		else if(tick >= Target/2  && tick < Target){
			Alpha_Lv += .0065f;
			tick++;
		}
		else if(tick >= Target){
			SM.loadState(StateManager.MAINMENU);
		}
		else
			System.err.println("Error: Game_Update method - IntroState");
	}//end Game_Update


	//Game_Render
	public void Game_Render(Graphics2D g2d){
		g2d.drawImage(Intro, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT, null);
		g2d.setColor(new Color(0, 0, 0, Alpha_Lv));
		g2d.fillRect(0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);	
	}//end Game_Render


	//Handle_Input
	public void Handle_Input(){
		if(Keys.Is_Pressed(Keys.ENTER))
		{
			SM.loadState(StateManager.MAINMENU);
		}
	}//end Handle_Input


}
