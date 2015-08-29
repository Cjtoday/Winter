

/*
 * this class will use an arry filled with numbers that correspond to a specific tile
 * it will draw the tiles to a buffered image and that is all. all of the game logic will be handled in the 
 * play state class. 
 * 
 * takes input from a file as a string and creates a matrix of integers, which will be converted into a matrix of tile objects
 * this may be changed later to take in a sting and generate an array of tile objects right away.  
 * 
 * for testing purposases all maps will be 10 by 10 with tile sizes 
 * 
 * 
 */
package com.WinterStudios.Winter.Maps;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.WinterStudios.Winter.Main.GameScreen;


public class TileMap {
	
	private String mapLoc;
	
	//row and col
	private int row = 25;//13
	private int col =12;//22
	
	private Tile map[][] = new Tile[row][col];
	
	private BufferedImage tileSet;
	
	private BufferedImage[][] tiles;
	private BufferedImage mapImage;

	private int TilesetWidth;
	private int TilesetHeight;
	
	public TileMap(String mapLoc){
		this.mapLoc = mapLoc;
		init();
	}
	
	private void init(){
		loadTileSet();
		mapImage = loadMap2(mapLoc);
		
		
	}
	
	private void loadTileSet(){
		try
		{
			tileSet = ImageIO.read(getClass().getResourceAsStream("/Images/testtilesetiso.png"));
		}
		catch(IOException e)
		{
			System.err.println("error loading tileset");
			e.printStackTrace();
		}
		

		int tilesetWidth = tileSet.getWidth()/64;
		int tilesetHeight = tileSet.getHeight()/64;
		
		TilesetWidth = tilesetWidth;
		TilesetHeight= tilesetHeight;
		
		System.out.println("widht: " + tilesetWidth + " Height: " + tilesetHeight);

		tiles = new BufferedImage[tilesetHeight][tilesetWidth];

		for (int i = 0; i < tilesetHeight; i++){
			for (int j = 0; j < tilesetWidth; j++){
				tiles[i][j] = (BufferedImage)tileSet.getSubimage(j*64, i*64, 64, 64);
			}
		}
	}
	
	public BufferedImage loadMap2(String S){ 

		String mapString = null;

		try {		//"bin/Maps/testisomap.txt"
			BufferedReader br = new BufferedReader(new FileReader("bin/Maps/testisomap2.txt"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while(line != null){
				sb.append(line);
				line = br.readLine();
			}
			br.close();

			mapString = sb.toString();

		}catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(mapString.length());
		int string_Index = 0; //so we know what point we are at in the stirng
		char tile;
		BufferedImage builtMap = new BufferedImage(GameScreen.WIDTH, GameScreen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) builtMap.getGraphics();

		int x = -64; //0
		int y = -128;

		boolean even = true;
		
		int xScale = GameScreen.WIDTH / GameScreen.XREZ;
		int yScale = GameScreen.HEIGHT / GameScreen.YREZ;

		for(int i = 0; i <= row -1; i++)
		{
			for(int j = 0; j <= col-1; j++)
			{
				
				//width and height should be 64
				tile = mapString.charAt(string_Index);

				if (tile == 'a'){
					map[i][j] = new Tile(tiles[0][0], 0);
					g.drawImage(tiles[0][0],x,y,128,128,null);
				}
				else if(tile == 'c'){
					map[i][j] = new Tile(tiles[2][0], 1);
					g.drawImage(tiles[0][2],x,y,128,128,null);
				}
				else if(tile == 'e'){  
					map[i][j] = new Tile(tiles[1][1], 2);
					g.drawImage(tiles[1][1],x,y,128,128,null);
				}
				else if(tile == 'f'){  
					map[i][j] = new Tile(tiles[2][1], 2);
					g.drawImage(tiles[1][2],x,y,128,128,null);
				}
				else if(tile == 'j'){  
					map[i][j] = new Tile(tiles[1][3], 2);
					g.drawImage(tiles[3][1],x,y,128,128,null);
				}
				else if(tile == 'h'){  
					map[i][j] = new Tile(tiles[2][1], 2);
					g.drawImage(tiles[2][1],x,y,128,128,null);
				}
				
				x += 128; //64
				string_Index++;
			}
			y += 32; //16
			if(even){
				x = 0; //32
				even = false;
			}
			else if(!even){
				x = -64; //0
				even = true;
			}
			
		}
		return builtMap;
	}
	
	public void draw2(Graphics2D g){
		/*
		for (int i = 0; i < TilesetHeight; i++){
			for (int j = 0; j < TilesetWidth; j++){
			//	g.drawImage(tiles[i][j],j*64, i*64, 64, 64, null);
			}
			}
*/
		g.drawImage(mapImage,0,0,mapImage.getWidth(), mapImage.getHeight(),null);
	}
	
	
}

	/*
	
	private String MapLoc;

	//Row and Col info
	private int row = 9;
	private int col = 16;




	private Tile map[][] = new Tile[row][col];

	private BufferedImage mapImage;

	private BufferedImage tileset;
	private BufferedImage[][] tiles;


/*---------------------------------------------------------------------------------------------------------------	
	public TileMap(String MapLoc){
		System.out.println("Creating tileMap object");
		this.MapLoc = MapLoc;
		init();
	}

	public void init() {
		LoadTileset();
		mapImage = loadMap2(MapLoc);
		//LoadMap(MapLoc);
	}

	public void LoadTileset()
	{
		try
		{
			tileset = ImageIO.read(getClass().getResourceAsStream("/Images/tileset.gif"));
		}
		catch(IOException e)
		{
			System.err.println("error loading tileset");
			e.printStackTrace();
		}

		int tilesetWidth = tileset.getWidth()/32;
		int tilesetHeight = tileset.getHeight()/32;
		System.out.println("widht: " + tilesetWidth + " Height: " + tilesetHeight);

		tiles = new BufferedImage[tilesetHeight][tilesetWidth];

		for (int i = 0; i < tilesetHeight; i++){
			for (int j = 0; j < tilesetWidth; j++){
				tiles[i][j] = (BufferedImage)tileset.getSubimage(j*32, i*32, 32, 32);
			}
		}
	}

	public BufferedImage loadMap2(String S){ 

		String mapString = null;

		try {	
			BufferedReader br = new BufferedReader(new FileReader("bin/Maps/testmap.txt"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while(line != null){
				sb.append(line);
				line = br.readLine();
			}
			br.close();

			mapString = sb.toString();

		}catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		int string_Index = 0; //so we know what point we are at in the stirng
		int tile;
		BufferedImage builtMap = new BufferedImage(GameScreen.WIDTH, GameScreen.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) builtMap.getGraphics();

		int x = 0;
		int y = 0;

		int xScale = GameScreen.WIDTH / GameScreen.XREZ;
		int yScale = GameScreen.HEIGHT / GameScreen.YREZ;

		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				tile = Character.getNumericValue(mapString.charAt(string_Index));

				if (tile == 1){
					map[i][j] = new Tile(tiles[0][0], 0);
					g.drawImage(tiles[0][0],x,y,xScale,yScale,null);
				}
				else if(tile == 2){
					map[i][j] = new Tile(tiles[0][0], 1);
					g.drawImage(tiles[0][1],x,y,xScale,yScale,null);
				}
				else if(tile == 3){  
					map[i][j] = new Tile(tiles[0][0], 2);
					g.drawImage(tiles[0][2],x,y,xScale,yScale,null);
				}
				x += xScale;
				string_Index++;
			}
			y += yScale;
			x = 0;
		}
		return builtMap;
	}
	
	public Tile getTile(int row, int col){
		return map[row][col];
	}

	public int getWidth(){
		return col;
	}
	
	public int getHeight(){
		return row;
	}
	
	public void draw2(Graphics2D g){


		g.drawImage(mapImage,0,0,mapImage.getWidth(), mapImage.getHeight(),null);
	}


	/*public void LoadMap(String S){ 

		String mapString = null;

		try {	
			BufferedReader br = new BufferedReader(new FileReader("bin/Maps/testmap.txt"));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while(line != null){
				sb.append(line);
				line = br.readLine();
			}
			br.close();

			mapString = sb.toString();

		}catch (IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		int string_Index = 0; //so we know what point we are at in the stirng
		int tile;

		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				tile = Character.getNumericValue(mapString.charAt(string_Index));

				if (tile == 1)
					map[i][j] = tiles[0][0];
				else if(tile == 2)
					map[i][j] = tiles[0][1];
				else if(tile == 3)
					map[i][j] = tiles[0][2];

				string_Index++;
			}
		}
	}


	//draw the map to buffered image all the way back in game screen graphics object is passed through classes to reach here
	public void draw(Graphics2D g){
		int x = 0;
		int y = 0;
		for( int i = 0; i < row; i ++)
		{
			for( int j = 0; j < col; j++)
			{
				g.drawImage(map[i][j],x,y,32,32,null);
				x += 32;
			}
			y += 32;
			x = 0;
		}
	}
	 
}
*/


