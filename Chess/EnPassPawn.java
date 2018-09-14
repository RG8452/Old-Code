/*
 *Ryan Gahagan & Mark Tressler
 *This piece serves as a placeholder for when you jump a pawn forward initially.
 *If taken by another pawn, this piece will remove the pawn that jumped it.
 *Otherwise, it acts as an Empty.
 *It should be noted that the "Empty" handling is not done by team, but by instanceof.
 *instanceof refers to when an object is an instance of another class and is used to exclude this piece from targeting.
 */
 
public class EnPassPawn extends Piece 
{
	public int turnNumber;  //used for counting how long this piece is alive, as it only gets one turn
	
	public EnPassPawn() 
	{
		uni = "";
		turnNumber = 0;
		value = 1;
	}	
		
	public EnPassPawn(int r, int c)
	{
		super(r, c);
		uni = "";
		turnNumber = 0;
		value = 1;
	}
	public EnPassPawn(int r, int c, int t, int n) 
	{
		super(r,c,t);
		uni = "";
		turnNumber = n;
		value = 1;
	}	

	public void move() 
	{
		//Do nothing
	}
	
	public void pass() //destroys old pieces
	{
		if(Chess.turnNum > turnNumber+1) 
		{
			Chess.board[row][col] = new Empty(row,col);
		}
	}

	public String name() 
	{
		if(team == 1)
		{
			return "White EnPassPawn";	
		}
		else if(team == 2)
		{
			return "Black EnPassPawn";
		}
		
		return null;
	}
}
