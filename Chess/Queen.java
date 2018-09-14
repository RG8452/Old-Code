/*
 *Ryan Gahagan & Mark Tressler
 *This is the queen piece
 *The movement method is identical to the rook and bishop movement methods simultaneously
 */
 
import java.awt.Color;

public class Queen extends Piece 
{
	public Queen() 
	{
		uni = "\u2655";
		value = 9;
	}	
		
	public Queen(int r, int c)
	{
		super(r, c);
		value = 9;
		uni = "\u2655";
	}
	
	public Queen(int r, int c, int t)
	{
		super(r,c,t);
		value = 9;
		uni = "\u2655";
	}
	
	public void move()
	{
//		System.out.println("Queen of team " + team + " at location " + row + "," + col + " moved.");
		
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
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tRow > 0 && !stop)  //up
		{
			if(Chess.board[tRow-1][tCol].getTeam() == 3  || Chess.board[tRow-1][tCol] instanceof EnPassPawn)  //if next up spot is empty
			{
				Chess.boardColors[tRow-1][tCol] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow-1][tCol].getTeam() != team)  //elif next up spot is enemy
			{
				Chess.boardColors[tRow-1][tCol] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow-1][tCol].getTeam() == team) //elif next up is ally
			{
				stop = true; //stop 
			}
			
			tRow--;  //increment U
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tRow < 7 && !stop)  //down
		{
			if(Chess.board[tRow+1][tCol].getTeam() == 3  || Chess.board[tRow+1][tCol] instanceof EnPassPawn)  //if next down spot is empty
			{
				Chess.boardColors[tRow+1][tCol] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow+1][tCol].getTeam() != team)  //elif next down spot is enemy
			{
				Chess.boardColors[tRow+1][tCol] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow+1][tCol].getTeam() == team) //elif next down is ally
			{
				stop = true; //stop 
			}
			
			tRow++;  //increment U
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tCol > 0 && !stop)  //left
		{
			if(Chess.board[tRow][tCol-1].getTeam() == 3  || Chess.board[tRow][tCol-1] instanceof EnPassPawn)  //if next left spot is empty
			{
				Chess.boardColors[tRow][tCol-1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow][tCol-1].getTeam() != team)  //elif next left spot is enemy
			{
				Chess.boardColors[tRow][tCol-1] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow][tCol-1].getTeam() == team) //elif next left is ally
			{
				stop = true; //stop 
			}
			
			tCol--;  //increment U
		}
		
		
		tRow = row;  //reset checking variables
		tCol = col;
		stop = false;
		
		while(tCol < 7 && !stop)  //right
		{
			if(Chess.board[tRow][tCol+1].getTeam() == 3  || Chess.board[tRow][tCol+1] instanceof EnPassPawn)  //if next right spot is empty
			{
				Chess.boardColors[tRow][tCol+1] = Color.YELLOW;  //make it yellow
			}
			
			else if(Chess.board[tRow][tCol+1].getTeam() != team)  //elif next right spot is enemy
			{
				Chess.boardColors[tRow][tCol+1] = Color.RED;  //make it red
				stop = true;  //stop 
			}
			
			else if(Chess.board[tRow][tCol+1].getTeam() == team) //elif next right is ally
			{
				stop = true; //stop 
			}
			
			tCol++;  //increment U
		}
	}
	
	public String name()
	{
		if(team == 1)
		{
			return "White Queen";	
		}
		else if(team == 2)
		{
			return "Black Queen";	
		}
		
		return "ERROR FINDING PIECE NAME";
	}
}
