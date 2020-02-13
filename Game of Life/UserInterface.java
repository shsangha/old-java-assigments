/*
Author: Shawn Sangha [10105434]

Version# Feb/28/2016

Tutorial Section 02

The purpose of this class is to funciton as a menu of sorts to handle user input to determine which version of the biosphere is initialized. When the start method is called the class will display the menu options for the user and allow them to choose which bioshphere to implement. Using the scanner their choice will be taken as an integer from 1-8 which correstonds to which bioshere is initialized. When given an invalid option the default of the program will be to close and print an error statement. 
*/

import java.util.Scanner;

public class UserInterface
{
	private int sphereChoice;
	private Biosphere aBiosphere = new Biosphere();


	
	// when called runs the methods that display, get input and pass the input to the Biosphere class.
    public void start ()
    {
		displayMenu();
		getChoice();
		runMenu();
		checkRunAgain();
 	} 

	
	// displays the user options for which biosphere to initialize and display 
	public void displayMenu()
	{
		System.out.println("Choose the starting biosphere");
		System.out.println("1: All empty ");
		System.out.println("2: One Critter ");
		System.out.println("3: Single birth");
		System.out.println("4: Simple Pattern in middle ");
		System.out.println("5: Pattern near edges ");
		System.out.println("6: Fertile Critter ");
		System.out.println("7: Complex pattern 1: no fertile critters ");
		System.out.println("8: Complex pattern 2: fertile critters\n ");
		
	}


	//gets the user input and returns the integer value 
	public int getChoice()
	{
		Scanner in = new Scanner(System.in);		
		sphereChoice = in.nextInt();
		return sphereChoice;

	}


	// takes the user input and passes into the initialize mehthod of the biosphere class 
	public void runMenu()
	{
		System.out.print(sphereChoice);
	
		switch(sphereChoice)
		{	
			case 1:
			aBiosphere.initialize(1);
    	  	aBiosphere.runTurn();
			break;
	
			case 2:
			aBiosphere.initialize(2);
        	aBiosphere.runTurn();
			break;
			
			case 3:
			aBiosphere.initialize(3);
        	aBiosphere.runTurn();
			break;
		
			case 4:
			aBiosphere.initialize(4);
        	aBiosphere.runTurn();
			break;
		
			case 5:
			aBiosphere.initialize(5);
        	aBiosphere.runTurn();
			break;

			case 6:
			aBiosphere.initialize(6);
        	aBiosphere.runTurn();
			break;			
		
			case 7:
			aBiosphere.initialize(7);
        	aBiosphere.runTurn();
			break;

			case 8:
			aBiosphere.initialize(8);
        	aBiosphere.runTurn();
			break;
		
			default:
			System.out.println("Please make a valid selection (1-8)");
		}

	}
	
	// checks to see if the user wants to run another round 
	public void checkRunAgain()
	{
		boolean goAgain = true;
		while(goAgain==true)
		{
			Scanner in = new Scanner(System.in);
			String p = in.nextLine();
			if(p.equals(""))
			{
				aBiosphere.runTurn();	
			}
			else if((p.equals("q"))||(p.equals("q")))
			{
				goAgain = false;
				System.out.print("End of simulation");
			}
			else if((p.equals("d"))||(p.equals("D")))
			{
				if(Debug.on == true)
				{
					Debug.on = false;
					Mode.debug = false;
					System.out.print("Debug Mode off ");
				}
				else if (Debug.on == false)
				{
					Debug.on = true;
					Mode.debug= true;
					System.out.print("Debug mode on ");
				}
			}
		}

	}	
}

