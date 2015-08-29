package com.WinterStudios.Winter.States;

import java.awt.Graphics2D;
import com.WinterStudios.Winter.Manager.StateManager;

public abstract class GameState {
	
	protected StateManager SM;
	
	//consturctor
	public GameState(StateManager SM) {
		this.SM = SM;
	}//end constructor
	
	public abstract void init();//initilize the the state.
	public abstract void Game_Update();//update the game logic based off of which state is active.
	public abstract void Game_Render(Graphics2D g);//draw graphics to the render image based off of which state is active. this method is called by the StateManager
	public abstract void Handle_Input();//use to deal with input. the listenr is included in the game screen oject
	
}
