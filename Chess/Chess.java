/*
 *Ryan Gahagan & Mark Tressler
 *This is the Chess class
 *It handles all of the logic based interactions
 *It also implements the listening methods
 */

//IMPORTS
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Chess implements MouseListener,
    						  KeyListener, 
    						  ActionListener
{
	public static int block = GUI.block; //retrieves block size from frameTest class
	static int lastCol = 10; //last column clicked
	static int lastRow = 10; //last row clicked
	static Color brown = new Color(85, 59, 22);
	static Color tan = new Color(189, 147, 86);
	static Piece[][] board = new Piece[8][8]; //array for board pieces
	static Color[][] boardColors = new Color[8][8]; //color array
	static boolean select = false; //true if a piece has been selected
	static int playingTeam = 1;	//controls who's turn it is (1 = white, 2 = black)
	static int turnNum = 0;	//counts the total number of turns
	static boolean end = false; //if true, game is over
	static int victor = 0; //used to control winning text. If white wins, == 1; if black wins, == 2
	static int whiteScore = 0;	//holds score for white player
	static int blackScore = 0;	//holds score for black player
	
	//Unicode characters for chess pieces
	static String kingUni = "\u2654";
	static String queenUni = "\u2655";
	static String rookUni = "\u2656";
	static String bishopUni = "\u2657";
	static String knightUni = "\u2658";
	static String pawnUni = "\u2659";
	
	//Psuedo-main method for logic
	public static void play(Graphics g)
	{
		GUI.getMyPanel().repaint();		//repaint the screen
	}
	
	
	//Unused event and keylistener methods
//actionListener methods
	public void actionPerformed(ActionEvent e){}
//keyListener methods
	public void keyPressed(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	
	public void keyTyped(KeyEvent e)	//k
	{
		char c = e.getKeyChar();
		System.out.println("You typed " + c);
	}   
	/////////////////////////////////////////////		
			
	//mouseListener methods
	public void mouseClicked(MouseEvent e)
	{
		int tempCol = lastCol;  //stores last location for deselect
		int tempRow = lastRow;

//		System.out.println(String.format("Clicked at %d, %d", e.getX(), e.getY())); //prints coords
		
		lastCol = e.getX()/block; //gets grid coords of click
		lastRow = e.getY()/block;
		
//		System.out.println(board[lastRow][lastCol].name());  //prints name of piece clicked
		
		if(!end)	//If game is over, stop the game
		{
			moveLogic(tempRow, tempCol);	//Logic for movement of all pieces
		}
		
		GUI.getMyPanel().repaint();  //repaint
	}
	
	public void mousePressed(MouseEvent e)
	{
//		System.out.println("You pressed the mouse.");
	}
	
	public void mouseReleased(MouseEvent e)
	{
//		System.out.println("You released the mouse.");
	}	
		
	public void mouseEntered(MouseEvent e){}
	
	public void mouseExited(MouseEvent e){}	
		
		
	public static void moveLogic(int tRow, int tCol)
	{		
		if(boardColors[lastRow][lastCol].equals(Color.GREEN))  //if you click a selected piece
		{
			select = false;	//Unselects piece
			fillColors();	//Resets color array
		}
		
		else if(boardColors[lastRow][lastCol].equals(Color.YELLOW))  //if you click an empty move spot
		{	
			board[lastRow][lastCol] = board[tRow][tCol]; //swaps clicked with old	
			
			if(board[tRow][tCol].name().equals("White Pawn") && tRow == 6 && lastRow == 4) //if you jumped a white
			{
				board[tRow-1][tCol] = new EnPassPawn(tRow-1, tCol, 1, turnNum);  //create an enPassPawn
			}	
				
			else if(board[tRow][tCol].name().equals("Black Pawn") && tRow == 1 && lastRow == 3)  //elif you jump a black
			{
				board[tRow+1][tCol] = new EnPassPawn(tRow+1, tCol, 2, turnNum);  //create an enPassPawn
			}

			board[tRow][tCol] = new Empty(tRow, tCol);  //puts empty in old
			
			board[lastRow][lastCol].setCoords(lastRow, lastCol);  //sets piece coords to match
			
			fillColors();  //refills board
			
			swapPlayers();  //swap players
			
			enPassLogic();  //deletes unnecessary enPasses
		}
		
		else if(boardColors[lastRow][lastCol].equals(Color.RED))   //if you click a capture spot
		{
			if(board[lastRow][lastCol] instanceof EnPassPawn && board[tRow][tCol] instanceof Pawn) //if you moved a pawn and took enPass
			{
				board[tRow][lastCol] = new Empty(tRow, lastCol); //replace the enemy pawn with empty
			}
			
			Piece victory = board[lastRow][lastCol];	//Holds the piece that was taken (used for determining if king was lost)
			
			board[lastRow][lastCol] = board[tRow][tCol]; //swaps enemy with player
			board[tRow][tCol] = new Empty(tRow, tCol);  //puts empty in old
			
			board[lastRow][lastCol].setCoords(lastRow, lastCol);  //sets piece coords to match
			
			fillColors();  //refills board
			
			swapPlayers();  //swap players
			
			enPassLogic();  //removes old enPasses
			
			if(victory instanceof King)	//If a king is captured
			{
				if(victory.getTeam() == 2)
				{
					//WHITE WINS
					end = true;
					victor = 1;
					GUI.getMyPanel().repaint();
					
				} 
				else if(victory.getTeam() == 1)
				{
					//BLACK WINS
					end = true;
					victor = 2;
					GUI.getMyPanel().repaint();
				}
				else
				{
					System.out.println("No one is a winner.");		//Error message
				}
			}
			else	//Used for adding score if non-king is captured
			{
				if(victory.getTeam() == 2)	//white captures a piece
				{
					whiteScore += victory.getScore();
					
				} 
				else if(victory.getTeam() == 1) //white captures a piece
				{
					blackScore += victory.getScore();
				}
				else
				{
					System.out.println("Error in score collection.");		//Error message
				}
			}
		}
		
		else  //if you click anything else
		{
			if(board[lastRow][lastCol].getTeam() == 3)  //if you click an empty space
			{
			}
			
			else if(board[lastRow][lastCol].getTeam() == playingTeam) //if you click another piece of your team
			{
				fillColors();
				boardColors[lastRow][lastCol] = Color.GREEN;
				board[lastRow][lastCol].move();
				drawGrid(GUI.getMyPanel().getGraphics());
			}
		}
		
		if(lastRow == 0 && board[lastRow][lastCol].name().equals("White Pawn")) //checks white pawn at end
		{
			board[lastRow][lastCol] = swapPawn();  //swaps the pawn with user's choice
		}
		
		if(lastRow == 7 && board[lastRow][lastCol].name().equals("Black Pawn")) //checks black pawn at end
		{
			board[lastRow][lastCol] = swapPawn();  //swaps the pawn with the user's name
		}
	}
	
	public static void enPassLogic()  //loops through all possible enPassPawns and erases old ones
	{
		for(int passerCol = 0; passerCol <= 7; passerCol++) //loop across rows
		{
			//if there is a white enPassPawn on the cur col
			if(board[5][passerCol].name().equals("White EnPassPawn")) 
			{
				//store the needed enPassPawn as tempThing
				Piece tempThing = board[5][passerCol];
				EnPassPawn tempTwo = (EnPassPawn) tempThing;  //downcast tempThing in order to access the pass() method
				tempTwo.pass();  //erase the piece if necessary
			}
			
			//if there is a black enPassPawn on the cur col
			if(board[2][passerCol].name().equals("Black EnPassPawn"))
			{
				//store the needed enPassPawn as tempThing
				Piece tempThing = board[2][passerCol];
				EnPassPawn tempTwo = (EnPassPawn) tempThing;  //downcast tempThing in order to access the pass() method
				tempTwo.pass();  //erase the piece if necessary
			}
		}	
	}
		
	public static void swapPlayers()  //swaps players
	{
		//this crazy line sets to 2 if the boolean is true and 1 if it's false
		playingTeam = (playingTeam == 1) ? 2 : 1;
		GUI.lbl.setText((playingTeam == 1) ? "Player: White" : "Player: Black");
		turnNum ++;
	}	
		
	public static Piece swapPawn()  //uses the drop down menu for the pawn
	{
/*		 fr = new PawnSwap();  //broken test code
		
		fr.setVisible(true);
		
		return PawnSwap.pieceChoice; */
		
		String temp = EnterStringGUI("Which piece would you like to swap for your pawn? (Queen, Knight, Rook, Bishop)");  //gets piece choice
		switch(temp)  //checks and instantiates choice
		{
			case "Queen":  //if the user chose a queen
    			return new Queen(lastRow, lastCol, (playingTeam == 1) ? 2 : 1);  //make a queen of the user's team
    		case "Knight": //same as queen, but with a knight
    			return new Knight(lastRow, lastCol, (playingTeam == 1) ? 2 : 1);
    		case "Bishop": //etc
    			return new Bishop(lastRow, lastCol, (playingTeam == 1) ? 2 : 1);
    		case "Rook":
    			return new Rook(lastRow, lastCol, (playingTeam == 1) ? 2 : 1);
    		default:
    			System.out.println("tragic");
    			return null;
		}
	}
	
	public static void infoBox(String infoMessage, String titleBar) //used to pop up messages
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
	
	public static String EnterStringGUI(String prompt) //retrives a string
    { 
    	String ans = JOptionPane.showInputDialog(prompt);  //gets the answer
    	switch(ans) //checks the answer to be a viable piece
    	{
    		case "Queen":  
    			return ans;
    		case "Knight":
    			return ans;
    		case "Bishop":
    			return ans;
    		case "Rook":
    			return ans;
    		default:
    			return EnterStringGUI("Please use a valid piece (Queen, Knight, Rook, Bishop)");  //force choice
    	}
    }
	
	//draw methods
	public static void drawBackground(Graphics g)
	{		
		//this nested loop fills in all the colors
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				g.setColor(boardColors[row][col]);  //gets the color from boardColors
				g.fillRect(block*col, block*row, block, block);  //draws each box
			}
		}
		
		drawGrid(g);
	}
	
	public static void drawGrid(Graphics g)
	{
		g.setColor(Color.BLACK);
		for(int q=0; q<9; q++)  //draws in a grid
		{
			g.drawLine(block*q, 0, block*q, block*8);  //horizontal lines
			g.drawLine(0, block*q, block*8, block*q);  //vertical lines
		}
	}
	
	public static void drawPieces(Graphics g)
	{
		g.setFont(new Font("TimesRoman", Font.BOLD, block)); //sets font
		
		for(int row = 0; row < 8; row++) //loops through rows
		{
			for(int col = 0; col < 8; col++)  //loops through columns
			{
				Piece pp = board[row][col];  //gets piece at this location
				
				if(pp.getTeam() == 1) 
				{
					g.setColor(Color.white);  //white if p1
				}
				
				else if(pp.getTeam() == 2)
				{
					g.setColor(Color.black);  //black if p2
				}
				
				g.drawString(pp.getUni(), block*pp.getCol(), block*pp.getRow() + block*4/5); //draws unicode character in current box
			}
		}
	}	
		
	public static void fill() //fills board with empty spaces initially
	{
		fillColors();  //sets boardColors to default
		
		for(int row = 0; row < 8; row++)  //loop rows
		{
			for(int col = 0; col < 8; col++)  //loop columns
			{
				board[row][col] = new Empty(row, col);  //fill the board with Empty objects
			}
		}
	}	
		
	public static void fillColors() //fills boardColors
	{
		//this nested loop fills in all the colors
		for(int row = 0; row < 8; row++)
		{
			for(int col = 0; col < 8; col++)
			{
				if((row+col)%2==0) //fills in the dark browns on the "even" spaces
				{
					boardColors[row][col] = brown;
				}
				else //fills all other spaces with tan
				{
					boardColors[row][col] = tan;
				}
			}
		}
	} 
		
	public static void setup() //sets out all player pieces
	{
		Chess.fill(); //fill board with empty
		
		//KINGS
		board[7][3] = new King(7,3,1); //white
		board[0][3] = new King(0,3,2); //black
		
		//QUEENS
		board[7][4] = new Queen(7,4,1); //white
		board[0][4] = new Queen(0,4,2); //black
		
		//KIGHTS
		board[7][6] = new Knight(7,6,1); //white
		board[7][1] = new Knight(7,1,1);
		
		board[0][6] = new Knight(0,6,2); //black
		board[0][1] = new Knight(0,1,2);
		
		//BISHOPS
		board[7][2] = new Bishop(7,2,1); //white
		board[7][5] = new Bishop(7,5,1);
		
		board[0][2] = new Bishop(0,2,2); //black
		board[0][5] = new Bishop(0,5,2);
		
		//ROOKS
		board[7][0] = new Rook(7,0,1); //white
		board[7][7] = new Rook(7,7,1);
		
		board[0][0] = new Rook(0,0,2); //black
		board[0][7] = new Rook(0,7,2);
			
		
		//PAWNS
		for(int q=0; q<8; q++)	
		{
			board[1][q] = new Pawn(1,q,2);  //black
			board[6][q] = new Pawn(6,q,1);	//white
		}
	} 	
}