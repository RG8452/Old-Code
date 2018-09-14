/*
 *Ryan Gahagan & Mark Tressler
 *This is the rook
 *It can move in a + shape and this is where its methods are.
 *There will also be logic for checking the availability of castling.
 *Castling as an option will be drawn in Color.BLUE
 */
 
import java.awt.Color;

public class Rook extends Piece 
{
	public Rook() 
	{
		uni = "\u2656";
		value = 5;
	}	
		
	public Rook(int r, int c)
	{
		super(r, c);
		value = 5;
		uni = "\u2656";
	}
	
	public Rook(int r, int c, int t)
	{
		super(r,c,t);
		uni = "\u2656";
		value = 5;
	}
	
	public void move()
	{
//		System.out.println("Rook of team " + team + " at location " + row + "," + col + " moved.");
		
		int tRow = row;  //checking variables
		int tCol = col;
		boolean stop = false;
		
		while(!stop && tRow >0)  //check up
		{
			if(Chess.board[tRow-1][tCol].getTeam() == 3  || Chess.board[tRow-1][tCol] instanceof EnPassPawn)  //up empty
			{
				Chess.boardColors[tRow-1][tCol] = Color.YELLOW;
			}
			
			else if(Chess.board[tRow-1][tCol].getTeam() != team)  //up empty
			{
				Chess.boardColors[tRow-1][tCol] = Color.RED;  
				stop = true;  //stop on enemy
			}
			
			else  //if the spot is neither an enemy nor empty, it must be an ally
			{
				stop = true;  //stop on ally
			}
			
			tRow--;  //decrement row to go up
		}
		
		
		stop = false;  //reset check variables
		tRow = row;
		
		while(!stop && tRow <7)  //check down
		{
			if(Chess.board[tRow+1][tCol].getTeam() == 3  || Chess.board[tRow+1][tCol] instanceof EnPassPawn)  //all same as above and for below
			{
				Chess.boardColors[tRow+1][tCol] = Color.YELLOW;
			}
			
			else if(Chess.board[tRow+1][tCol].getTeam() != team)
			{
				Chess.boardColors[tRow+1][tCol] = Color.RED;
				stop = true;
			}
			
			else
			{
				stop = true;
			}
			
			tRow++;  //increment row to go down
		}
		
		
		stop = false;  //same as above
		tRow = row;
		
		while(!stop && tCol > 0)  //left
		{
			if(Chess.board[tRow][tCol-1].getTeam() == 3  || Chess.board[tRow][tCol-1] instanceof EnPassPawn)
			{
				Chess.boardColors[tRow][tCol-1] = Color.YELLOW;
			}
			
			else if(Chess.board[tRow][tCol-1].getTeam() != team)
			{
				Chess.boardColors[tRow][tCol-1] = Color.RED;
				stop = true;
			}
			
			else
			{
				stop = true;
			}
			
			tCol--;  //decrement column left
		}
		
		
		stop = false;
		tCol = col;
		
		while(!stop && tCol < 7)  //right
		{
			if(Chess.board[tRow][tCol+1].getTeam() == 3  || Chess.board[tRow][tCol+1] instanceof EnPassPawn)
			{
				Chess.boardColors[tRow][tCol+1] = Color.YELLOW;
			}
			
			else if(Chess.board[tRow][tCol+1].getTeam() != team)
			{
				Chess.boardColors[tRow][tCol+1] = Color.RED;
				stop = true;
			}
			
			else
			{
				stop = true;
			}
			
			tCol++;  //increment col to go right
		}
	}
	
	public String name()
	{
		if(team == 1)
		{
			return "White Rook";	
		}
		else if(team == 2)
		{
			return "Black Rook";	
		}
		
		return "ERROR FINDING PIECE NAME";
	}
}
