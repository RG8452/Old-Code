/*
 *Pong class
 *Handles paddles and all necessary logic in regards to moving the ball
 */
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class Pong implements KeyListener
{
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //get screen size
	public static Color background = Color.white;  //set color of ball & paddles to white
	
	private static double ballX, ballY;  //coords of current block
	private static int ballRadius = 30;  //radius of ball
	private static double ballXSpeed, ballYSpeed;  //speed of ball
	
	private static double maxSpeed = 7.5;  //speed at which the ball moves (adjustable?)
	
	private static int paddleSpeed = 25;  //distance one keystroke moves the paddle
	private static int paddle1X = 40;  //paddle one x
	private static int paddleWidth = 60; //width of paddle
	private static int paddleHeight = screenSize.height / 4;  //height of paddle
	private static int paddle2X = screenSize.width - 50 - paddleWidth;  //paddle 2 x
	private static int paddle1Y = screenSize.height / 3; //paddle 1 y
	private static double paddle2Y = screenSize.height / 3; //paddle 2 y
	
	private static int moveY1 = 0;  //dir of y movement for p1
	private static int p1score, p2score; //score counter
	
	private static long start = System.nanoTime() / 1000000;  //get game start
	
	//logic methods
	public static void play()
	{
		resetBall();  //reset ball
		
		while(true)  //loop forever
		{
			if((System.nanoTime()/1000000 - start) > 10)  //if 10 milliseconds have passed since last refresth
			{
				moveBall();  //move the ball
				moveCPU();  //move opponent
				GUI.getMyPanel().repaint(); //repaint
				start = System.nanoTime() / 1000000;  //reset timer
				
				movePlayer1();
				
//				background = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));   //RAINBOWS??  (seizure wanring)
			}
		}		
	}
	
	public static void moveBall()  //moves and bounds the ball
	{
		ballX += ballXSpeed;  //move the x
		
		if(ballX >= (paddle1X + 15) && ballX <= (paddle1X + paddleWidth) && ballY >= (paddle1Y - 10) && ballY <= (paddle1Y + paddleHeight + 10))  //if ball hits left paddle
		{
			ballXSpeed *= -1;  //flip x speed
			ballX += ballXSpeed;
		}
		
		else if(ballX >= (paddle2X - 15) && ballX <= (paddle2X + paddleWidth - 15) && ballY >= (paddle2Y - 10) && ballY <= (paddle2Y + paddleHeight + 10))  //if ball hits right paddle
		{ 
			ballXSpeed *= -1;  //flip x speed
			ballX += ballXSpeed;
		}
		
		else if(ballX < (paddle1X + 15))  //if ball is behind left paddle
		{
			p2score++;  //increment p2score
			resetBall(1);  //reset ball
		}
		
		else if(ballX > (paddle2X + paddleWidth - 15))  //if ball is behind right paddle
		{
			p1score++;  //increment p1score
			resetBall(2);  //reset ball
		}
		
		ballY += ballYSpeed;  //move the y
		
		if(ballY < 15)  //if the ball hits the ceiling
		{
			ballY = 15; //keep it in bounds
			ballYSpeed *= -1;  //flip the y speed
		}
		
		else if(ballY > (screenSize.height - 95)) //elif the ball hits the bottom
		{
			ballY = screenSize.height - 95; //keep it in bounds
			ballYSpeed *= -1;  //flip the y speed
		}
	}
	
	public static void moveCPU()  //moves the CPU on the right
	{
		paddle2Y += ballYSpeed;  //aligns the paddle with the ball
		if(paddle2Y < 0) {paddle2Y = 0;}  //bound the ceiling
		else if((paddle2Y + paddleHeight) >= (screenSize.height-80)) {paddle2Y = screenSize.height - paddleHeight - 80;}  //bound the floor
	}
	
	public static void resetBall()  //resets the ball to center and random direction
	{
		ballX = screenSize.width / 2;  //center x
		ballY = (screenSize.height - 80) / 2;  //center y
		
		int rnd = (int) (Math.random() * 2);  //randomizes x direction
		ballXSpeed = (rnd % 2 == 0) ? (0 - maxSpeed) : maxSpeed;
		rnd = (int) (Math.random() * 2); //randomizes y direction
		ballYSpeed = (rnd % 2 == 0) ? (0 - maxSpeed) : maxSpeed;
	}
	
	public static void resetBall(int player)  //resets the ball towards the loser
	{
		ballX = screenSize.width / 2;  //center x
		ballY = (screenSize.height - 80) / 2;  //center y
		
		ballXSpeed = (player%2 == 0) ? maxSpeed : (0 -  maxSpeed);
		int rnd = (int) (Math.random() * 2);  //randomizes y direction
		ballYSpeed = (rnd % 2 == 0) ? (0 - maxSpeed) : maxSpeed;
	}
	
	//keyListener methods
	public synchronized void keyPressed(KeyEvent e)
	{
		//both WS and UP/DOWN move the left paddle and bound its height
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_W:
				moveY1 = -1;
				break;
			case KeyEvent.VK_S:
				moveY1 = 1;
				break;
			case KeyEvent.VK_UP:
				moveY1 = -1;
				break;
			case KeyEvent.VK_DOWN:
				moveY1 = 1;
				break;
		}
		
		GUI.getMyPanel().repaint();
	}
	
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_W:
				moveY1 = 0;
				break;
			case KeyEvent.VK_S:
				moveY1 = 0;
				break;
			case KeyEvent.VK_UP:
				moveY1 = 0;
				break;
			case KeyEvent.VK_DOWN:
				moveY1 = 0;
				break;
		}
	}
	
	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();
		
//		FrameTest.getMyPanel().repaint();
	}   
		
		
	
	//draw methods
	public static void drawBackground(Graphics2D g)  //draws all components
	{
		drawPaddles(g);
		drawLine(g);
		drawBall(g);
		drawScore(g);
	}
	
	public static void drawBall(Graphics2D g)  //draws the ball
	{
		g.fillOval((int)ballX, (int)ballY, ballRadius, ballRadius);
	}
	
	public static void drawPaddles(Graphics2D g)  //draws both paddles
	{
		g.fillRect(paddle1X, paddle1Y, paddleWidth, paddleHeight);
		g.fillRect((int)paddle2X, (int)paddle2Y, paddleWidth, paddleHeight);
	}
	
	public static void drawLine(Graphics2D g)  //draws line dividing the middle
	{
		int rectHeight = (int) (screenSize.height / 12);
		
		for(int q = 0; q <= 15; q += 2)
		{
			g.fillRect((int)(screenSize.width / 2) - 15, (q * rectHeight), 30, rectHeight);
		}
	}
	
	public static void drawScore(Graphics2D g)  //draws the scoreboard
	{
		Font f = new Font("Impact", Font.BOLD, 100);
		
		g.setFont(f);
		g.drawString(String.valueOf(p1score), screenSize.width/2 - 120, 90);
		g.drawString(String.valueOf(p2score), screenSize.width/2 + 60, 90);
	}
	
	public static void movePlayer1()
	{
		paddle1Y += 5 * moveY1;
		if(paddle1Y < 0) {paddle1Y = 0;}
		if((paddle1Y + paddleHeight) >= (screenSize.height-80)) {paddle1Y = screenSize.height - paddleHeight - 80;}
	}
}