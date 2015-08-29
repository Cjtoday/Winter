/*
 * There are many solutions to deal with this particular problem. When it comes to using Graphics or Graphics2D, the easiest solution I have found is to use the scale() function.
First determine your render dimensions, which you can consider your viewing boundary, how many pixels do you want the player to be able to see. For example: 800x600
You then need to determine your screen/window dimensions. This is the resolution you wish your final render to fill-up. For example: 1024x768
You then need to calculate the difference between 800x600 & 1024x768.
float scaleWidth = 1024 / 800 ;
float scaleHeight 768 / 600 ;
Before rendering your images call:
graphics.scale( scaleWidth, scaleHeight ) ;
The above technique will work perfectly fine, assuming that your aspect ratio is the same between the Render Dimensions and the Screen Dimensions. If the aspect ration is different then stretching will occur.
To solve the stretching issue you can choose to only scale with the shortest scaling difference:
float scaleWidth = 1024 / 800 ;
float scaleHeight 768 / 600 ;

if( scaleWith < scaleHeight )
{
    scaleHeight = scaleWidth ;
}
else
{
    scaleWidth = scaleHeight ;
}

graphics.scale( scaleWidth, scaleHeight ) ;
This will ensure the render dimensions ratio is enforced irrelevant of the screen dimensions. This will however, create black borders. You'll need to translate the render to centre it within the screen.
Hope this helps.
 */


package com.WinterStudios.Winter.Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.WinterStudios.Winter.Maps.TileMap;

public abstract class Entity {

	//position;
	protected int x;
	protected int y;
	protected int xDest;
	protected int yDest;
	protected int rowY;
	protected int colX;

	protected int pixle_per_frame;

	//movement
	public static boolean moving;
	public static boolean left;
	public static boolean right;
	public static boolean up;
	public static boolean down;


	protected int movementSpeed = 50;

	protected TileMap map;

	protected BufferedImage entityImg;

	/*---------------------------------------------------------------------------------------------------------------*/	
	public Entity(TileMap map){
		this.map = map;
	}

	public int getXposition(){	return x;	}
	public int getYposition(){	return y;	}

	public void setPostion(int x, int y){	
		this.x = x;
		this.y = y;
		rowY = y/50;
		colX = x/50;
	}

	public void setUp(boolean b){
		up = b;
	}
	public void setDown(boolean b){
		down = b;
	}
	public void setLeft(boolean b){
		left = b;
	}
	public void setRight (boolean b){
		right = b;
	}

	public void move(){
		
		
	}

	public boolean checkValidMove(){
		if(up){
			if(rowY - 1 < 0 || map.getTile(rowY-1, colX).isBlocked()){
				up = moving = false;
				return false;
			}
		}
		else if(down){
			if(rowY + 1 > map.getHeight()-1 || map.getTile(rowY+1, colX).isBlocked()){	
				down = moving = false;
				return false;
			}
		}
		else if(left){
			if(colX - 1 < 0 || map.getTile(rowY, colX - 1).isBlocked()){
				left = moving = false;
				return false;
			}
		}
		else if(right){
			if(colX + 1 > map.getWidth() || map.getTile(rowY, colX + 1).isBlocked()){
				right = moving = false;
				return false;
			}
		}

		return true;
	}


	public void update(){
		rowY = y/50;
		colX = x/50;
		
		
	}

	public void draw(Graphics2D g){	
		g.drawImage(entityImg, x,y,50,50,null);
	}



}
