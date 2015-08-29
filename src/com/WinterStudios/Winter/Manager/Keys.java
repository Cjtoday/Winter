package com.WinterStudios.Winter.Manager;

import java.awt.event.KeyEvent;

public class Keys {
		
	public static boolean pressed;
	
	public static final int NUMBER_OF_KEYS = 13;
	
	public static final int ENTER = 	0;
	public static final int ESC 	= 	1;
	public static final int SPACE = 	2;
	public static final int W		=	3;
	public static final int A		=	4;
	public static final int S		=	5;
	public static final int D		=	6;
	public static final int P		=	7;
	public static final int UP		=	8;
	public static final int DOWN	=	9;
	public static final int LEFT	=	10;
	public static final int RIGHT	=	11;
	public static final int F1 		=   12;
	
	
	public static boolean[] KEY_STATE = new boolean[NUMBER_OF_KEYS];
	public static boolean[]	PREVIOUS_KEY_STATE  = new boolean[NUMBER_OF_KEYS];	

	//Key Manager; takes and int and boolean. will determin what key index is pushed down
	public static void keyPressed(int i, boolean b){
		if(i == KeyEvent.VK_ENTER){KEY_STATE[ENTER] = b;}
		else if(i == KeyEvent.VK_ESCAPE){KEY_STATE[ESC] = b;}
		else if(i == KeyEvent.VK_SPACE){KEY_STATE[SPACE] = b;}
		else if(i == KeyEvent.VK_W){KEY_STATE[W] = b;}
		else if(i == KeyEvent.VK_A){KEY_STATE[A] = b;}
		else if(i == KeyEvent.VK_S){KEY_STATE[S] = b;}
		else if(i == KeyEvent.VK_D){KEY_STATE[D] = b;}
		else if(i == KeyEvent.VK_P){KEY_STATE[P] = b;}
		else if(i == KeyEvent.VK_UP){KEY_STATE[UP] = b;}
		else if(i == KeyEvent.VK_DOWN){KEY_STATE[DOWN] = b;}
		else if(i == KeyEvent.VK_LEFT){KEY_STATE[LEFT] = b;}
		else if(i == KeyEvent.VK_RIGHT){KEY_STATE[RIGHT] = b;}
	}
	
	//update
	public static void update(){
		for(int i = 0; i < NUMBER_OF_KEYS; i++)
		{
			PREVIOUS_KEY_STATE[i] = KEY_STATE[i];
		}
	}//end update
	
	
	//Is_Pressed
	public static boolean Is_Pressed(int x){
		return KEY_STATE[x] && !PREVIOUS_KEY_STATE[x];
	}//end is pressed

	public static boolean Is_Down(int x){
		return KEY_STATE[x];
	}
}
