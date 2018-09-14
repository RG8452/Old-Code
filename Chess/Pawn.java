/*
 *Ryan Gahagan & Mark Tressler
 *This is the pawn class, handling all pawn methods
 *Used for all movement and capturing logic
 */
 
import java.awt.Color;

public class Pawn extends Piece 
{
	public Pawn() 
	{
		uni = "\u2659";
		value = 1;
	}	
		
	public Pawn(int r, int c)
	{
		super(r, c);
		value = 1;
		uni = "\u2659";
	}
	
	public Pawn(int r, int c, int t)
	{
		super(r,c,t);
		uni = "\u2659";
		value = 1;
	}
	
	public void move()
	{
//		System.out.println("Pawn of team " + team + " at location " + row + "," + col + " moved.");
	
		if(team == 1 && row != 0)  //WHITE PAWN
		{			
			Piece p = Chess.board[row-1][col];
				
			if(Chess.board[row-1][col].getTeam() == 3)  //FORWARD 1 if space == EMPTY
			{
				Chess.boardColors[row-1][col] = Color.YELLOW;
			}
			
			if(row == 6 && Chess.board[row-2][col].getTeam() == 3 && Chess.board[row-1][col].getTeam() == 3)  //FORWARD 2 on turn 1
			{
				Chess.boardColors[row-2][col] = Color.YELLOW;
			}
			
			if(col!=0)  //bounds leftmost
			{
				if(Chess.board[row-1][col-1].getTeam() == 2) //UP-LEFT if enemy is there
				{
					Chess.boardColors[row-1][col-1] = Color.RED;
				}
			}
			
			if(col!=7)  //bounds rightmost
			{
				if(Chess.board[row-1][col+1].getTeam() == 2) //UP-RIGHT if enemy is there
				{
					Chess.boardColors[row-1][col+1] = Color.RED;
				}
			}
		}
		
		else if(team == 2 && row != 7)  //BLACK PAWN
		{
			if(Chess.board[row+1][col].getTeam() == 3)  //FORWARD 1 if space == EMPTY
			{
				Chess.boardColors[row+1][col] = Color.YELLOW;
			}
			
			if(row == 1 && Chess.board[row+2][col].getTeam() == 3 && Chess.board[row+1][col].getTeam() == 3)  //FORWARD 2 on turn 1
			{
				Chess.boardColors[row+2][col] = Color.YELLOW;
			}
			
			if(col!=0)  //bounds leftmost
			{
				if(Chess.board[row+1][col-1].getTeam() == 1) //DOWN-LEFT if enemy is there
				{
					Chess.boardColors[row+1][col-1] = Color.RED;
				}
			}
			
			if(col!=7)  //bounds rightmost
			{
				if(Chess.board[row+1][col+1].getTeam() == 1) //DOWN-RIGHT if enemy is there
				{
					Chess.boardColors[row+1][col+1] = Color.RED;
				}
			}	
		}
	}
	
	public String name()
	{
		if(team == 1)
		{
			return "White Pawn";	
		}
		else if(team == 2)
		{
			return "Black Pawn";	
		}
		
		return "ERROR FINDING PIECE NAME";
	}	
}
