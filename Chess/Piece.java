/*
 *Ryan Gahagan & Mark Tressler
 *This is the abstract class which each piece will extend
 *It implements all instance variables and necessary methods
 */
 
abstract class Piece 
{
	protected int row;
	protected int col;
	protected int team;
	protected int value;
	protected String uni;
	
	public Piece()
	{
		row = 0;
		col = 0;
		team = 1;
	}
	
	public Piece(int r, int c)
	{
		row = r;
		col = c;
		team = 1;
	}
	
	public Piece(int r, int c, int t)
	{
		row = r;
		col = c;
		if(t == 1 || t == 2)
		{
			team = t;
		}
		else
		{
			team = 1;
		}
	}
	
	public abstract void move();
	
	public void setCoords(int r, int c)
	{
		row = r;
		col = c;
	}
	
	public int getRow() 
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String getUni()
	{
		return uni;
	}
	
	public int getTeam()
	{
		return team;
	}
	
	public int getScore()
	{
		return value;
	}
	
	public abstract String name();
}
