/**
 * 
 */

import java.util.Scanner;
import java.util.ArrayList;

import java.text.DecimalFormat;

public class Calculus 
{
	static Scanner sc = new Scanner(System.in);
	
    public static void main(String[] args) 
    {
    	System.out.println("Hello! Welcome to the Calculus program!");
    	
        while(true)
        {        	
        	ask();  //ask what kind of function is desired
        	
        	String bleh = sc.nextLine();  //gets next line
        	
        	boolean valid = true;  //error checking values
        	int choice = 0;
        	
        	try //try to parse out an int
        	{
        		choice = Integer.parseInt(bleh);
        	} 
        	catch(IllegalArgumentException e) //parse out strings
        	{
        		System.out.println("Sorry, I don't recognize that command!");
        		valid = false;
        	}
        	
        	if(choice < 1 || choice > 9)  //error handling on nonexistent numbers
        	{
        		valid = false;
        	}
        	
        	while(!valid)  //repeats above test to get a real answer
        	{
        		System.out.println("Please use another input.");
        		bleh = sc.nextLine();
        		valid = true;
        		
	        	try
	        	{
	        		choice = Integer.parseInt(bleh);
	        	} 
	        	catch(IllegalArgumentException e)
	        	{
	        		System.out.println("Sorry, I don't recognize that command!");
	        		valid = false;
	        	}
	        	
	        	if(choice < 1 || choice > 9)
	        	{
	        		valid = false;
	        	}
        	}
        	
        	System.out.println();
        	
        	String equation;  //temp equation
        	double spot;  //evaluation point
        	double ans;  //Used for numeric calculations
        	String newEQ;	//Used for formula calculations
        	
        	double a,b,c,discrete; //used for methods with multiple sums, ie Riemann or Euler's
        	
        	switch(choice)  //checks each choice in turn
        	{
        		case 1:  //Evaluation
        			equation = askEQ();
        			System.out.println("What number would you like to evaluate the function for?");
        			spot = sc.nextDouble();
        			ans = evaluateFunction(equation, spot);
        			System.out.println("The answer is: " + ans);
        			break;
        			
        		case 2:  //Derivative (point)
        			equation = askEQ();
        			System.out.println("What number would you like to evaluate the derivative at?");
        			spot = sc.nextDouble();
        			ans = derivativePoint(equation, spot);
        			System.out.println("The slope at this point is: " + ans);
        			break;
        			
        		case 3:  //Derivative (formula)
        			equation = askEQ();
        			newEQ = derivativeFormula(equation);
        			System.out.println("The derivative is: ");
        			System.out.println(newEQ);
        			break;
        			
        		case 4:  //Left Riemann Sum
        			equation = askEQ();  //gets the desired equation
        			
        			System.out.println("What number would you like to start at?");
			    	a = sc.nextDouble();  //beginning
			    	System.out.println("What number would you like to end at?");
			    	b = sc.nextDouble();  //end
			    	System.out.println("What size is your step? (If a to b does not perfectly divide, there may be some error in calculation)");
			    	discrete = sc.nextDouble();  //discrete
			    	
        			ans = evalLeftRiemann(equation, a, b, discrete); //evaluates equation
        			System.out.println("The sum is: " + ans);  //returns answer
        			break;
        			
        		case 5:  //Right Riemann Sum
        			equation = askEQ();  //gets the desired equation
        			
        			System.out.println("What number would you like to start at?");
			    	a = sc.nextDouble();  //beginning
			    	System.out.println("What number would you like to end at?");
			    	b = sc.nextDouble();  //end
			    	System.out.println("What size is your step? (If a to b does not perfectly divide, there may be some error in calculation)");
			    	discrete = sc.nextDouble();  //discrete
			    	
        			ans = evalRightRiemann(equation, a, b, discrete); //evaluates equation
        			System.out.println("The sum is: " + ans);  //returns answer
        			break;
        			
        		case 6:  //Integral (point)
        			equation = askEQ();
        			
        			System.out.println("What number would you like to start at?");
			    	a = sc.nextDouble();  //beginning
			    	System.out.println("What number would you like to end at?");
			    	b = sc.nextDouble();  //end
			    	
        			ans = integralRange(equation, a, b);
        			System.out.println("The area under the curve is: " + ans);
        			break;
        			
        		case 7:  //Integral (formula)
        			equation = askEQ(); 
        			newEQ = integralFormula(equation);
        			System.out.println("Your final formula is: ");
        			System.out.println(newEQ + " + C");
        			break;
        			
        		case 8:  //Evaluate Implicit Formula
        			equation = askImplicitEQ();
        			
        			System.out.println("What x would you like to evaluate the function for?");
        			a = sc.nextDouble();
        			System.out.println("What y would you like to evaluate the function for?");
        			b = sc.nextDouble();
        			
        			ans = evaluateImplicitFunction(equation, a, b);
        			System.out.println("The value is: " + ans);
        			break;
        			
        		case 9:  //Euler's method
        			equation = askImplicitEQ();
        			
        			System.out.println("What x value would you like to start at?");
			    	a = sc.nextDouble();  //beginning
			    	System.out.println("What y value would you like to start at?");
			    	b = sc.nextDouble();  //end
			    	System.out.println("What x value would you like to end at?");
			    	c = sc.nextDouble();
			    	System.out.println("What size is your step? (If a to b does not perfectly divide, there may be some error in calculation)");
			    	discrete = sc.nextDouble();  //step size
			    	
			    	ans = evalEuler(equation, a, b, c, discrete); //evaluates equation
			    	System.out.println("The Euler's approximation at that point is: y=" + ans);
			    	break;
        	}
        	
        	System.out.println("Press enter to continue.");  //prints out a line for delay
        	sc.nextLine();
        	String dummy = sc.nextLine();  //dummy eats lines sometimes so this just waits until you hit enter once.
        	
        	System.out.println("\n\n");
        }
    }
    
    public static void ask()  //just cascading printlns to ask for the desired function
    {
    	System.out.println("What function would you like to use? (Type the chosen number)");
    	System.out.println("1: Evaluation");
    	System.out.println("2: Derivative (point)");
    	System.out.println("3: Derivative (formula)");
    	System.out.println("4: Left Riemann sum");
    	System.out.println("5: Right Riemann sum");
    	System.out.println("6: Integral (range)");
    	System.out.println("7: Integral (formula)");
    	System.out.println("8: Evaluate Implicit Formula");
    	System.out.println("9: Euler's method");
    }
    
    public static String askEQ()  //asks user for the desired formula
    {
    	String formula;
    	
    	System.out.println("Please enter your formula. (Type HELP for specification)");
    	formula = sc.nextLine();  //retrieves formula
    	
    	while(formula.length() == 0 || formula.equals("HELP")) //drop menu for HELP
    	{
    		System.out.println("Type your equation as a simple quadratic without parenthesis.");
    		System.out.println("Use carats to denote power and precede any term with its coeffecient.");
    		System.out.println("Also, use the lower-case letter 'x' as your variable of choice.");
    		System.out.println("This program can derive but not integrate natural logs (EX: ln(2x) = 2.0/2.0x)");
    		System.out.println("Finally, put one space between every term and symbol.");
    		System.out.println("EX: 1.5x^4 + 64x^2 - 0.34x^.5 + 14");
    		formula = sc.nextLine();
    	}
    	
    	return formula;
    }
    
    public static String askImplicitEQ()  //asks user for the implicit formula
    {
    	String formula;
    	
    	System.out.println("Please enter your implicit formula. (Type HELP for specification)");
    	formula = sc.nextLine();  //retrieves formula
    	
    	while(formula.length() == 0 || formula.equals("HELP"))  //drop menu for HELP
    	{
    		System.out.println("Type your equation with spaces between terms and no parenthesis.");
    		System.out.println("Use carats to denote power and precede any term with its coefficient.");
    		System.out.println("Do not space out any term with powers on both x and y (EX: 3x^2y^3)");
    		System.out.println("Use lower-case x and y to represent the implicit variables.");
    		System.out.println("EX: 3x^3 - 2xy^2 + x^4y^2 - 3");
    		formula = sc.nextLine();
    	}
    	
    	return formula;
    }
    
    public static double getTempPower(String temp)  //gets the power of the current term
    {
    	int carat = temp.indexOf("^");  //find the carat
    	if(carat != -1)  //if you have a carat
    	{
    		String powString = temp.substring(carat+1);  //get the string of the power
			return Double.parseDouble(powString);  //return the power
    	}
    	
		else //if there is no carat
		{
			if(temp.indexOf("x") != -1) //if you have an x but no carat
			{
				return 1;  //power of 1
			}
			else //if you have no x
			{
				return 0;  //power of 0
			}
		}
    }
    
    public static double getTempCoef(String temp)
    {
    	double coef = 0; 
    	
    	if(temp.indexOf("x")!=-1)  //if the current term has an x
		{
			String coefString = temp.substring(0, temp.indexOf("x")); //cut out everything from beginning to x
			
			if(coefString.length() != 0)
			{
				coef = Double.parseDouble(temp.substring(0, temp.indexOf("x")));  //parse the coefficient if possible
			}
			else
			{
				coef = 1;
			}				
		}
		else  //if there is no x
		{
			coef = Double.parseDouble(temp);  //the coefficient must be the whole term
		}
		
		return coef;
    }
    
    public static double evaluateFunction(String eq, double x)
    {
    	Scanner read = new Scanner(eq);  //scans the equation
    	boolean pos = true;  //used for positive evaluation
    	String lastSign = "";
    	double total = 0; //used to return evalutaion
    	
		while(read.hasNext()) //as long as the equation isn't out
		{
			double val = 0;  //holds for values
			double coef = 0;  //coefficient of current term
			String temp = read.next();
			
			if(lastSign.equals("-")) {pos = false;}  //used for subtraction
			else {pos = true;}
			
			coef = getTempCoef(temp);
				
			if(!pos) 
			{
				coef*=-1; //multiplies the coefficient by -1 if you are subtracting
			} 
			
			if(temp.indexOf("^")!=-1)  //if you have a carat
			{
				double power = getTempPower(temp);
				
				val = coef * Math.pow(x, power);  //calculates the value of this term
				total += val;  //adds this value to the total
			}
			
			else if(temp.indexOf("x") == temp.length()-1)  //if the term ends in an x (AKA there is no power)
			{
				total += coef * x; //add the coefficient times x
			}
			 
			else  //if there is no x at all
			{
				total += coef;
			}
			 
			if(read.hasNext()) {lastSign = read.next();}  //if there is more, read in the sign
		}
		
		return total;  //returns the total
    }
    
    public static double derivativePoint(String eq, double x)  //derives a formula at a point and returns the slope
    {
    	String derived = derivativeFormula(eq);  //gets the derived equation
    	return evaluateFunction(derived, x);  //evaluates the derived equation at the wanted point
    }
    
    public static String derivativeFormula(String eq)  //returns the derivative formula of an equation
    {
    	Scanner read = new Scanner(eq);  //scans the equation
    	boolean pos = true;  //used for positive evaluation
    	String lastSign = "";
		String ansString = ""; //used to return answer
    	
		while(read.hasNext()) //as long as the equation isn't out
		{
			double coef = 0;  //coefficient of current term
			String temp = read.next();
			
			if(lastSign.equals("-")) {pos = false;}  //used for subtraction
			else {pos = true;}
			
			if(lastSign.length() != 0 && temp.indexOf("x") != -1)  //if there is another term with an x (that matters)
			{
				ansString += " " + lastSign + " ";  //add the sign
			}
				
			if(temp.indexOf("ln(") != -1)
			{
				String lnTemp = "";
				lnTemp = temp.substring(temp.indexOf("(") + 1, temp.indexOf(")"));

				ansString += derivativeFormula(lnTemp) + "/" + lnTemp;
			}
			
			else
			{				
				if(temp.indexOf("x")!=-1)  //if the current term has an x
				{
					String coefString = temp.substring(0, temp.indexOf("x")); //cut out everything from beginning to x
					
					if(coefString.length() != 0)
					{		
						coef = Double.parseDouble(temp.substring(0, temp.indexOf("x")));  //parse the coefficient if possible			
					}
					else  //if there is no coefficient
					{
						coef = 1;  //supplement 1
					}
				}
				
				double power = getTempPower(temp); //gets term power
				coef *= power;  //multiply coefficients
				
				power--;  //decrement power
				 
				if(coef != 0)
				{
					if(power != 1 && power != 0)	//If power is not 1 or 0
					{
						ansString += String.valueOf(coef) + "x^" + String.valueOf(power);	//Adds the formula of the term to the total formula if there is not an exponent of 1
					}
					else if(power == 0)		//If power is 0
					{
						ansString += coef;  //add the coefficient
					}
					else if(power == 1)		//Should only evaluate is power is 1
					{
						ansString += String.valueOf(coef) + "x";	//Adds the formula of the term to the total formula if there is an exponent of 1
					}
					else
					{
						ansString += "ERROR!";  //error if all others are false
					}
					
				}
				else
				{
					ansString += "";	//If there is no x in the term, the derivative will be 0 and will not be added to the total formula
				}
				
			}
			 
			if(read.hasNext())  //if you have a sign
			{
				lastSign = read.next();  //store the sign
			}
		}
		
		return ansString;  //returns the final formula
    	
    }
    	
    public static double evalLeftRiemann(String eq, double a, double b, double discrete)  //evaluates a left Riemann sum
    {    	
    	double total = 0;  //total for caculating
    	
    	for(double x = a; x < b; x += discrete)  //loops through every x
    	{
    		total += evaluateFunction(eq, x) * discrete;  //adds the rectangle of the left evaluation and the discrete
    	}
    	
    	return total;  //returns the total
    }
    
    public static double evalRightRiemann(String eq, double a, double b, double discrete)  //evaluates a left Riemann sum
    {
    	double total = 0;  //total for caculating
    	
    	for(double x = b; x > a; x -= discrete)  //loops through every x
    	{
    		total += evaluateFunction(eq, x) * discrete;  //adds the rectangle of the left evaluation and the discrete
    	}
    	
    	return total;  //returns the total
    }
    
    public static String integralFormula(String eq) //returns the formula of an interval
    {
    	String newFormula = "";  //used to store new formula
    	String temp = "";  //stores current term
    	String lastSign = "";  //stores last sign (+ or -)
    	Scanner read = new Scanner(eq);  //reads equation
    	DecimalFormat output = new DecimalFormat("0.000"); //formats long decimals

    	while(read.hasNext())  //while the equation has more terms
    	{
    		temp = read.next(); //get the next term
    		
			double coef = getTempCoef(temp);  //get the next coefficient
			
			double power = getTempPower(temp);  //get the next power
			
			double newPower = power + 1;  //increment the power
			double newCoef = coef / newPower;  //make the new coefficient the old over the power
			
			String newCoefString = "";  //used for storing the coefficient as a string
			
			if(String.valueOf(newCoef).substring(String.valueOf(newCoef).indexOf(".") + 1).length() > 3)  //if you have a long decimal
			{
				newCoefString = output.format(newCoef);  //truncate the decimal to three places
			}
			else //if your decimal is not long
			{
				newCoefString = String.valueOf(newCoef);  //store the decimal as a string
			}
			
			if(newPower != 0  && newPower != 1) //if you have a power other than 0 or 1
			{
				newFormula += (newCoefString + "x^" + newPower);  //add the coef * x ^ power
			}
			else if(newPower == 1)  //if you have a power of 1
			{
				newFormula += (newCoefString + "x");  //add coef*x
			}
			else  //if you have a power of 0
			{
				newFormula += (newCoefString);  //add the coefficient
			}
			
			
			if(read.hasNext())  //if you have a sign
			{
				lastSign = read.next();  //store the sign
				newFormula += " " + lastSign + " ";  //add the sign to the new formula
			}
    	}	
    		
    	return newFormula;  //return your new formula at the end
    }
    
    public static double integralRange(String eq, double a, double b)
    {
    	double ans = 0;
    	
    	String integral = integralFormula(eq);
    	
    	ans += evaluateFunction(integral, b);
    	ans -= evaluateFunction(integral, a);
    	
    	return ans;
    }
    
    public static double evaluateImplicitFunction(String eq, double x, double y)  //evaluates an implicit formula at a given point
    {
    	Scanner read = new Scanner(eq);  //scans eq
    	double ans = 0;  //answer
    	String splitX, splitY, lastSign = "";  //splitX and splitY hold halves of temp with their respective x and y terms
    	
    	while(read.hasNext())
    	{
    		String temp = read.next();  //get next term
    		int xSpot = temp.indexOf("x");  //find x
    		int ySpot = temp.indexOf("y");  //find y
    		double coef, power, powerX, powerY, val;  //declare all of these to zero
    		//coef is term coefficient, power is single term power, powerX/Y are for two variable terms, and val is the terms result
    		
    		if(ySpot == -1 && xSpot != -1)  //if this term only has an x
    		{
    			coef = getTempCoef(temp);
    			
    			if(lastSign.equals("-")) {coef *= -1;}
    			
    			if(temp.indexOf("^")!=-1)  //if you have a carat
				{
					power = getTempPower(temp);
					
					val = coef * Math.pow(x, power);  //calculates the value of this term
					ans += val;  //adds this value to the total
				}
				
				else if(temp.indexOf("x") == temp.length()-1)  //if the term ends in an x (AKA there is no power)
				{
					ans += coef * x; //add the coefficient times x
				}
    		}
    		
    		else if(ySpot != -1 && xSpot == -1)  //if this term only has a y
    		{
    			temp = temp.replaceAll("y", "x");
    			
    			coef = getTempCoef(temp);
    			
    			if(lastSign.equals("-")) {coef *= -1;}
    			
    			if(temp.indexOf("^")!=-1)  //if you have a carat
				{
					power = getTempPower(temp);
					
					val = coef * Math.pow(y, power);  //calculates the value of this term
					ans += val;  //adds this value to the total
				}
				
				else if(temp.indexOf("x") == temp.length()-1)  //if the term ends in an x (AKA there is no power)
				{
					ans += coef * y; //add the coefficient times x
				}
    		}
    		
    		else if(ySpot != -1 && xSpot != -1)  //if you have both an x and a y
    		{
    			if(xSpot < ySpot) // if the x term comes before the y term
    			{
    				splitX = temp.substring(0, temp.indexOf("y")); //gets the entire term up to x
    				splitY = temp.substring(splitX.length()); //gets everything from y onwards
    				
    				coef = getTempCoef(splitX);  //parses the coef out of the x term
    				if(lastSign.equals("-")) {coef *= -1;}  //flips the coef if necessary
    				
    				powerX = getTempPower(splitX);  //parses out the x power
    				
    				splitY = splitY.replaceAll("y", "x"); //parses the 'y's out to become 'x's
    				powerY = getTempPower(splitY);  //gets the power
    				
    				val = coef * Math.pow(x, powerX) * Math.pow(y, powerY);  //adds the value to ans
    				ans += val;
    			}
    			
    			else if(ySpot < xSpot) //if the y term comes before the x term
    			{
    				splitY = temp.substring(0, temp.indexOf("x")); //gets the entire term up to x
    				splitX = temp.substring(splitY.length()); //gets everything from y onwards
    				
    				splitY = splitY.replaceAll("y", "x"); //parses the 'y's out to become 'x's
    				powerY = getTempPower(splitY);  //gets the y power
    				
    				coef = getTempCoef(splitY);  //parses the coef out of the y term
    				if(lastSign.equals("-")) {coef *= -1;}  //flips the coef if necessary
    				
    				powerX = getTempPower(splitX);  //parses out the x power
    				
    				val = coef * Math.pow(x, powerX) * Math.pow(y, powerY);  //adds the value to ans
    				ans += val;
    			}
    		}
    		
    		else  //if there is no x and no y
    		{
    			coef = Double.parseDouble(temp);
    			if(lastSign.equals("-")) {coef *= -1;}
    			ans += coef;
    		}
    		
    		if(read.hasNext()) {lastSign = read.next();}  //get that next sign
    	}
    	
    	return ans;
    }
    
    public static double evalEuler(String eq, double startX, double startY, double endX, double dX)
    {
    	double curY = startY;  //start y
    	double dYdX = 0;  //slope
    	double dY = 0;  //change in Y
    	DecimalFormat output = new DecimalFormat("0.000");  //used for limiting length
    	String tempDec = ""; //used with long decimals
    	
    	for(double curX = startX; curX < (endX - dX + .0001); curX += dX)  //the crazy conditional handles error bounds
    	{
    		dYdX = evaluateImplicitFunction(eq, curX, curY);  //find dYdX
    		dY = dYdX * dX;  //use dX and dYdX to find dY
    		curY += dY;  //add onto Y
    		
//    		System.out.println(curX + "," + curY);
    	}
    	
    	if(String.valueOf(curY).substring(String.valueOf(curY).indexOf(".") + 1).length() > 3)  //if you have a long decimal
		{
			tempDec = output.format(curY);  //truncate the decimal to three places
		}
		else //if your decimal is not long
		{
			return curY;
		}
    	
    	return Double.parseDouble(tempDec);
    }
}

