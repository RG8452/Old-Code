/**
 * Ryan Gahagan
 * P4 Jad Jadeja
 * Programming II
 * 19 April 2017
 * Final instance of Battleship
 * This game allows each player to set up battleships with WASD
 * Then, each player takes turns firing on each other until one player has no remaining ships
 * At that point, the game is over and both sides get to see each other's boards.
 * Same as attempt three, only expanded for legibility
 */
 
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class Battleship extends JPanel implements ActionListener, KeyListener
{
	static int block = EnterIntGUI("How large would you like each box to be? (Recommend 80)"); //block size
	static Ship boat; //current ship
	static boolean switching = false; //determines if switching
	static Color[][] player1 = new Color[10][10]; //p1 board
	static Color[][] targets1 = new Color[10][10]; 
	static Color[][] player2 = new Color[10][10]; //p2 board
	static Color[][] targets2 = new Color[10][10]; 
	static Color sea = new Color(0, 158, 162); //ocean green!
	static int player = 1;  //declares player and switching variable
	static boolean initial = true;  
	static ArrayList<Ship> allBoats = new ArrayList<Ship>();  //list of all ships a player gets
	static boolean intro = true; //boolean for title
	static Battleship game = new Battleship();
	static int p1hits; //number of hits each player has, used for win condition
	static int p2hits; 
	static boolean end = false;
		
    public Battleship() //sets up the game
    { 
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics g)
    {  //Used for painting
    	super.paintComponent(g); //g2d stuff
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, //aliasing
							RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(intro) //draws a title screen
		{ 
			g.setFont(new Font("TimesNewRoman", Font.BOLD, getWidth()/7));
			g.setColor(new Color(3, 126, 150));
    		g.drawString("BATTLESHIP", getWidth()/20, getHeight()/3);
    		g.setFont(new Font("TimesNewRoman", Font.BOLD, getWidth()/14));
    		g.setColor(new Color(17, 23, 102));
    		g.drawString("Press space to continue", getWidth()/15, getHeight()/6 * 5);
		}
		
		if(!switching && !intro) //non switching
		{ 
			g2d.clearRect(0,0,getWidth(),getHeight());
			drawBackground(g2d); //draws grid & colors
	    	if(initial) //if the players are setting up
	    	{ 
	    		g2d.setColor(Color.RED); //draws current boat
	    		boat.draw(g2d);
	    	}
		}					
		else if (switching && !intro) //clears the screen while swapping
		{ 
			g2d.clearRect(0,0,getWidth(),getHeight());
			g2d.setColor(sea);
			g2d.fillRect(0,0,getWidth(),getHeight());
		}
		
		if(victory() && end) //win screen
		{ 
			g2d.clearRect(0,0,getWidth(),getHeight());
			drawEnd(g2d);
		}
    }
    
    public static int getBlockSize() //gets block size
    {
    	return block;
    } 
    
    public void actionPerformed(ActionEvent e){} //for implementation
    
    public void keyReleased(KeyEvent e){}
    
    public void keyPressed(KeyEvent e){}  //keyListener stuff
    
    public void keyTyped(KeyEvent e)
    {
    	int code = e.getKeyCode();  //gets key
    	char c = e.getKeyChar();
//    	System.out.println(c);  //keyPress debug
    	
    	if(initial && !intro) 
    	{
    		boat.move(c);   //if the players are setting up, then move the boat
    	}
    	
    	if(c == ' ' && initial && !intro) //if the player places a ship with space
    	{ 
    		boolean validSpot = true;
    		//change arrays to simulate ship being placed
    		if(player == 1)
    		{
    			for(int i=0;i<boat.getLength();i++)
    			{
    				switch(boat.getRotation())  //draws out the boat based on rotation
    				{
    					case 0:
    						if(player1[boat.getRow()-i][boat.getCol()].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 1:
    						if(player1[boat.getRow()][boat.getCol()+i].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 2:
    						if(player1[boat.getRow()+i][boat.getCol()].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 3:
    						if(player1[boat.getRow()][boat.getCol()-i].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					default:
    						System.out.println("BAD NEWS P1");
    				}
    			}
    		} 
    			
    		else if(player == 2) //same as above but with the other player
    		{ 
    			for(int i=0;i<boat.getLength();i++)
    			{
    				switch(boat.getRotation())
    				{
    					case 0:
    						if(player2[boat.getRow()-i][boat.getCol()].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 1:
    						if(player2[boat.getRow()][boat.getCol()+i].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 2:
    						if(player2[boat.getRow()+i][boat.getCol()].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					case 3:
    						if(player2[boat.getRow()][boat.getCol()-i].equals(Color.GRAY)) 
    						{
    							validSpot = false;
    						}
    						break;
    					default:
    						System.out.println("BAD NEWS P2");
    				}
    			}
    		}
    		
    		//PLACES BOAT IF POSSIBLE
    		if(validSpot)
    		{
    			if(player == 1)
    			{
	    			for(int i=0;i<boat.getLength();i++)
	    			{
	    				switch(boat.getRotation())  //draws out the boat based on rotation
	    				{
	    					case 0:
	    						player1[boat.getRow()-i][boat.getCol()] = Color.GRAY;
	    						break;
	    					case 1:
	    						player1[boat.getRow()][boat.getCol()+i] = Color.GRAY;
	    						break;
	    					case 2:
	    						player1[boat.getRow()+i][boat.getCol()] = Color.GRAY;
	    						break;
	    					case 3:
	    						player1[boat.getRow()][boat.getCol()-i] = Color.GRAY;
	    						break;
	    					default:
	    						System.out.println("BAD NEWS P1");
	    				}
	    			}
	    		} 
	    			
	    		else if(player == 2)//same as above but with the other player
	    		{ 
	    			for(int i=0;i<boat.getLength();i++)
	    			{
	    				switch(boat.getRotation())
	    				{
	    					case 0:
	    						player2[boat.getRow()-i][boat.getCol()] = Color.GRAY;
	    						break;
	    					case 1:
	    						player2[boat.getRow()][boat.getCol()+i] = Color.GRAY;
	    						break;
	    					case 2:
	    						player2[boat.getRow()+i][boat.getCol()] = Color.GRAY;
	    						break;
	    					case 3:
	    						player2[boat.getRow()][boat.getCol()-i] = Color.GRAY;
	    						break;
	    					default:
	    						System.out.println("BAD NEWS P2");
	    				}
	    			}
	    		}
    		
	    		//change boat
	    		if(allBoats.size() != 0) 
	    		{
	    			boat = allBoats.get(0); //gets each boat in turn
	    			allBoats.remove(0);
	    		} 
	    		else if (allBoats.size() == 0) 
	    		{
	    			if(player == 1) //swaps players
	    			{ 
	    				switching = true;  
	    				setup();
	    				repaint();
	    				infoBox("Please swap players.", "Taking Turns");
	    				switching = false;
	    				swap();
	    			}
	    			
	    			else if(player == 2) //swaps players
	    			{  
	    				initial = false;
	    				switching = true;
				    	repaint();
				    	swap();
				    	infoBox("Please swap players.", "Taking Turns");
				    	switching = false;
				    	repaint(); 
			    		letsPlay(); //heads to logic method after all boats are placed
	    			}
	    		}
    		}
    	}
    	
    	if(intro && c == ' ') 
    	{
    		intro = false; //checks initialization
    	} 
    	
    	repaint();
    } 

    public static void main(String[] args) 
    {
        JFrame jf = new JFrame(); //creates the frame
        
        jf.add(game);  //sets up the frame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(block*23+20, block*11+45);
        jf.setVisible(true);
        
        fillArrays(); //fills color arrays with sea
        setup(); //sets up ship list
    }
    
    public static void letsPlay() //logic method
    { 
    	boolean notTaken = false; //spot bool
    	String message = "";  //output
    	
    	while(!victory())
		{			
			String coord = EnterStringGUI("Player " + player + ", please enter a coordinate."); //retrieve coord
			
			int col;
			char row = coord.charAt(0); //row 
			String remainder = coord.substring(1, coord.length()); //gets the rest
			if(remainder.length() == 0) 
			{
				col = 11; //error handling for no number
			} 
			else 
			{
				try //error handling for illegal argument
				{
					col = Integer.parseInt(remainder);
				} 
				catch(IllegalArgumentException e) 
				{
					col = 11;
				}
			}
			
			if(row == 'Z' && col == 0) 
			{
				p1hits = 17; //debug mode
			} 
			
			if(player == 1 && row >=65 && row <=74 && col>=1 && col<=10) 
			{
				notTaken = (targets1[row-65][col-1].equals(sea)); //set notTaken if that spot has already been fired upon
			}  
				
			if(player == 2 && row >=65 && row <=74 && col>=1 && col<=10) 
			{
				notTaken = (targets2[row-65][col-1].equals(sea));
			}

			while(!(row >=65 && row <=74 && col>=1 && col<=10) || !notTaken) //as long as the player has chosen an invalid spot
			{ 
				notTaken = true;
				message = "Please choose a valid coordinate."; //get another coord
				if(row >=65 && row <=74 && col>=1 && col<=10) //if in bounds
				{ 
					if(player == 1) 
					{
						if(!(targets1[row-65][col-1].equals(sea)))   //re checks taken
						{
							notTaken = false;
						}
					}
					
					if(player == 2) 
					{
						if(!targets2[row-65][col-1].equals(sea)) 
						{
							notTaken = false;
						}
					}
					
					message = "You've already fired on this spot!"; //informs the player that they've already fired there
				}
				
				coord = EnterStringGUI(message);  //resets coordinates
				remainder = coord.substring(1, coord.length());
				
				if(remainder.length() == 0) 
				{
					col = 11; //error handling for no number
				} 
					
				else 
				{
					try
					{
						col = Integer.parseInt(remainder);
					} 
					catch(IllegalArgumentException e) 
					{
						col = 11;
					}
				}
				row = coord.charAt(0); 
			}
			int r = row - 65; 
			col-=1; //turns data into array indices
			
			if(player == 1) 
			{ 
				targets1[r][col] = getHit(r, col); //sets target board and enemy board
				player2[r][col] = getHit(r, col);
			} 
			else
			{
				targets2[r][col] = getHit(r, col);
				player1[r][col] = getHit(r, col);
			}

			game.repaint();
				
			String lastShot = "miss"; //status of last shot fired
			
			if(player==1)
			{
				if(targets1[r][col].equals(Color.RED)) 
				{
					infoBox("Hit!", "Status"); p1hits++; lastShot = "hit"; //informs the player of their shot
				} 
				else {infoBox("Miss!", "Status");}
			} 
			else 
			{
				if(targets2[r][col].equals(Color.RED)) 
				{
					infoBox("Hit!", "Status"); p2hits++; lastShot = "hit";
				}
				else 
				{
					infoBox("Miss!", "Status");
				}
			}
			
			if(!victory()) //if nobody has won
			{
				switching = true; //switch players
				game.repaint();
				infoBox("Please swap players.", "Taking Turns");
				swap();
				switching = false;
				game.repaint();
				
				String update = "Player "; //set up a string to inform the player of where they were attacked
				update += (player%2 + 1); update += " got a ";
				update += lastShot; update += " at coordinate ";
				update += String.valueOf(row); update += (col + 1); update += ".";
				infoBox(update, "Notice!");
			}
			
			else //if a player has won the game
			{ 
				infoBox("You have sunk all the opponent's battleships!", "Victory!");
				end = true;
				game.repaint();
				String jad = "Player " + player + " wins!";
				infoBox(jad, "The End");
			}
		}
    }
    
    public static void infoBox(String infoMessage, String titleBar) //used to pop up messages
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static int EnterIntGUI(String prompt) //retrieves an int
    { 
    	String tempString = JOptionPane.showInputDialog(prompt);
    	if(tempString.length()==0) 
    	{
    		tempString = EnterStringGUI("Please enter a real number!"); //error handling for an empty number
    	}
    	int temp = Integer.parseInt(tempString);
    	return temp;
    }
    
    public static String EnterStringGUI(String prompt) //retrives a string
    { 
    	String ans = JOptionPane.showInputDialog(prompt);
    	if(ans.length() == 0) 
    	{
    		ans = EnterStringGUI("Please use a valid input!"); //error handling for no input
    	}
    	return ans;
    }
    
    public static void drawBackground(Graphics g) //draws the background
    { 
    	g.setColor(Color.BLACK);
    	for(int q=1;q<11;q++){ //grid
    		g.drawLine(block*q, 0, block*q, block*11);
    		g.drawLine(block*(q+12), 0, block*(q+12), block*11);
    		
    		g.drawLine(0, block*q, block*11, block*q);
    		g.drawLine(block*12, block*q, block*23, block*q);
    	}
    	
    	g.fillRect(block*11, 0, block, block*11);
    	
    	g.setFont(new Font("TimesNewRoman", Font.BOLD, block/3)); //sets font
    	for(int q = 0; q < 10; q++)
    	{
    		g.drawString(String.valueOf((char)(q+65)), block/2-block/9, block*(q+1) + block/2+block/9); //draws in the letters
    		g.drawString(String.valueOf((char)(q+65)), block/2-block/9 + block*12, block*(q+1) + block/2+block/9);
    		
    		g.drawString(String.valueOf(q+1), block*(q+1) + block/2-block/9, block/2+block/9); //draws in the numbers
    		g.drawString(String.valueOf(q+1), block*(q+1) + block/2-block/9 + block*12, block/2+block/9);
    	}
    	
    	if(player == 1)  //fill p1 board
    	{ 
    		g.setColor(Color.BLUE);
    		g.drawString("P1", block/2-block/8, block/2+block/9);
    		
    		g.setColor(Color.ORANGE);
    		g.drawString("T", block/2-block/8 + block*12, block/2+block/9);
    		
    		for(int i=0;i<10;i++)
    		{
    			for(int k=0;k<10;k++)
    			{
    				g.setColor(player1[i][k]);
    				g.fillRect(block*(k+1)+1, block*(i+1)+1, block-1,block-1); //fill player squares
    				
    				g.setColor(targets1[i][k]);
    				g.fillRect(block*(k+13)+1, block*(i+1)+1, block-1,block-1); //fill target squares
    			}
    		}
    	}
    	
    	else  //fill p2 board, same as previous
    	{ 
    		g.setColor(Color.ORANGE);
    		g.drawString("P2", block/2-block/8, block/2+block/9);
    		
    		g.setColor(Color.BLUE);
    		g.drawString("T", block/2-block/8 + block*12, block/2+block/9);
    		
    		for(int i=0;i<10;i++)
    		{
    			for(int k=0;k<10;k++)
    			{
    				g.setColor(player2[i][k]);
    				g.fillRect(block*(k+1)+1, block*(i+1)+1, block-1,block-1);
    				
    				g.setColor(targets2[i][k]);
    				g.fillRect(block*(k+13)+1, block*(i+1)+1, block-1,block-1);
    			}
    		}
    	}	
    }
    
    public static void drawEnd(Graphics g)  //draws the ending screen with both boards visible
    { 
    	g.setColor(Color.BLACK);
    	for(int q=1;q<11;q++) //grid
    	{ 
    		g.drawLine(block*q, 0, block*q, block*11);
    		g.drawLine(block*(q+12), 0, block*(q+12), block*11);
    		
    		g.drawLine(0, block*q, block*11, block*q);
    		g.drawLine(block*12, block*q, block*23, block*q);
    	}
    	g.fillRect(block*11, 0, block, block*11);
    	
    	g.setFont(new Font("TimesNewRoman", Font.BOLD, block/3)); //sets font
    	for(int q = 0; q < 10; q++)
    	{
    		g.drawString(String.valueOf((char)(q+65)), block/2-block/9, block*(q+1) + block/2+block/9); //draws in the letters
    		g.drawString(String.valueOf((char)(q+65)), block/2-block/9 + block*12, block*(q+1) + block/2+block/9);
    		
    		g.drawString(String.valueOf(q+1), block*(q+1) + block/2-block/9, block/2+block/9); //draws in the numbers
    		g.drawString(String.valueOf(q+1), block*(q+1) + block/2-block/9 + block*12, block/2+block/9);
    	}
    	
    	g.setColor(Color.BLUE);  //draws player tags
		g.drawString("P1", block/2-block/8, block/2+block/9);
		
		g.setColor(Color.ORANGE);
		g.drawString("P2", block/2-block/8 + block*12, block/2+block/9);
		
		for(int i=0;i<10;i++) //draws both boards
		{  
			for(int k=0;k<10;k++)
			{
				g.setColor(player1[i][k]);
				g.fillRect(block*(k+1)+1, block*(i+1)+1, block-1,block-1);
				
				g.setColor(player2[i][k]);
				g.fillRect(block*(k+13)+1, block*(i+1)+1, block-1,block-1);
			}
		}
    }
    
    public static void fillArrays()
    {
    	for(int zzz=0;zzz<10;zzz++) //fills all boards with water
    	{ 
			Arrays.fill(player1[zzz], sea);
			Arrays.fill(player2[zzz], sea);
			
			Arrays.fill(targets1[zzz], sea);
			Arrays.fill(targets2[zzz], sea);
		}
    }
    
    public static void setup() //makes the ship array for setup
    { 
    	boat = new Ship(3);
    	for(int k=2;k<=5;k++)
    	{
    		allBoats.add(new Ship(k));
    	}		
    }
    
    public static void swap() //swaps player turn
    { 
		if(player == 1) 
		{
			player = 2;
		}
		else if(player == 2) 
		{
			player = 1;
		}
    }
    
    public static Color getHit(int row, int col) //returns red if a hit,white if otherwise
    { 
    	if(player == 1)
    	{
    		if(player2[row][col].equals(Color.GRAY))
    		{
    			return Color.RED;
    		}
    	} 
    	else if(player == 2)
    	{
    		if(player1[row][col].equals(Color.GRAY))
    		{
    			return Color.RED;
    		}
    	}
    	return Color.WHITE;
    }
    
    public static boolean victory()  //used for win condition
    { 
    	if(player == 1) 
    	{
    		if(p1hits >= 17) 
    		{
    			return true;
    		}		
    	} 
    		
    	else 
    	{
    		if(p2hits >= 17) 
    		{
    			return true;
    		}		
    	}
    	
    	return false;
    }
}

