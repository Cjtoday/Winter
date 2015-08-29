package com.WinterStudios.Winter.Main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Driver {
	
	public static void main(String[] Args){
	
	JFrame Game_Frame = new JFrame();
	GameScreen Game_Screen = new GameScreen();
	
	
	Game_Frame.add(Game_Screen);
	Game_Frame.pack();
	Game_Frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	Game_Frame.setVisible(true);
	//Game_Frame.setResizable(false); //creates weird white border on right and bottom of frame
	
	}
}
