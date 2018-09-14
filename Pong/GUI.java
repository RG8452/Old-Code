/**
 * Ryan Gahagan
 * This class creates and handles the frame/GUI of the Pong game
 * The Pong class handles all the logic
 */
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class GUI extends JFrame
{
	private static JPanel myPanel;  //sets up the panel to act on
	public static Pong game = new Pong();  //handler class used to handle inputs
	public static JFrame jf;  //frame to hold panel
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //retrieves screen dimensions
	
    public GUI()  //default constructor
    {
    	super("PONG"); //creates a GUI Window with title "PONG"
    	
    	myPanel = new JPanel() {  //overridden constructor
    		protected void paintComponent(Graphics g)  //override method
    		{
    			super.paintComponent(g);  
    			
    			Graphics2D g2 = (Graphics2D) g;  //cast to g2d
    			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);  //set rendering hints
    			
    			g2.clearRect(0,0,getWidth(),getHeight());  //clear screen
    			g2.setColor(Color.black);  //set to black
    			g2.fillRect(0,0,getWidth(),getHeight());  //fill screen with black
    			
    			g2.setColor(Pong.background);  //set the color to white (or whatever it is in Pong)
    			
    			Pong.drawBackground(g2);  //draw the background
    		}
    	};

    	myPanel.setBackground(Color.BLACK); //sets color to white
    	add(myPanel, BorderLayout.CENTER); //puts the panel onto the frame
    	
    	myPanel.addKeyListener(game);  //adds a key listener to the panel
    	myPanel.setFocusable(true);  //allows the user to "focus" on the panel
    	setFocusTraversalKeysEnabled(true);	
    }
    
    public static void main(String[] args) 
    {
        jf = new GUI();  //instantiates the frame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the x
        
		//sets screen size to fullscreen, excluding desktop bar
 		jf.setSize(screenSize.width, screenSize.height - 50);
        jf.setVisible(true);  //makes the frame visible
        
        Pong.play();
    }
    
	//repaints the panel containing the pong game
    public void paint(Graphics g)
    {
    	myPanel.repaint();
    }
    
    //Uses JOptionPanes to retrieve an integer
    public static int EnterIntGUI(String prompt)
    {
    	String tempString = JOptionPane.showInputDialog(prompt);
    	int temp = Integer.parseInt(tempString);
    	return temp;
    }
    
    //Allows access to the panel if necessary
    public static JPanel getMyPanel(){return myPanel;}
}