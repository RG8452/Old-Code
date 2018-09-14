/*
 *Ryan Gahagan & Mark Tressler
 *This is the knight class, handling all knight methods
 *Used for all movement and capturing logic
 */
 
import java.awt.Color;

public class Knight extends Piece 
{
	public Knight() 
	{
		uni = "\u2658";
		value = 3;
	}	
		
	public Knight(int r, int c)
	{
		super(r, c);
		value = 3;
		uni = "\u2658";
	}
	
	public Knight(int r, int c, int t)
	{
		super(r,c,t);
		value = 3;
		uni = "\u2658";
	}
	
	public void move()
	{
//		System.out.println("Knight of team " + team + " at location " + row + "," + col + " moved.");
		
		//The way the knight will move is by checking bounds and then checking spots
		//It starts with the highest, leftmost spot and then goes right and down
		if(row > 1)
		{
			if(col > 0) //UUL
			{
				if(Chess.board[row-2][col-1].getTeam() == 3  || Chess.board[row-2][col-1] instanceof EnPassPawn)  //if UUL is empty
				{
					Chess.boardColors[row-2][col-1] = Color.YELLOW;
				}
				
				else if(Chess.board[row-2][col-1].getTeam() != team)  //elif UUL is enemy
				{
					Chess.boardColors[row-2][col-1] = Color.RED;
				}
				
				//in all of these statements, there is no need for handling of an ally
			}
			
			if(col < 7) //UUR
			{
				if(Chess.board[row-2][col+1].getTeam() == 3  || Chess.board[row-2][col+1] instanceof EnPassPawn)  //if UUR is emptty
				{
					Chess.boardColors[row-2][col+1] = Color.YELLOW;
				}
				
				else if(Chess.board[row-2][col+1].getTeam() != team)  //elif UUR is empty
				{
					Chess.boardColors[row-2][col+1] = Color.RED;
				}
			}
		}
		
		if(row > 0)
		{
			if(col > 1) //ULL
			{
				if(Chess.board[row-1][col-2].getTeam() == 3  || Chess.board[row-1][col-2] instanceof EnPassPawn)  //if ULL is empty
				{
					Chess.boardColors[row-1][col-2] = Color.YELLOW;
				}
				
				else if(Chess.board[row-1][col-2].getTeam() != team)  //elif ULL is enemy
				{
					Chess.boardColors[row-1][col-2] = Color.RED;
				}
			}
			
			if(col < 6) //URR
			{
				if(Chess.board[row-1][col+2].getTeam() == 3  || Chess.board[row-1][col+2] instanceof EnPassPawn)  //if URR is emptty
				{
					Chess.boardColors[row-1][col+2] = Color.YELLOW;
				}
				
				else if(Chess.board[row-1][col+2].getTeam() != team)  //elif URR is empty
				{
					Chess.boardColors[row-1][col+2] = Color.RED;
				}
			}
		}
		
		if(row < 7)
		{
			if(col > 1) //DLL
			{
				if(Chess.board[row+1][col-2].getTeam() == 3  || Chess.board[row+1][col-2] instanceof EnPassPawn)  //if DLL is empty
				{
					Chess.boardColors[row+1][col-2] = Color.YELLOW;
				}
				
				else if(Chess.board[row+1][col-2].getTeam() != team)  //elif DLL is enemy
				{
					Chess.boardColors[row+1][col-2] = Color.RED;
				}
			}
			
			if(col < 6) //URR
			{
				if(Chess.board[row+1][col+2].getTeam() == 3  || Chess.board[row+1][col+2] instanceof EnPassPawn)  //if DRR is emptty
				{
					Chess.boardColors[row+1][col+2] = Color.YELLOW;
				}
				
				else if(Chess.board[row+1][col+2].getTeam() != team)  //elif URR is empty
				{
					Chess.boardColors[row+1][col+2] = Color.RED;
				}
			}
		}
		
		if(row < 6)
		{
			if(col > 0) //DDL
			{
				if(Chess.board[row+2][col-1].getTeam() == 3  || Chess.board[row+2][col-1] instanceof EnPassPawn)  //if DDL is empty
				{
					Chess.boardColors[row+2][col-1] = Color.YELLOW;
				}
				
				else if(Chess.board[row+2][col-1].getTeam() != team)  //elif DDL is enemy
				{
					Chess.boardColors[row+2][col-1] = Color.RED;
				}
			}
			
			if(col < 7) //DDR
			{
				if(Chess.board[row+2][col+1].getTeam() == 3  || Chess.board[row+2][col+1] instanceof EnPassPawn)  //if DDR is emptty
				{
					Chess.boardColors[row+2][col+1] = Color.YELLOW;
				}
				
				else if(Chess.board[row+2][col+1].getTeam() != team)  //elif DDR is empty
				{
					Chess.boardColors[row+2][col+1] = Color.RED;
				}
			}
		}
	}
	
	public String name()
	{
		if(team == 1)
		{
			return "White Knight";	
		}
		else if(team == 2)
		{
			return "Black Knight";	
		}
		
		return "ERROR FINDING PIECE NAME";
	}
}
