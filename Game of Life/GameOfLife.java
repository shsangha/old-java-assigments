/*
Author: Shawn Sangha [10105434]

Version# Mar/2/2016

Tutorial Section 02

Limitations: The program crashes if a user inputs anthing outside the range of 1-10 to implement the biosphere 

Program Features|:
This program is a simplified game of life siimulation. When initialized the program asks for user input to initialize a biosphere. Each biosphere object is made up of an array of critters that are either empty ' ', fertile '!', or regular '*'. The 10x10 array is then displayed with the previous and current generation which are two sepperate critter arrays. 
 A critter will only live if it has 2-3 neighbors, a critter will be born in an empty space if it has exactly 3 neighbors. Upon birth fertile critters count as 2 neighbors, upon death all critters are equal to one neighbor. After each generation the user has an option to hit enter to run another lifecycle or to hit 'd' to enter debug mode, or to hit 'q' to end  the simulation.

Biosphere Class: handles the display and scanning of the critter objects, aswell as moves them after each turn

UserInterface Class: Acts as the menu, displauys options and passes through which biosphere to be run, aswell as toggling debug mode and quitting the program.

Critter Class: Holds the appearance of the critter ojects and contains methods to return and set the appearance 

Debug/Mode Classes: Static classes used to implement debug flags 

 

*/
public class GameOfLife
{
    public static void main (String [] args)
    {
        UserInterface anInterface = new UserInterface ();
        anInterface.start();
    }
}




