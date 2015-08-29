package com.WinterStudios.Winter.Entity;

import com.WinterStudios.Winter.Manager.ContentManager;
import com.WinterStudios.Winter.Maps.TileMap;

public class Player extends Entity{

	public Player(TileMap map) {
		super(map);
		entityImg = ContentManager.PLAYER[0][0];
	}
	
	
}
