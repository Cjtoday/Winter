package com.WinterStudios.Winter.Manager;

import java.awt.Graphics2D;

import com.WinterStudios.Winter.States.GameState;
import com.WinterStudios.Winter.States.IntroState;
import com.WinterStudios.Winter.States.MainMenuState;
import com.WinterStudios.Winter.States.PlayState;

public class StateManager {
	
	private final int Num_Of_States = 4;

	public static final int INTRO = 0;
	public static final int MAINMENU = 1;
	public static final int PAUSED = 2;
	public static final int PLAY = 3;
	
	private int Current_State; 
	private int Previous_State;


	GameState[] Game_States;

/*-----------------------------------------------------------------------------------------------------------------------------------------------------*/	
	
	//Constructor
	public StateManager(){
		Game_States = new GameState[Num_Of_States];
	}//end Constructor


	//LoadState
	public void loadState(int x){
		Previous_State = Current_State;
		Current_State = x;
		unloadState(Previous_State);

		switch(x)	{
		case INTRO: 
			Game_States[x] = new IntroState(this);
			break;
		case MAINMENU:
			Game_States[x] = new MainMenuState(this);
			break;
		case PLAY:
			Game_States[x] = new PlayState(this);
		}
		Game_States[Current_State].init();
	}//end LoadState

		
	//UnloadState
	public void unloadState(int x){
		Game_States[x] = null;
	}//end UnloadState
	
	
	//Game_Update
	public void Game_Update(){
		Game_States[Current_State].Game_Update();
	}//end Game_Update
	
	
	//Game_Render
	public void Game_Render(Graphics2D G2D){//pass the graphics2d object used to draw components to the rendered image.
		Game_States[Current_State].Game_Render(G2D);
	}//end Game_Render

}
