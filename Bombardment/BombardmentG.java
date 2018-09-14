/**
 * Ryan Gahagan
 *P3 Jadeja
 *This is the seventh version
 *It will make the playing field square 800 x 800
 */
import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.*;
public class BombardmentG 
extends Applet 
implements KeyListener{    
    static void main(String[] args) {
    }
	private Rectangle rect; //rect that gets moved
	private Rectangle rect2; //player rect
	private Arrow arrow1; //first arrow
	boolean bool = true; //creates an initial loop
	public static int shieldSide = 0; //shield Side 0 is up, 1 is right, etc
	public static int wait = 0;
	public void init() //initializing method
	{
		int speed = enterIntGUI("How fast are your reflexes? (will strongly affect gameplay) \n 1 = Super slow \n 2 = Weaksauce \n 3 = Alright \n 4 = Pretty good \n 5 = Sonic Speed \n 6 = GODLIKE");
		rect = new Rectangle(340, 300, 120, 30); //shield rectangle
		rect2 = new Rectangle (340, 340, 120, 120); //player rect
		arrow1 = new Arrow(Expo.random(0, 3), Arrow.arrowSpeed); //creates the arrow
		this.addKeyListener(this); //creates a key listener
		switch(speed)
		{
			case 1: wait = 35; break;
			case 2: wait = 20; break;
			case 3: wait = 13; break;
			case 4: wait = 5; break;
			case 5: wait = 0; break;
			case 6: wait = 0; Arrow.arrowSpeed = 3; break;
		}
	}
	public void paint(Graphics g) //paint method
	{
		while(bool)
		{
			Expo.setColor(g, Expo.gold);
			Expo.setFont(g, "Arial", Font.BOLD, 100);
			Expo.drawString(g, "BOMBARDMENT", 1, 100);
			Expo.setColor(g, Expo.black);
			Expo.setFont(g, "Arial", Font.BOLD, 70);
			Expo.drawString(g, "Loading...", 255, 350);
			Expo.setFont(g, "Arial", Font.PLAIN, 30);
			Expo.drawString(g, "Arrow keys to control shield", 250, 400);
			Expo.drawString(g, "Block the arrows!", 265, 430);
			Expo.delay(5000);
			bool = false;
		}
		setSize(800, 800); //sets screen size
		Graphics2D g2 = (Graphics2D)g; //g2 screen
		Expo.setColor(g, Expo.blue); 
		g2.fill(rect); //draws shield 
		g2.draw(rect2); //draws player rect
		Expo.setColor(g, Expo.green); 
		Arrow.drawLives(g2); //draws the num of lives in the corner
		Expo.fillStar(g2, 400, 400, 20, 5); //draws player
		arrow1.drawMovingArrow(g2); //draws the arrow
		Arrow.checkShield(g2, arrow1.arrowX, arrow1.arrowY, arrow1.arrowSide, shieldSide, arrow1); //checks for collision between arrow & shield
		if(Arrow.lives > 0) {update(); }//repaints
		else
		{
			Expo.setColor(g2, Expo.white);
			Expo.fillRectangle(g2, 0, 0, 800, 800); //clears screen
			Expo.setColor(g2, Expo.black);
			Expo.setFont(g2, "Arial", Font.BOLD, 70);
			Expo.drawString(g2, "GAME OVER", 165, 250);
			Expo.setFont(g2, "Arial", Font.PLAIN, 40);
			Expo.drawString(g2, "GG", 360, 300);
			Expo.drawString(g2, "Score: " + String.valueOf(Arrow.score), 300, 350);
		}
	}
	public void keyPressed ( KeyEvent e ) { //sets shield side off of arrow keys
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) //right arrow key
		{
			rect.setSize(30, 120);
			rect.setLocation(480, 340);
			shieldSide = 1;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) //left arrow key
		{
			rect.setSize(30, 120);
			rect.setLocation(290, 340);
			shieldSide = 3;
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP) //up arrow key
		{
			rect.setSize(120, 30);
			rect.setLocation(340, 300);
			shieldSide = 0;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) //down arrow key
		{
			rect.setSize(120, 30);
			rect.setLocation(340, 470);
			shieldSide = 2;
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) //escape causes lots of errors
		{
			System.exit(0);
		}
	}
	public void keyReleased ( KeyEvent e ) {} 
	public void keyTyped ( KeyEvent e ) {}
	public void update() {
		Expo.delay(wait);
		repaint();
	}
	public static int enterIntGUI(String prompt)  // Allows GUI keyboard input of an integer in a graphics program.
	{
		String tempString = JOptionPane.showInputDialog(prompt);
		int temp = Integer.parseInt(tempString);
		return temp;
	}
}
class Arrow //for all your arrow needs
{
	public int arrowSide; //side the arrow is on, same as shield
	static int arrowSpeed = 2; //num of pixels arrow moves each repaint
	public int arrowX = 0; //arrow coords 
	public int arrowY = 0; //arrow coords
	static int delayTime = 40; //time between repaints
	static int lives = 3; //player lives
	static int score = 0; //player's score
	static int accel = 5; //provides a better acceleration factor
	public Arrow() //default constructor
	{
		arrowSide = 0;
		arrowSpeed = 1;
	}
	public Arrow(int side, int speed) //overloaded constructor
	{
		arrowSide = side; //sets side
		arrowSpeed = speed; //sets speed
		if(arrowSide == 0) {arrowX = 400; arrowY = 0;} //sets arrow coords based on side
		else if(arrowSide == 1) {arrowX = 800; arrowY = 400;}
		else if(arrowSide == 2) {arrowX = 400; arrowY = 800;}
		else {arrowX = 0; arrowY = 400;}		
	}
	public void setArrowCoords(int side) //sets arrow coords based on side
	{
		if(arrowSide == 0) {arrowX = 400; arrowY = 0;} //sets arrow coords based on side
		else if(arrowSide == 1) {arrowX = 800; arrowY = 400;}
		else if(arrowSide == 2) {arrowX = 400; arrowY = 800;}
		else {arrowX = 0; arrowY = 400;}
	}
	public static void drawDownArrow(Graphics g, int arrowX, int arrowY) //draw a downward arrow
	{
		Expo.setColor(g, Expo.red);
		Expo.fillPolygon(g, arrowX, arrowY, arrowX + 15, arrowY - 15, arrowX - 15, arrowY -15); //arrowhead
		Expo.fillRectangle(g, arrowX - 5, arrowY - 40, arrowX + 5, arrowY -15); //arrow body
	}
	public static void drawLeftArrow(Graphics g, int arrowX, int arrowY) //draw a leftward arrow
	{
		Expo.setColor(g, Expo.red);
		Expo.fillPolygon(g, arrowX, arrowY, arrowX + 15, arrowY + 15, arrowX + 15, arrowY - 15); //arrowhead
		Expo.fillRectangle(g, arrowX + 15, arrowY - 5, arrowX + 40, arrowY + 5); //arrow body
	}
	public static void drawUpArrow(Graphics g, int arrowX, int arrowY) //draw an upward arrow
	{
		Expo.setColor(g, Expo.red);
		Expo.fillPolygon(g, arrowX, arrowY, arrowX + 15, arrowY + 15, arrowX - 15, arrowY + 15); //arrowhead
		Expo.fillRectangle(g, arrowX - 5, arrowY + 15, arrowX + 5, arrowY + 40); //arrow body
	}
	public static void drawRightArrow(Graphics g, int arrowX, int arrowY) //draws a rightward arrow
	{
		Expo.setColor(g, Expo.red);
		Expo.fillPolygon(g, arrowX, arrowY, arrowX - 15, arrowY - 15, arrowX - 15, arrowY + 15); //arrowhead
		Expo.fillRectangle(g, arrowX - 40, arrowY - 5, arrowX - 15, arrowY + 5); //arrowbody
	}
	public void drawMovingArrow(Graphics g) //draws a moving arrow
	{
			if(arrowSide == 0)
			{
				drawDownArrow(g, arrowX, arrowY);
				arrowY += arrowSpeed;
			}
			else if(arrowSide == 1)
			{
				drawLeftArrow(g, arrowX, arrowY);
				arrowX -= arrowSpeed;
			}
			else if(arrowSide == 2)
			{
				drawUpArrow(g, arrowX, arrowY);
				arrowY -= arrowSpeed;
			}
			else if(arrowSide == 3)
			{
				drawRightArrow(g, arrowX, arrowY);
				arrowX += arrowSpeed;
			}
			Expo.delay(delayTime);
	}
	public static void checkShield(Graphics g, int x, int y, int side, int shield, Arrow arrow) //checks if shield blocks arrow
	{
	
		if(side == shield) //are the arrow and shield on the same side?
		{
			switch(delayTime)
			{
				case 30: accel = 4; break;
				case 22: accel = 3; break;
				case 16: accel = 2; break;
				case 6 : accel = 1; break;
			}
			if(side == 0) //check respective side
			{
				if (y >= 300 && y <= 335) { //check if coords match
					arrow.arrowSide = Expo.random(0, 3);  //reset arrow
					arrow.setArrowCoords(arrow.arrowSide);  //reset arrow coords
					delayTime -= accel; //accelerate
					score += 1; //increment score
					java.awt.Toolkit.getDefaultToolkit().beep(); //play a beep
				}
			}
			else if(side == 1) //same as above
			{
				if (x >= 465 && x <= 510) {
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide);
					delayTime -= accel;
					score += 1;
					java.awt.Toolkit.getDefaultToolkit().beep(); //play a beep
				}
			}
			else if(side == 2) //same as above
			{
				if (y >= 465 && y <= 500) {
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide);
					delayTime -= accel;
					score += 1;
					java.awt.Toolkit.getDefaultToolkit().beep(); //play a beep
				}
			}
			else if(side == 3) //same as above
			{
				if (x >= 290 && x <= 335) {
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide); 
					delayTime -= accel;
					score += 1;
					java.awt.Toolkit.getDefaultToolkit().beep(); //play a beep
				}
			}
			if(delayTime <= 3) {delayTime = 3;} //doesn't allow the program to accelerate too much			
		}
		else if(side != shield) //if the arrow and shield are on the wrong sides
		{
			if(side == 0) //check respective sides
			{
				if (y >= 335) { //if arrow passes shield
					lives -= 1;  //lose a life
					arrow.arrowSide = Expo.random(0, 3); //reset arrow
					arrow.setArrowCoords(arrow.arrowSide);  //reset arrow coords
					delayTime = 40; //reset arrow speed
					accel = 5; //reset acceleration
				}
			}
			else if(side == 1) //same as above
			{
				if (x <= 465) {
					lives -= 1; 
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide); 
					delayTime = 40;
					accel = 5;
				}
				
			}
			else if(side == 2) //same as above
			{
				if (y <= 465) {
					lives -= 1; 
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide); 
					delayTime = 40;
					accel = 5;
					}
			}
			else if(side == 3) //same as above
			{
				if (x >= 335) {
					lives -= 1; 
					arrow.arrowSide = Expo.random(0, 3); 
					arrow.setArrowCoords(arrow.arrowSide); 
					delayTime = 40;
					accel = 5;
				}
			}
		}
	}
	public static void drawLives(Graphics g) //draws a star in the corner for each of the player's lives
	{
		Expo.setColor(g, Expo.green);
		for(int bleh = 0; bleh < lives; bleh++)
		{
			Expo.fillStar(g, bleh * 30 + 30, 30, 20, 5);
		}
	}
}