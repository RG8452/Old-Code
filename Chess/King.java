/*
 *Ryan Gahagan & Mark Tressler
 *This is the king piece
 *It handles all movement methods for the king
 *It is also partially responsible for castling, which can be seen in Rook
 */
 
import java.awt.Color;

public class King extends Piece
{
	public King() 
	{
		uni = "\u2654";
	}	
		
	public King(int r, int c)
	{
		super(r, c);
		uni = "\u2654";
	}
	
	public King(int r, int c, int t)
	{
		super(r,c,t);
		uni = "\u2654";
	}
	
	public void move()
	{
		System.out.println("King of team " + team + " at location " + row + "," + col + " moved.");
		
		//checks each spot in turn for availability
		if(row>0)  //upper three
		{
			if(Chess.board[row-1][col].getTeam() == 3  || Chess.board[row-1][col] instanceof EnPassPawn)  //straight up emtpy
			{
				Chess.boardColors[row-1][col] = Color.YELLOW;
			}
			
			else if(Chess.board[row-1][col].getTeam() != team)  //straight up enemy
			{
				Chess.boardColors[row-1][col] = Color.RED;
			}
			
			//if all other conditions are false, the spot must be an ally so nothing happens
			
			if(col>0)  //bounds UL
			{
				if(Chess.board[row-1][col-1].getTeam() == 3  || Chess.board[row-1][col-1] instanceof EnPassPawn)  //UL empty
				{
					Chess.boardColors[row-1][col-1] = Color.YELLOW;
				}
				
				else if(Chess.board[row-1][col-1].getTeam() != team)  //UL enemy
				{
					Chess.boardColors[row-1][col-1] = Color.RED;
				}
			}
			
			if(col<7)  //bounds UR
			{
				if(Chess.board[row-1][col+1].getTeam() == 3  || Chess.board[row-1][col+1] instanceof EnPassPawn)  //UR empty
				{
					Chess.boardColors[row-1][col+1] = Color.YELLOW;
				}
				
				else if(Chess.board[row-1][col+1].getTeam() != team)  //UR enemy
				{
					Chess.boardColors[row-1][col+1] = Color.RED;
				}
			}
		}
		
		if(col > 0)  //straight L
		{
			if(Chess.board[row][col-1].getTeam() == 3  || Chess.board[row][col-1] instanceof EnPassPawn)  //L empty
			{
				Chess.boardColors[row][col-1] = Color.YELLOW;
			}
			
			else if(Chess.board[row][col-1].getTeam() != team)  //L enemy
			{
				Chess.boardColors[row][col-1] = Color.RED;
			}
		}
		
		if(col < 7)  //straight R
		{
			if(Chess.board[row][col+1].getTeam() == 3  || Chess.board[row][col+1] instanceof EnPassPawn)  //R empty
			{
				Chess.boardColors[row][col+1] = Color.YELLOW;
			}
			
			else if(Chess.board[row][col+1].getTeam() != team)  //R enemy
			{
				Chess.boardColors[row][col+1] = Color.RED;
			}
		}
		
		if(row<7)  //lower three
		{
			if(Chess.board[row+1][col].getTeam() == 3  || Chess.board[row+1][col] instanceof EnPassPawn)  //straight down empty
			{
				Chess.boardColors[row+1][col] = Color.YELLOW;
			}
			
			else if(Chess.board[row+1][col].getTeam() != team)  //D enemy
			{
				Chess.boardColors[row+1][col] = Color.RED;
			}
			
			if(col>0)  //bounds DL
			{
				if(Chess.board[row+1][col-1].getTeam() == 3  || Chess.board[row+1][col-1] instanceof EnPassPawn)  //DL empty
				{
					Chess.boardColors[row+1][col-1] = Color.YELLOW;
				}
				
				else if(Chess.board[row+1][col-1].getTeam() != team)  //DL enemy
				{
					Chess.boardColors[row+1][col-1] = Color.RED;
				}
			}
			
			if(col<7)  //bounds DR
			{
				if(Chess.board[row+1][col+1].getTeam() == 3  || Chess.board[row+1][col+1] instanceof EnPassPawn)  //DR empty
				{
					Chess.boardColors[row+1][col+1] = Color.YELLOW;
				}
				
				else if(Chess.board[row+1][col+1].getTeam() != team)  //DR enemy
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
			return "White King";	
		}
		else if(team == 2)
		{
			return "Black King";	
		}
		
		return "ERROR FINDING PIECE NAME";

	}
}
