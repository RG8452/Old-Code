/**
 * Ryan Gahagan
 * This program can construct a rubix cube of any size
 * This trial will create a method to rotate a face clockwise and a method to rotate an inside clockwise
 *
 * Note that, as in trial B, [0][1] of faceOne (WHITE) is understood to be [0][1] of faceTwo (ORANGE) and [1][0] of faceTwo is [0][1] of faceThree (GREEN)
 * Conversely, [0][1] of faceSix (YELLOW) is [2][1] of faceFour (BLUE) and [1][0]  is [2][1] of faceFive (RED)
 * Note that all of above changes in proportion to size
 * Also note that all opposite faces add to 7
 *
 * Potential REV:
 * Make the delineation work with huge cubes and small cubes
 * Allow the user to choose their colors in the Solve method?
 * Apply rotateFaceClockwise method to rotatePiece method
 * Faster solve-checking method?
 */

import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class RubixD {
	static boolean end = false;
    public static void main(String[] args) {
    	while(!end)
    	{
    		int size = enterIntGUI("What size would you like the cube? (2-5 recommended)"); //gets cube size
	        while(size < 1) {size = enterIntGUI("Choose a real size, please.");}  //size of cube
	        
	        Rubix currentRubix = new Rubix(size);  //asks for current cube
	        currentRubix.solve();  //solves for randomization
	        currentRubix.randomize1();   
	        currentRubix.play();   //allows for interaction
	        
	        System.out.print("Would you like to create a new cube to use? ('Y' for Yes) ");
	        String endDummy = Rubix.input.nextLine();
	        if(!(endDummy.equals("Y"))) {end = true;}
    	}
    }
    
    public static int enterIntGUI(String prompt)  // Allows GUI keyboard input of an integer in a graphics program.
	{
		String tempString = JOptionPane.showInputDialog(prompt);
		int temp = Integer.parseInt(tempString);
		return temp;
	}
}

class Face
{	
	private	char[][] face;  //array
	private int faceNum;    //identifier
	
	public Face() //base constructor
	{
		face = new char[3][3];
	}
	
	public Face(int size, int identity) //constructs a face of a certain size
	{
		face = new char[size][size];
		faceNum = identity;
	}
	
	public void displayFace()  //prints out object name and current setup
	{
		System.out.println("Face " + this.faceNum);
		for(int row = 0; row < this.face.length; row++)
		{
			for(int col = 0; col < this.face.length; col++)
			{
				System.out.print(this.face[row][col] + " ");
			}
			System.out.println();
		}
	}
	
	public void assignFace(char color)//makes an entire face one color.
	{
		for(int r = 0; r < this.face.length; r++)
		{
			for(int c=0;c<this.face.length;c++)
			{
				this.face[r][c] = color;
			}
		}
	} 
	
	public int getSize() {return this.face.length;}  //size getter
	
	public char getPiece(int r, int c) {return this.face[r][c];} //piece getter
	
	public void setPiece(int r1, int c1, char swap) {this.face[r1][c1] = swap;} //piece setter
}

class Rubix
{
	public static Scanner input = new Scanner(System.in); //input system
	
	private Face faceOne;  //six faces (Cubes)
    private Face faceTwo;
    private Face faceThree;
    private Face faceFour;
    private Face faceFive;
    private Face faceSix;
    
    public Rubix()  //default constructor 3x3
    {
    	faceOne = new Face(3, 1);
		faceTwo = new Face(3, 2);
		faceThree = new Face(3, 3);
		faceFour = new Face(3, 4);
		faceFive = new Face(3, 5);
		faceSix = new Face(3, 6);
    }
    
    public Rubix(int size)  //constructs a size^2 cube
    {
    	faceOne = new Face(size, 1);
		faceTwo = new Face(size, 2);
		faceThree = new Face(size, 3);
		faceFour = new Face(size, 4);
		faceFive = new Face(size, 5);
		faceSix = new Face(size, 6);
    }
    
    public Face getFace(int identity) //gets the object based on it's identifier
	{
		switch(identity)
		{
			case(1): return this.faceOne;
			case(2): return this.faceTwo;
			case(3): return this.faceThree;
			case(4): return this.faceFour;
			case(5): return this.faceFive;
			case(6): return this.faceSix;
			default: return null;
		}
	}
	
	public void displayCube() //prints out all six faces
	{
		System.out.print("FACE ONE"); for(int q = 0; q < 7; q++) {System.out.print(" ");}  //space delineates && prints three faces
		System.out.print("FACE TWO"); for(int q = 0; q < 7; q++) {System.out.print(" ");}
		System.out.println("FACE THREE");  
		for(int row = 0; row < this.faceOne.getSize(); row++)
		{
			for(int col = 0; col < this.faceOne.getSize(); col++){System.out.print(this.faceOne.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*this.faceOne.getSize(); space++) {System.out.print(" ");}
			for(int col = 0; col < faceTwo.getSize(); col++){System.out.print(this.faceTwo.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*this.faceTwo.getSize(); space++) {System.out.print(" ");}
			for(int col = 0; col < faceThree.getSize(); col++){System.out.print(this.faceThree.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*this.faceThree.getSize(); space++) {System.out.print(" ");}
			System.out.println();
		}
		
		System.out.print("\nFACE FOUR"); for(int q = 0; q < 6; q++) {System.out.print(" ");}  //space delineates && prints other three faces
		System.out.print("FACE FIVE"); for(int q = 0; q < 6; q++) {System.out.print(" ");}
		System.out.println("FACE SIX");  
		for(int row = 0; row < this.faceFour.getSize(); row++)
		{
			for(int col = 0; col < this.faceOne.getSize(); col++){System.out.print(this.faceFour.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*faceOne.getSize(); space++) {System.out.print(" ");}
			for(int col = 0; col < this.faceTwo.getSize(); col++){System.out.print(this.faceFive.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*faceTwo.getSize(); space++) {System.out.print(" ");}
			for(int col = 0; col < this.faceThree.getSize(); col++){System.out.print(this.faceSix.getPiece(row, col) + " ");}	
			for(int space = 0; space < 15 - 2*faceThree.getSize(); space++) {System.out.print(" ");}
			System.out.println();
		}
	}
	
	public void solve()//sets the whole cube to the proper colors
	{
		this.faceOne.assignFace('W');
		this.faceTwo.assignFace('O');
		this.faceThree.assignFace('G');
		this.faceFour.assignFace('B');
		this.faceFive.assignFace('R');
		this.faceSix.assignFace('Y');	
	} 
	
	//This method finds a piece and rotates it
	//1 is F, 2 is B, and then numbers start in the top left and go clockwise
	public void rotateThingClockwise(int face, int num)  //gets the thing to be rotated
	{
		if(num == 1) {rotateFaceClockwise(face);}   //F
		if(num == 2) {rotateFaceClockwise(7-face);} //B
		if(num == 3) {
			for(int qued = 0; qued < 3; qued++)     //F'
				rotateFaceClockwise(face);
		}
		if(num == 4) {
			for(int qued = 0; qued < 3; qued++)     //B'
				rotateFaceClockwise(7-face);
		}
		
		if(num > 4)
		{
			double edge = (num-4) / getFace(face).getSize(); //for non FB cases
			int rem = (num-4) % getFace(face).getSize();
			
			rotateEdges(rem, edge, face);     //rotates all non prime edges
			
			if(rem == 0)    //rotates all prime edges
			{
				if(edge <= 1) {for(int bleh = 0; bleh<3; bleh++) rotateEdges(1, 2.5, face);}
				if(edge <= 2 && edge > 1) {for(int bleh = 0; bleh<3; bleh++) rotateEdges(1, 3.5, face);}
				if(edge <= 3 && edge > 2) {for(int bleh = 0; bleh<3; bleh++) rotateEdges(1, .5, face);}
				if(edge <= 4 && edge > 3) {for(int bleh = 0; bleh<3; bleh++) rotateEdges(1, 1.5, face);}
			}
			
			if(rem != 0 && rem != 1) {rotatePiece(face, num-5);}  //carries to next method if not edge
		}
	}
	
	public void rotateEdges(int rem, double edge, int face) //rotates the appropriate faces on an edge
	{
		if(rem == 1 && edge <= 1){    //case L
			switch(face)
			{
				case 1:
					rotateFaceClockwise(3);	break;
				case 2:
					rotateFaceClockwise(4);	break;
				case 3:
					rotateFaceClockwise(2);	break;
				case 4:
					rotateFaceClockwise(5);	break;
				case 5:
					rotateFaceClockwise(3);	break;
				case 6:
					rotateFaceClockwise(5);	break;
				default: break;
			}
		}
		
		if(rem == 1 && edge > 1 && edge <= 2){  //case U
			switch(face)
			{
				case 1:
					rotateFaceClockwise(2);	break;
				case 2:
					rotateFaceClockwise(1);	break;
				case 3: 
					rotateFaceClockwise(1);	break;
				case 4:
					rotateFaceClockwise(1);	break;
				case 5:
					rotateFaceClockwise(1);	break;
				case 6:
					rotateFaceClockwise(4);	break;
				default: break;
			}
		}
		
		if(rem == 1 && edge > 2 && edge <= 3){  //case U
			switch(face)
			{
				case 1:
					rotateFaceClockwise(4);	break;
				case 2:
					rotateFaceClockwise(3);	break;
				case 3:
					rotateFaceClockwise(5);	break;
				case 4:
					rotateFaceClockwise(2);	break;
				case 5:
					rotateFaceClockwise(4);	break;
				case 6:
					rotateFaceClockwise(2);	break;
				default: break;
			}
		}
		
		if(rem == 1 && edge > 3 && edge <= 4){  //case D
			switch(face)
			{
				case 1:
					rotateFaceClockwise(5);	break;
				case 2:
					rotateFaceClockwise(6);	break;
				case 3: 
					rotateFaceClockwise(6);	break;
				case 4:
					rotateFaceClockwise(6);	break;
				case 5:
					rotateFaceClockwise(6);	break;
				case 6:
					rotateFaceClockwise(3);	break;
				default: break;
			}
		}
	}  
	
	public void rotateFaceClockwise(int face) //rotates a face
	{
		int dim = getFace(face).getSize() - 1;
		
		for(int concentric = 0; concentric < (dim + 1) /2; concentric++) //inner + outer 
		{
			for(int c=concentric; c<dim-concentric;c++){ 
				char temp = getFace(face).getPiece(concentric, dim-c); 
				getFace(face).setPiece(concentric, dim-c, getFace(face).getPiece(c, concentric)); //left into top
				getFace(face).setPiece(c, concentric, getFace(face).getPiece(dim-concentric, c)); //bottom into left
				getFace(face).setPiece(dim-concentric, c, getFace(face).getPiece(dim-c, dim-concentric)); //left into top
				getFace(face).setPiece(dim-c, dim-concentric, temp); //top into right
			}
		}
		
/*		switch(face) //for rotating edges
		{
			case(1):  //SWAPS O,B,R,G CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(3).getPiece(0, z);
					getFace(3).setPiece(0,z,getFace(5).getPiece(0,z));
					getFace(5).setPiece(0,z,getFace(4).getPiece(0,z));
					getFace(4).setPiece(0,z,getFace(2).getPiece(0,z));
					getFace(2).setPiece(0,z,curSwap);
				}
				break;
			case(2):  //SWAPS GYBW CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(1).getPiece(0, dim-z);
					getFace(1).setPiece(0,dim-z,getFace(4).getPiece(dim-z,dim));
					getFace(4).setPiece(dim-z,dim,getFace(6).getPiece(dim-z,dim));
					getFace(6).setPiece(dim-z,dim,getFace(3).getPiece(z,0));
					getFace(3).setPiece(z,0,curSwap);
				}
				break; 
			case(5):  //SWAPS BYGW CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(1).getPiece(dim, z);
					getFace(1).setPiece(dim,z,getFace(3).getPiece(dim-z,dim));
					getFace(3).setPiece(dim-z,dim,getFace(6).getPiece(z,0));
					getFace(6).setPiece(z,0,getFace(4).getPiece(z,0));
					getFace(4).setPiece(z,0,curSwap);
				}
				break; 
			case(3):  //SWAPS RYOW CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(1).getPiece(z,0);
					getFace(1).setPiece(z,0,getFace(2).getPiece(dim-z,dim));
					getFace(2).setPiece(dim-z,dim,getFace(6).getPiece(dim,z));
					getFace(6).setPiece(dim,z,getFace(5).getPiece(z,0));
					getFace(5).setPiece(z,0,curSwap);
				}
				break; 
			case(4):  //SWAPS OYRW CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(1).getPiece(z,dim);
					getFace(1).setPiece(z,dim,getFace(5).getPiece(z,dim));
					getFace(5).setPiece(z,dim,getFace(6).getPiece(0,z));
					getFace(6).setPiece(0,z,getFace(2).getPiece(z,0));
					getFace(2).setPiece(z,0,curSwap);
				}
				break; 
			case('Y'):  //SWAPS OGRB CHECKED
				for(int z=0;z<=dim;z++) {
					char curSwap = getFace(3).getPiece(dim, z);
					getFace(3).setPiece(dim,z,getFace(5).getPiece(dim,z));
					getFace(5).setPiece(dim,z,getFace(4).getPiece(dim,z));
					getFace(4).setPiece(dim,z,getFace(2).getPiece(dim,z));
					getFace(2).setPiece(dim,z,curSwap);
				}
				break;
			default: break;
		}*/
		
		switch(face)
		{
			case 1:	rotatePiece(2, dim + 1); break;
			case 2: rotatePiece(1, dim + 1); break;
			case 3:	rotatePiece(1, 0); break;
			case 4: rotatePiece(1, (dim + 1) * 2); break;
			case 5:	rotatePiece(1, (dim + 1) * 3); break;
			case 6: rotatePiece(4, (dim + 1) * 3); break;
			default: System.out.println("Error: No suitable face rotation"); 
		}
	}
	
	//note that num in this case starts at zero in the top left
	public void rotatePiece(int face, int num)
	{
		int dim = getFace(face).getSize()-1;
		if(num > 4*(dim+1)) {num-=(4*(dim+1));}
		int side = (int)Math.floor(num / (dim+1));
		switch(face)
		{
			case 1:
				switch(side)
				{
					case 0:  //SWAPS OYRW
						for(int z=0;z<=dim;z++) {
							char curSwap = getFace(1).getPiece(z,num);
							getFace(1).setPiece(z,num,getFace(2).getPiece(dim-z,dim-num));
							getFace(2).setPiece(dim-z,dim-num,getFace(6).getPiece(dim-num,z));
							getFace(6).setPiece(dim-num,z,getFace(5).getPiece(z,num));
							getFace(5).setPiece(z,num,curSwap);
						} break;
					case 1: //SWAPS BYGW
						num%=(dim+1);
						for(int z=0;z<=dim;z++) {
							char curSwap = getFace(1).getPiece(num,z);
							getFace(1).setPiece(num,z,getFace(4).getPiece(z,dim-num));
							getFace(4).setPiece(z,dim-num,getFace(6).getPiece(z,dim-num));
							getFace(6).setPiece(z,dim-num,getFace(3).getPiece(dim-z,num));
							getFace(3).setPiece(dim-z,num,curSwap);
						} break;
					case 2: //SWAPS RYOW
						num%=(dim+1);
						for(int z=0;z<=dim;z++) {
							char curSwap = getFace(1).getPiece(z,dim-num);
							getFace(1).setPiece(z,dim-num,getFace(5).getPiece(z,dim-num));
							getFace(5).setPiece(z,dim-num,getFace(6).getPiece(num,z));
							getFace(6).setPiece(num,z,getFace(2).getPiece(dim-z,num));
							getFace(2).setPiece(dim-z,num,curSwap);
						} break;
					case 3: //SWAPS GYBW
						num%=(dim+1);
						for(int z=0;z<=dim;z++) {
							char curSwap = getFace(1).getPiece(dim-num, dim-z);
							getFace(1).setPiece(dim-num,dim-z,getFace(3).getPiece(z,dim-num));
							getFace(3).setPiece(z,dim-num,getFace(6).getPiece(z, num));
							getFace(6).setPiece(z,num,getFace(4).getPiece(dim-z,num));
							getFace(4).setPiece(dim-z,num,curSwap);
						} break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face); 
				} break;
			case 2:
				switch(side)
				{
					case 0: 
						rotatePiece(1, 2*(dim+1) + num); break;
					case 1:
						num%=(dim+1);
						for(int z=0;z<=dim;z++) {
							char curSwap = getFace(2).getPiece(num, dim-z);
							getFace(2).setPiece(num,dim-z,getFace(3).getPiece(num,z));
							getFace(3).setPiece(num,z,getFace(5).getPiece(num,z));
							getFace(5).setPiece(num,z,getFace(4).getPiece(num,z));
							getFace(4).setPiece(num,z,curSwap);
						} break;
					case 2:
						rotatePiece(1, num - 2*(dim+1)); break;
					case 3:
						for(int inc=0;inc<3;inc++){
							rotatePiece(2, num - (2*(dim + 1) + 1));	 
						} break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face);
				}break;
			case 3:
				switch(side)
				{
					case 0: 
						rotatePiece(1, num + dim + 1); break;
					case 1:
						rotatePiece(2, num); break;
					case 2:
						rotatePiece(1, num + dim + 1); break;
					case 3:
						rotatePiece(2, num); break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face);
				}break;
			case 4:
				switch(side)
				{
					case 0: 
						rotatePiece(1,num + 3*(dim+1)); break;
					case 1:
						rotatePiece(2, num); break;
					case 2:
						rotatePiece(1, num-(dim+1)); break;
					case 3:
						rotatePiece(2, num); break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face);
				}break;
			case 5:
				switch(side)
				{
					case 0: 
						rotatePiece(1, num); break;
					case 1:
						rotatePiece(2, num); break;
					case 2:
						rotatePiece(1, num); break;
					case 3:
						rotatePiece(2, num); break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face);
				}break;
			case 6:
				switch(side)
				{
					case 0: 
						rotatePiece(4, num); break;
					case 1:
						rotatePiece(2, num-(dim+1)); break;
					case 2:
						rotatePiece(4, num); break;
					case 3:
						rotatePiece(5, num-3*(dim+1)); break;
					default: System.out.println("ERROR REACHED, NO EDGE, FACE " + face);
				}break;
			default: System.out.println("NO BUENO!"); break;				
		}
	}
	
	public void randomize1() //uses psuedorandom number generator to do a random rotation
	{
		int size = getFace(1).getSize();
		Random rnd = new Random();
		for(int shuffle=0;shuffle<200;shuffle++){
			rotateThingClockwise(rnd.nextInt(6)+1, rnd.nextInt(size*4) + 5);
		}
	}
	
	public static int getChosenFace() //UI for choosing a face
	{
		System.out.print("Which face would you like to rotate?  ");
		int choose = 0;
		if(input.hasNextInt()){choose = input.nextInt(); input.nextLine();}
		else {input.nextLine();}
		while(choose < 1 || choose > 6)
		{
			System.out.print("Please choose an existing face.  ");
			String dummy3 = input.nextLine();
			Scanner stupid = new Scanner(dummy3);
			if(stupid.hasNextInt()) {choose = stupid.nextInt();}
		}
		return choose;
	}  
	
	public int getChosenMove(int chosenFace)  //UI to access what kind of move is chosen
	{
		int count = 5;  //perimeter values for non FB values
		int endCount = 4 * getFace(chosenFace).getSize() + 4;   
		
		for(int q = 0; q < 1+String.valueOf(endCount).length(); q++) {System.out.print(" ");} //delineate
		
		for(int zed = 0; zed < getFace(chosenFace).getSize(); zed++)  //first row
		{
			System.out.print(count + "  ");
			count++;
		}
		
		System.out.println();
		for(int rows = 0; rows < getFace(chosenFace).getSize(); rows++)  //big block
		{
			System.out.print(endCount + " ");
			for(int cols = 0; cols < getFace(chosenFace).getSize(); cols++)
			{
				System.out.print(getFace(chosenFace).getPiece(rows, cols) + "  ");
			}
			System.out.println(count);
			count++;
			endCount--;
		}
		
		for(int q = 0; q < 1+String.valueOf(endCount).length(); q++) {System.out.print(" ");} //delineate
		for(int omega = 0; omega<getFace(chosenFace).getSize(); omega++)
		{
			System.out.print(endCount + " ");
			endCount--;
		}
		
		System.out.print("\n\nWhich move would you like to make? (1 is F, 2 is B, 3 is F', 4 is B')  ");
		int move = Integer.parseInt(input.nextLine()); //UI For move
		while(move<0 || move>(4*getFace(chosenFace).getSize() + 4)) //validates
		{
			if(move == 69) {return move;}  //test case for win
			System.out.print("Please choose a valid number.  ");
			move = Integer.parseInt(input.nextLine());
		}
		return move;
	}
	
	public void changePiece(int r, int c, int face, char col) //non-static piece method
	{
		getFace(face).setPiece(r, c, col);
	}
	
	public void play() //main running
	{
		do
		{
			displayCube();  //show me
			System.out.println(); 
			int temp = getChosenFace();  //retrieves face
			int temp2 = getChosenMove(temp); //retrieves move
			if(temp2!=69) {rotateThingClockwise(temp, temp2);} //moves
			if(temp2==69) {solve();}
			System.out.println();
		} while(!isSolved());
	}
	
	public boolean isSolved() //checks for completion
	{
		boolean solved = true; //used to drop the loops for efficiency
		int faces = 1;
		while(solved && faces <= 6)
		{
			int row = 0;
			while(solved && row < getFace(faces).getSize()){
				int col = 0;
				while(solved && col < getFace(faces).getSize()){
					if(getFace(faces).getPiece(row, col) != getFace(faces).getPiece(0,0)){
						solved = false;
					}
					col++;
				}
				row++;
			}
			faces++;
		}  //REV: Big O(N^3) is better with while statements, but could it be faster?
		
		if(solved) {  //offer choice to mess with cube
			displayCube();
			System.out.println("\nCongratulations! A winner is you!");
			System.out.print("Would you like to keep playing with this cube? ('Y' for Yes) ");
			String dum = input.nextLine();
			if(dum.equals("Y")) {
				solved = false;
				System.out.print("Would you like to shuffle the cube? ('Y' for Yes) ");
				dum=input.nextLine();
				if(dum.equals("Y")) {this.randomize1();}
			}
			System.out.println();
		}
		
		return solved;
	}
}