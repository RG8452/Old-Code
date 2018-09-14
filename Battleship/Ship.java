/*
 * Ryan Gahagan
 * P4 Jad Jadeja
 * Programming II
 * This is the ship class of my Battleship code
 * It exists to manipulate the users ship at the beginning of each run.
 */

import java.awt.Graphics;
import java.awt.Graphics2D;

class Ship
{
	private int row, col, length, rotation; //coords, size, and orientation
	static int block = Battleship.getBlockSize();  //retrieves the size of a block from the main class
	
	public Ship() //default constructor makes an aircraft carrier
	{
		row = 0;
		col = 0;
		length = 5;
		rotation = 1;
	}
	
	public Ship(int l) //overrider, constructs with length
	{
		row = 0;
		col = 0;
		length = l;
		rotation = 1;
	}
	
	public Ship(int r, int c, int l, int rot) //total override
	{
		row = r;
		col = c;
		length = l;
		rotation = rot;
	}
	
	//retrieval methods
	public int getRow() 
	{
		return row;
	} 
		
	public int getCol()
	{
		return col;
	}
	
	public int getLength() 
	{
		return length;
	}
	
	public int getRotation() 
	{
		return rotation;
	}
	
	public boolean inBounds() //checks if the ship is within the grid based on rotation
	{ 
		if(rotation == 0)
		{
			if(row - (length-1) < 0) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		} 
			
		else if(rotation == 1)
		{
			if(col + (length-1) > 9) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		} 
			
		else if(rotation == 2)
		{
			if(row + (length-1) > 9) 
			{
				return false;
			}
			else 
			{
				return true;
			}
		} 
			
		else if(rotation == 3)
		{
			if(col - (length-1) < 0)
			{
				return false;
			}
			else 
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void move(char c) //moves the ship based on a character typed
	{ 
		switch(c)
    	{
    		case 'w': //up
    			row--; 
    			if(row - (length-1) < 0 && rotation == 0) //checks bounds on the edge of the ship
    			{
    				row++;
    			} 
    			break;
    		case 'a': //left
    			col--; 
    			if(col - (length-1) < 0 && rotation == 3) //same in all other dir
    			{
    				col++;
    			} 
    			break;
    		case 's': //down
    			row++; 
    			if(row + (length-1) > 9 && rotation == 2) 
    			{
    				row--;
    			}
    			break;
    		case 'd': //right
    			col++;
    			if(col + (length-1) > 9 && rotation == 1) 
    			{
    				col--;
    			}
    			break;
    		case 'q': //rotates counterclockwise
    			rotation--;  
    				
    			if(rotation<0) 
    			{
    				rotation = 3;  //keeps rotation in range 0 to 3
    			}
    			
    			if(!inBounds()) 
    			{
    				rotation++; //un-rotates if bounds are broken
    			} 
    				
    			if(rotation>3) 
    			{
    				rotation = 0; //maintains bounds
    			} 
    				
    			break;
    		case 'e': //rotates clockwise
    			rotation++;
    			
    			if(rotation>3) 
    			{
    				rotation = 0; //same as above
    			} 
    				
    			if(!inBounds()) 
    			{
    				rotation--;
    			}
    			
    			if(rotation<0) 
    			{
    				rotation = 3;
    			}
    			
    			break;
    		case ' ':
    			break;
    	}
    	
    	//checks bounds on key piece of the ship
    	if(row < 0) 
    	{
    		row = 0;
    	} 
    		
    	if(row > 9) 
    	{
    		row = 9;
    	}
    	
    	if(col < 0) 
    	{
    		col = 0;
    	}
    	
    	if(col > 9) 
    	{
    		col = 9;
    	}
	}
	
	public void draw(Graphics g) //draws the boat in the graphics screen
	{ 
		for(int q=0;q<length;q++) 
		{
    		switch (rotation)
    		{
    			case 3:
    				g.fillRect((col-q+1) * block + 1, (row+1) * block + 1, block-1, block-1);
    				break;
    			case 2:
    				g.fillRect((col+1) * block + 1, (row+q+1) * block + 1, block-1, block-1);
    				break;
    			case 1:
    				g.fillRect((col+q+1) * block + 1, (row+1) * block + 1, block-1, block-1);
    				break;
    			case 0:
    				g.fillRect((col+1) * block + 1, (row-q+1) * block + 1, block-1, block-1);
    				break;
    			default:
    				System.out.println("ERROR");
    		}
    	}
	}
}