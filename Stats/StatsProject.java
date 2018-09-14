/**
 * Ryan Gahagan & Mark Tressler & Keon Williams
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class StatsProject
{        
    public static void main(String[] args) 
    {
		BigFive.init(); //initializes the array
		int totalStudents = BigFive.numStudents(); //asks the user for number of students
		Classroom.init(); //initializes the class
		ArrayList tempList = new ArrayList(); //creates a temporary arraylist
		
		for(int increment = 0; increment < totalStudents; increment++)
		{
			System.out.println("Student " + (increment + 1)); //prints out the student being edited
			BigFive.setArray(tempList); //sets the student to the input values
			Classroom.addStudent(tempList); //adds the student to the class
		}
		
		Classroom.printAll(); //prints out all student attributes
    }
}

class BigFive //used for mostly calculations
{
	static Scanner input; //user input
	
	public static void init() //initialize
	{
		input = new Scanner(System.in);
	}
	
	public static int numStudents() //retrieve number of students
	{
		System.out.print("How many students are in the class? ");
		return input.nextInt();
	}
	
	public static void setArray(ArrayList list) //fills up the student's values with user input
	{
		for(int count = 0; count < 25; count++)
		{
			System.out.print("Enter row " + (count + 1) + ":  ");
			list.add(input.nextInt());				
		}
	}
	
	public static int calcTotal(ArrayList list, int row) //calculates the total
	{
		int total = 0;
		int hold = 0;
		for(int count = 5 * row; count < 5 * row + 5; count++)
		{
			hold = (int)(list.get(count));
			total += hold;	
		}
		return total;
	}
	
	public static int normScore(int row, int tempTotal) //runs through the norm scores and puts out the norm score based on category
	{
		int norm = 0;
		if(row == 1)
		{
			if(tempTotal > 22) {tempTotal = 22;}
			if(tempTotal < 7) {tempTotal = 7;}
			switch(tempTotal) //checks emotional stability
			{
				case 7: norm = 21; break;
				case 8: norm = 25; break;
				case 9: norm = 29; break;
				case 10: norm = 33; break;
				case 11: norm = 36; break;
				case 12: norm = 40; break;
				case 13: norm = 44; break;
				case 14: norm = 48; break;
				case 15: norm = 51; break;
				case 16: norm = 55; break;
				case 17: norm = 59; break;
				case 18: norm = 62; break;
				case 19: norm = 66; break;
				case 20: norm = 70; break;
				case 21: norm = 73; break;
				case 22: norm = 77; break;
				default: norm = 0; break;
			}			
		}
		else if (row == 2) //extroversion
		{
			if(tempTotal < 5) {tempTotal = 5;}
			switch(tempTotal)
			{
				case 5: norm = 21; break;
				case 6: norm = 26; break;
				case 7: norm = 28; break;
				case 8: norm = 30; break;
				case 9: norm = 33; break;
				case 10: norm = 35; break;
				case 11: norm = 37; break;
				case 12: norm = 40; break;
				case 13: norm = 43; break;
				case 14: norm = 46; break;
				case 15: norm = 48; break;
				case 16: norm = 50; break;
				case 17: norm = 52; break;
				case 18: norm = 55; break;
				case 19: norm = 57; break;
				case 20: norm = 60; break;
				case 21: norm = 62; break;
				case 22: norm = 65; break;
				case 23: norm = 67; break;
				case 24: norm = 70; break;
				case 25: norm = 72; break;
			}
		}
		else if (row == 3) //oppenness to experience
		{
			if(tempTotal < 5) {tempTotal = 5;}
			switch(tempTotal)
			{
				case 5: norm = 22; break;
				case 6: norm = 25; break;
				case 7: norm = 28; break;
				case 8: norm = 31; break;
				case 9: norm = 34; break;
				case 10: norm = 37; break;
				case 11: norm = 40; break;
				case 12: norm = 42; break;
				case 13: norm = 45; break;
				case 14: norm = 47; break;
				case 15: norm = 50; break;
				case 16: norm = 54; break;
				case 17: norm = 56; break;
				case 18: norm = 59; break;
				case 19: norm = 62; break;
				case 20: norm = 64; break;
				case 21: norm = 67; break;
				case 22: norm = 70; break;
				case 23: norm = 73; break;
				case 24: norm = 76; break;
				case 25: norm = 79; break;
			}
		}
		else if (row == 4) //agreeableness
		{
			if(tempTotal < 8) {tempTotal = 8;}
			switch(tempTotal)
			{
				case 8: norm = 20; break;
				case 9: norm = 24; break;
				case 10: norm = 27; break;
				case 11: norm = 29; break;
				case 12: norm = 32; break;
				case 13: norm = 35; break;
				case 14: norm = 38; break;
				case 15: norm = 41; break;
				case 16: norm = 44; break;
				case 17: norm = 47; break;
				case 18: norm = 50; break;
				case 19: norm = 54; break;
				case 20: norm = 55; break;
				case 21: norm = 59; break;
				case 22: norm = 62; break;
				case 23: norm = 65; break;
				case 24: norm = 68; break;
				case 25: norm = 71; break;
			}
		}
		else if (row == 5) //conscientiousness
		{
			if(tempTotal < 5) {tempTotal = 5;}
			switch(tempTotal)
			{
				case 5: norm = 22; break;
				case 6: norm = 24; break;
				case 7: norm = 26; break;
				case 8: norm = 28; break;
				case 9: norm = 30; break;
				case 10: norm = 33; break;
				case 11: norm = 35; break;
				case 12: norm = 38; break;
				case 13: norm = 41; break;
				case 14: norm = 44; break;
				case 15: norm = 46; break;
				case 16: norm = 48; break;
				case 17: norm = 50; break;
				case 18: norm = 52; break;
				case 19: norm = 55; break;
				case 20: norm = 59; break;
				case 21: norm = 61; break;
				case 22: norm = 63; break;
				case 23: norm = 65; break;
				case 24: norm = 67; break;
				case 25: norm = 69; break;
			}
		}
		return norm; //returns found value
	}
}

class Classroom //used for the classroom arraylist
{
	
	static ArrayList classroom; //the class
	
	public static void init() //initialize
	{
		classroom = new ArrayList<ArrayList>();
	}
	
	public static void addStudent(ArrayList list) //adds student
	{
		classroom.add(list);
	}
	
	public static ArrayList getStudent(int value) //returns the student as an arraylist
	{
		return (ArrayList)(classroom.get(value));
	}
	
	public static void printStudent(int value) //prints out each norm score using a students canlculated total. The student is accessed as value
	{
		System.out.println("The Norm Score of Emotional Stability is: " + BigFive.normScore(1, BigFive.calcTotal((ArrayList)classroom.get(value), 0)) + "\n");
		System.out.println("The Norm Score of Extroversion is: " + BigFive.normScore(2, BigFive.calcTotal((ArrayList)classroom.get(value), 1)) + "\n");
		System.out.println("The Norm Score of Openness to Experience is: " + BigFive.normScore(3, BigFive.calcTotal((ArrayList)classroom.get(value), 2)) + "\n");
		System.out.println("The Norm Score of Agreeableness is: " + BigFive.normScore(4, BigFive.calcTotal((ArrayList)classroom.get(value), 3)) + "\n");
		System.out.println("The Norm Score of Conscientiousness is: " + BigFive.normScore(5, BigFive.calcTotal((ArrayList)classroom.get(value), 4)) + "\n");
	}
	
	public static void printAll() //loops through the print statement of all students
	{
		for(int bleh = 0; bleh < totalStudents; bleh++)
		{
			System.out.println("\nStudent " + (bleh + 1) + " :");
			printStudent(bleh);
		}
	}
}