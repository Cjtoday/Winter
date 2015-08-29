package com.WinterStudios.Winter.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import com.WinterStudios.Winter.Entity.Entity;
import com.WinterStudios.Winter.Manager.Keys;
import com.WinterStudios.Winter.Manager.StateManager;

public class GameScreen extends JPanel implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	
	private final Font fpsFont = new Font("Trajan Pro 3", Font.PLAIN, 10);

	//Dimensions
	public static final int WIDTH = 1400;
	public static final int HEIGHT = 768;
	
	public static final int XREZ = 16;
	public static final int YREZ = 9;

	//Timer
	private int Frame_Count = 0;
	public static final int FPS = 90;
	private float averageFPS;
	private long Target_Time = 1000 / FPS;
	private long Start_Time;
	private long Exe_Time;
	private long Sleep_Time;
	private long Total_Time = 0;

	//Thread
	private Thread Game_Driver;
	private boolean running = false;

	//Grapics
	Graphics2D Render_Graphics;		//used to draw to the buffered image
	BufferedImage Render_Image;		//iimage that is drawn off screen
	Graphics Draw_Graphics;			//used to draw the buffered image to te main screen

	//States
	StateManager SM = new StateManager();
/*---------------------------------------------------------------------------------------------------------------*/	
	//Constructor
	public GameScreen(){

		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();

	}//end Constructor


	//addNotify
	public void addNotify(){
		super.addNotify();
		if(Game_Driver == null || !running){
			addKeyListener(this);
			Game_Driver = new Thread(this);
			Game_Driver.start();
			running = true;
		}
	}//end addNotify


	//Run Method
	public void run() 
	{
		SM.loadState(StateManager.INTRO);
		Render_Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Render_Graphics = (Graphics2D) Render_Image.getGraphics();

		while(running)
		{
			Start_Time = System.nanoTime();

			Game_Update();
			Game_Render();
			Game_Draw();

			Exe_Time = (System.nanoTime() - Start_Time) / 1000000;

			Sleep_Time = Target_Time - Exe_Time;

			if (Sleep_Time > 0){
				try 
				{
					Game_Driver.sleep(Sleep_Time);
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Total_Time += System.nanoTime() - Start_Time;
				Frame_Count++;

				if(Frame_Count == FPS)
				{
					averageFPS = (float) (1000.0 / ((Total_Time / Frame_Count) / 1000000));
					Frame_Count = 0;
					Total_Time = 0;
				}
			}
		}
	}//end Run Method


	//Game_Update
	private void Game_Update()
	{
		SM.Game_Update();
		Keys.update();
	}//end Game_Update


	//Game_Render
	private void Game_Render()
	{
		SM.Game_Render(Render_Graphics);
		Render_Graphics.setFont(fpsFont);
		Render_Graphics.setColor(Color.GREEN);
		Render_Graphics.drawString("FPS: " + averageFPS, 10, 10);
	}//end Game_Render


	//Game_Draw
	private void Game_Draw()
	{
		Draw_Graphics = getGraphics();
		Draw_Graphics.drawImage(Render_Image, 0, 0, null);
		Draw_Graphics.dispose();
	}//end Game_Draw

	public void keyPressed(KeyEvent e) 
	{
		Keys.keyPressed(e.getKeyCode(), true);
	}
	public void keyReleased(KeyEvent e) 
	{
		Keys.keyPressed(e.getKeyCode(), false);	
		System.out.println("Key: " + e.getKeyCode() + " has been released" );
		
	}
	public void keyTyped(KeyEvent arg0) {

	}




}
