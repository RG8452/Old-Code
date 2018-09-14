
public class Empty extends Piece 
{
	public Empty() 
	{
		uni = "";
	}	
		
	public Empty(int r, int c)
	{
		super(r, c);
		team = 3;
		uni = "";
	}
	
	public void move()
	{
	}
	
	public String name()
	{
		return "Empty Space";
	}
}
