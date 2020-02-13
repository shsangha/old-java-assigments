/*
Author Shawn Sangha[10105434]

Version: Mar/1/2016

Tutorial Section 2

This class stores the information about the appearance of the critter objects that will be initialized in class Biosphere. This class contains mehtods to set, get and display the appearance of the critter object. 

*/

public class Critter
{
	// constants
    public static final char REGULAR = '*';
    public static final char EMPTY = ' ';
    public static final char FERTILE = '!';

	// instance variable 
    private char appearance;
	
	// deafault constructor 
    public Critter ()
    {
        appearance = REGULAR;
    }
	
	// constructor that takes an argument
    public Critter (char ch)
    {
        appearance = ch;
    }

	// returns the appearance 
    public char getAppearance ()
    {
        return(appearance);
    } 

	// allows access to alter the appearance while avoiding a memory leak 
    public void setAppearance (char newAppearance)
    {
        if ((newAppearance == EMPTY) || 
            (newAppearance == REGULAR) ||
            (newAppearance == FERTILE))
            appearance = newAppearance;
         else
             System.out.println("Critter's appearance must be either a star, space or exclaimation");
    } 

	// displays the critter appearance 
    public void display ()
    {
        System.out.print(appearance);
    }
	
}


