package com.WinterStudios.Winter.Maps;

import java.awt.image.BufferedImage;

public class Tile {

	/*
	 * image will be added through the content manager calss so that the file does no need to read from a file
	 * every time it need to set the image of a tile.
	 */
		
	BufferedImage tileImage;

	private int tileType;	//can walk on, cannot walk on, move point, activation point
	
	private boolean blocked; // if this is true the tile cannot be walked on
	private boolean Portal;	//if this is true the tile leads to another 
	private String Destination;
	
	//need a variable to designate where to go if a tile is a portal//will be a new call of play state but with a new map as a parameter???????
	
	//dimensions
	private final int Tile_Width = 32;	 //32*50= 1600
	private final int Tile_Height = 32; //32*28 <=> 900
	
	
	public Tile(BufferedImage bi, int i){
		this.tileImage = bi;
		if(i == 2)
			blocked = true;
	}
	
	
	public boolean isBlocked(){
		return blocked;
	}
	
	
}
