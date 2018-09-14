/**
 * @(#)StatisticsMath.java
 *
 *
 * @RyanGahagan
 */
 
import java.util.Scanner;

public class StatisticsMath
{
    public static void main(String[] args) 
    {
    	int[] array1 = new int[5];
		int[] array2 = new int[5];
		int[] array3 = new int[5];
		int[] array4 = new int[5];
		int[] array5 = new int[5];
		BigFive.init();
		
        BigFive.setArray(array1);
        BigFive.printTotal(array1);
        System.out.println("The Norm Score of Emotional Stability is: " + BigFive.normScore(1) + "\n");
        
        BigFive.setArray(array2);
        BigFive.printTotal(array2);
        System.out.println("The Norm Score of Extroversion is: " + BigFive.normScore(2) + "\n");
        
        BigFive.setArray(array3);
        BigFive.printTotal(array3);
        System.out.println("The Norm Score of Openness to Experience is: " + BigFive.normScore(3) + "\n");
        
        BigFive.setArray(array4);
        BigFive.printTotal(array4);
        System.out.println("The Norm Score of Agreeableness is: " + BigFive.normScore(4) + "\n");
        
        BigFive.setArray(array5);
        BigFive.printTotal(array5);
        System.out.println("The Norm Score of Conscientiousness is: " + BigFive.normScore(5) + "\n");
    }
}

class BigFive
{
	static Scanner input;
	static int rowSet;
	static int overCount;
	static int tempTotal;
	
	public static void init()
	{
		input = new Scanner(System.in);
		overCount = 1;
		rowSet = 1;
	}
	
	public static void setArray(int[] array)
	{
		overCount = 1;
		for(int count = 0; count < 5; count++)
		{
			System.out.print("Enter row " + (overCount*5-(5-rowSet)) + ":  ");
			array[count] = input.nextInt();
			overCount++;				
		}
		rowSet++;
	}
	
	public static void printTotal(int[] array)
	{
		int total = 0;
		for(int count = 0; count < array.length; count++)
		{
			total += array[count];	
		}
		tempTotal = total;
		System.out.println("The total number of points is:  " + total);
	}
	
	public static int normScore(int row)
	{
		int norm = 0;
		if(row == 1)
		{
			if(tempTotal > 22) {tempTotal = 22;}
			if(tempTotal < 7) {tempTotal = 7;}
			switch(tempTotal)
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
		else if (row == 2)
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
		else if (row == 3)
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
		else if (row == 4)
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
		else if (row == 5)
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
		return norm;
	}
}