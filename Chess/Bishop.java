/*
 *Ryan Gahagan & Mark Tressler
 *This is the bishop class, handling all bishop methods
 *Used for all movement and capturing logic
 */
 
import java.awt.Color;

public class Bishop extends Piece 
{
	public int value = 3;
	
	public Bishop() 
	{
		uni = "\u2657";
	}	
		
	public Bishop(int r, int c)
	{
		super(r, c);
		uni = "\u2657";
	}
	
	public Bishop(int r, int c, int t)
	{
		super(r,c,t);
		uni = "\u2657";
	}
	
	public void move()
	{
//		System.out.println("Bishop of team " + team + " at location " + row + "," + col + " moved.");
		
		int tRow = row;  //set checking variables
		int tCol = col;
		boolean stop = false;
		
		while(tRow > 0 && tCol > 0 && !stop)  //diagonal up and left
		{
			if(Chess.board[tRow-1][tCol-1].getTeam() == 3  || Chess.board[tRow-1][tCol-1] instanceof EnPassPawn)  //if next up and left spot is empty
			{
				Chess.boardColors[tRow-1][tCol-1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow-1][tCol-1].getTeam() != team)  //elif next up and left spot is enemy
			{
				Chess.boardColors[tRow-1][tCol-1] = Color.RED;  //make it red
				stop = true;  //stop going up and left
			}
			
			else if(Chess.board[tRow-1][tCol-1].getTeam() == team) //elif next UL is ally
			{
				stop = true; //stop going up and left
			}
			
			tRow--;  //decrement U
			tCol--;	 //decrement L
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tRow > 0 && tCol < 7 && !stop)  //diagonal up and right
		{
			if(Chess.board[tRow-1][tCol+1].getTeam() == 3  || Chess.board[tRow-1][tCol+1] instanceof EnPassPawn)  //if next UR spot is empty
			{
				Chess.boardColors[tRow-1][tCol+1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow-1][tCol+1].getTeam() != team)  //elif next UR spot is enemy
			{
				Chess.boardColors[tRow-1][tCol+1] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow-1][tCol+1].getTeam() == team) //elif next UR is ally
			{
				stop = true; //stop 
			}
			
			tRow--;  //decrement U
			tCol++;	 //increment L
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tRow < 7 && tCol > 0 && !stop)  //diagonal down and left
		{
			if(Chess.board[tRow+1][tCol-1].getTeam() == 3  || Chess.board[tRow+1][tCol-1] instanceof EnPassPawn)  //if next down and left spot is empty
			{
				Chess.boardColors[tRow+1][tCol-1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow+1][tCol-1].getTeam() != team)  //elif next down and left spot is enemy
			{
				Chess.boardColors[tRow+1][tCol-1] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow+1][tCol-1].getTeam() == team) //elif next DL is ally
			{
				stop = true; //stop 
			}
			
			tRow++;  //increment U
			tCol--;	 //decrement L
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tRow < 7 && tCol < 7 && !stop)  //diagonal down and right
		{
			if(Chess.board[tRow+1][tCol+1].getTeam() == 3  || Chess.board[tRow+1][tCol+1] instanceof EnPassPawn)  //if next down and right spot is empty
			{
				Chess.boardColors[tRow+1][tCol+1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow+1][tCol+1].getTeam() != team)  //elif next down and right spot is enemy
			{
				Chess.boardColors[tRow+1][tCol+1] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow+1][tCol+1].getTeam() == team) //elif next DR is ally
			{
				stop = true; //stop 
			}
			
			tRow++;  //increment U
			tCol++;	 //increment L
		}
	}
	
	public String name()
	{
		if(team == 1)
		{
			return "White Bishop";	
		}
		else if(team == 2)
		{
			return "Black Bishop";	
		}
		
		return "ERROR FINDING PIECE NAME";
	}
}
