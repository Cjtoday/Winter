package com.WinterStudios.Winter.States;

import java.awt.Graphics2D;

import com.WinterStudios.Winter.Entity.Player;
import com.WinterStudios.Winter.Main.GameScreen;
import com.WinterStudios.Winter.Manager.Keys;
import com.WinterStudios.Winter.Manager.StateManager;
import com.WinterStudios.Winter.Maps.TileMap;

public class PlayState extends GameState{

	//player
	Player player;

	//map
	TileMap testMap;
	/*---------------------------------------------------------------------------------------------------------------*/	
	public PlayState(StateManager SM) {
		super(SM);
	}

	@Override
	public void init() {
		testMap = new TileMap("bin/Maps/testmap.txt");
		player = new Player(testMap);
		player.setPostion((GameScreen.WIDTH/2),(GameScreen.HEIGHT/2)-25);
		System.out.println(""+ ((GameScreen.WIDTH/2)- 16));
	}

	@Override
	public void Game_Update() {
		Handle_Input();
		player.update();

	}

	@Override
	public void Game_Render(Graphics2D g) {
		testMap.draw2(g);		
		player.draw(g);
	}

	@Override
	public void Handle_Input() {
		if(Keys.Is_Down(Keys.W))
			player.setUp(true);
		else if(Keys.Is_Down(Keys.S))
			player.setDown(true);
		else if(Keys.Is_Down(Keys.A))
			player.setLeft(true);
		else if(Keys.Is_Down(Keys.D))
			player.setRight(true);

	}

}
