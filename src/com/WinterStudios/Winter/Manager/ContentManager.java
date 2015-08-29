package com.WinterStudios.Winter.Manager;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.text.AbstractDocument.Content;

/*
 * this class will manage the images/sprites etc needed by other classes.
 * 
 * ex: contnetn class will load images and sprites that will be used multiple times from a file and form there be used to draw over and over agiain
 * withouth the need for from a file many times
 * 
 */


public class ContentManager {

	public static final int	WIDTH = 32;
	public static final int HEIGHT = 32;


	public static BufferedImage[][] PLAYER = load("/Images/player.gif", HEIGHT, WIDTH);




	public static BufferedImage[][] load(String filepath, int h, int w){
		BufferedImage[][] ret;
		try{
			BufferedImage spritesheet = ImageIO.read(Content.class.getResourceAsStream(filepath));
			int height = spritesheet.getHeight()/h;
			int width = spritesheet.getWidth()/w;
			ret = new BufferedImage[height][width];
			for(int i = 0; i < height; i ++){
				for(int j = 0; j < width; j++){
					ret[i][j] = spritesheet.getSubimage(j*32, i*32, 32, 32);
				}
			}
			return ret;
		}catch(IOException e){
			System.err.println("error loading content from file: " + filepath);
			e.printStackTrace();
			System.exit(0);
		}
		return null;

	}
}
