/*
 *Ryan Gahagan & Mark Tressler
 *This code creates a frame with a drop down menu.
 *It will be used when the user gets a pawn to the edge of the screen
 *and will allow for the exchange of pieces.
 *
 *As of right now I totally botched this but it could probably be fixed eventually
 */
 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class PawnSwap extends JFrame implements ActionListener
{
	String[] choices = {"Queen", "Knight", "Bishop", "Rook"};  //list of choices
	JComboBox cmb = new JComboBox(choices);  //drop down menu
	JLabel lbl = new JLabel();  //test label
	JButton btn = new JButton("OK");  //OK button
	public static Piece pieceChoice;  //currently chosen piece
	public static boolean chosen = false;  //true only when the piece has been chosen
	
	public PawnSwap() 
	{
		setLayout(new GridLayout(3, 1));  //sets everything in a line
		setSize(500, 200);  //size
		setTitle("Pawn Swap");  //title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lbl.setText("Please choose a piece to replace your pawn.");  //changes the text
		
		cmb.setSelectedIndex(-1);  //initially display an empty drop down box
		cmb.addActionListener(this);  //add an action listener
		add(lbl);  //put all three components in the frame
		add(cmb);
		add(btn);
	}
	
	public void actionPerformed(ActionEvent ae)  //when the user does something
	{
		if(ae.getSource() == cmb)  //if they've changed the drop down box
		{
			JComboBox cb = (JComboBox)ae.getSource();  //gets the box choices
			String msg = (String)cb.getSelectedItem();  //gets the chosen string
			switch(msg)  //checks choice
			{
				case "Queen":  //if they chose queen
					pieceChoice = new Queen(Chess.lastRow, Chess.lastCol, Chess.playingTeam); //make the chosen piece a queen
					lbl.setText("You have selected a Queen.");  //inform them of their choice
					break;
				case "Knight":  //if they chose knight
					pieceChoice = new Knight(Chess.lastRow, Chess.lastCol, Chess.playingTeam);  //same as above
					lbl.setText("You have selected a Knight.");
					break;
				case "Bishop":  //if they chose bishop
					pieceChoice = new Bishop(Chess.lastRow, Chess.lastCol, Chess.playingTeam);
					lbl.setText("You have selected a Bishop.");
					break;
				case "Rook":  //if they chose rook
					pieceChoice = new Rook(Chess.lastRow, Chess.lastCol, Chess.playingTeam);
					lbl.setText("You have selected a Rook.");
					break;
				default:  //error handling
					System.out.println("Oh no");
					lbl.setText("You broke it D:");
			}
		}
		
		if(ae.getSource() == btn)  //if they press the button (questionable working)
		{
			chosen = true;  //choice is made
			setVisible(false);  //make this window invisible
			dispose();  //eat the window
		}	
	}
}
