/*
 *Ryan Gahagan & Mark Tressler
 *This class is responsible for setting up the JFrame and JPanels
 *It handles all window interactions.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class GUI extends JFrame
{
	private static JPanel myPanel; //panel on which the game is displayed
	public static int block = EnterIntGUI("How many pixels do you want in each box?");
	public static Chess game = new Chess();  //handler class used to handle inputs
	public static JFrame jf; //frame which holds the panel
	
	public static JLabel lbl;  //label for turn
	
	public GUI() 
    {
    	super("Chess"); //creates a GUI Window with title "Title"
    	
    	/*The next few lines instantiate the JPanel
    	 *They also redeclare the paint method of the panel
    	 *Panels are components of frames, so you can exclusively repaint the panel.
    	 */
    	myPanel = new JPanel() {
    		protected void paintComponent(Graphics g)
    		{
    			super.paintComponent(g);
    			if(!Chess.end)
    			{
    				//the reason these methods are called in here is to reduce flashing and lag	
    				g.clearRect(0,0,getWidth(),getHeight());
    				Chess.drawBackground(g);

					Chess.drawPieces(g);
    			}
				else if(Chess.end)
				{
					g.clearRect(0,0,getWidth(),getHeight());
    				Chess.drawBackground(g);
					Chess.drawPieces(g);
					
					g.setFont(new Font("TimesNewRoman", Font.BOLD, 72));
					if(Chess.victor == 1)
					{
						g.setColor(Color.BLACK);
						g.fillRect((int)(block*1.6),(int)(block*1.2),(int)(block*5.3),(int)(block*2.4));
						g.setColor(Color.WHITE);
						g.drawString("White wins!",(int)(block*1.8),(int)(block*2));
						g.drawString("Score: " + Chess.whiteScore,(int)(block*2.2),(int)(block*3));
					}
					else if(Chess.victor == 2)
					{
						g.setColor(Color.WHITE);
						g.fillRect((int)(block*1.6),(int)(block*1.2),(int)(block*5.3),(int)(block*2.4));
						g.setColor(Color.BLACK);
						g.drawString("Black wins!",(int)(block*1.8),(int)(block*2));
						g.drawString("Score: " + Chess.blackScore,(int)(block*2.2),(int)(block*3));
					}
					else
					{
						System.out.println("Fatal error");
					}	
				}
				else
				{
					System.out.println("Error finding game state");
				}
    		}
    	};

    	myPanel.setBackground(Color.WHITE); //sets color to white
    	add(myPanel, BorderLayout.CENTER); //puts the panel onto the frame
    	
    	myPanel.addMouseListener(game);  //adds mouse listener to the panel
    	
    	myPanel.addKeyListener(game);  //adds a key listener to the panel
    	myPanel.setFocusable(true);  //allows the user to "focus" on the panel
    	setFocusTraversalKeysEnabled(true);	
    		
    	lbl = new JLabel("Player: White"); //creates a JLabel
    	add(lbl, BorderLayout.SOUTH);  //puts the JLabel on the bottom
    }
	
	public static void main(String[] args) 
	{
		jf = new GUI();  //instantiates the frame
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //makes the x
        jf.setSize(block*8+15, block*8+60); //sets the size of the frame
        jf.setVisible(true);  //makes the frame visible
        
        Chess.setup();  //creates all pieces
 		Chess.play(myPanel.getGraphics()); //call board logic
	}
	
	/*
     *Graphics g refers to frame graphics, which inclueds the perimeter
     *myPanel.getGraphics() refers only to the screen
     *The latter will be used more often to avoid math.
     */
    public void paint(Graphics g)
    {
    	jf.setFocusable(true);						//Sets the focus to the game screen
    	myPanel.repaint();
    	lbl.repaint();
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
