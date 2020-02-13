/*
Author Shawn Sangha[10105434]

Version: Mar/1/2016

Tutorial Section 2

Features of this class include taking an array of "Critter" objects in the form of a 2 dimensional array in order to display the biosphere. Taking an argument from the userinterface class determined by user input the biosphere to be implemented is determined by a switch statement and then displayed. During each round the biosphere scans each element by scanning exceptional cases (corners, top/bottom, left,right), and the inside of the biosphere to determine which cells live or die. The birth methods for each case will print a critter '*' for each empty space that has exactly 3 neighbors. The death methods will convert critters to empty space if they have less that 2 neighbors or 4 or more neighbors. After each check the current generation is copied to the previous so that another turn may be run. 


Limitations:

*/
public class Biosphere
{
	// constants
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;
    public static final int MIN_ROW = 0;
    public static final int MAX_ROW = 9;
    public static final int MIN_COLUMN = 0;
    public static final int MAX_COLUMN = 9;
    public static final String HORIZONTAL_LINE = "  - - - - - - - - - -";
    public static final String HORIZONTAL_COUNT = "  0 1 2 3 4 5 6 7 8 9 ";

    private Critter [][] previous;
    private Critter [][] current; 
    private int generation;

	// default constuctor sets all elements to empty critters 
    public Biosphere ()
    {
        int r;
        int c;
        generation = 0;
        previous = new Critter [ROWS][COLUMNS];
        current = new Critter [ROWS][COLUMNS]; 
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
      }
	
	// when called takes spereType(user input) to determine which sphere is initialized 
    public void initialize (int sphereType)
    {
        switch (sphereType)
        {
            case 1:
                initializeCase1 ();
                break;

            case 2:
                initializeCase2 ();
                break;

            case 3:
                initializeCase3();
                break;
    
            case 4:
                initializeCase4 ();
                break;
	    
            case 5:
              initializeCase5();
              break;

            case 6:
                initializeCase6 ();
                break;
           
            case 7:
                initializeCase7 ();
                break;

            case 8:
                initializeCase8 ();
                break;
        }
    }
    
    // Completely empty
    private void initializeCase1 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
    }

    // Single critter
    private void initializeCase2 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
        for (c = 0; c < COLUMNS; c++)
        {
            previous[r][c] = new Critter(Critter.EMPTY);
            current[r][c] = new Critter(Critter.EMPTY);
        }
    }
    previous[MIN_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);
    current[MIN_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);	
    }

    // Single birth (3 critters)
    private void initializeCase3 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
        for (c = 0; c < COLUMNS; c++)
        {
            previous[r][c] = new Critter(' ');
            current[r][c] = new Critter (' ');
        }
        }
        current[1][1] = new Critter('*');
        previous[1][1] = new Critter('*');
        current[2][3] = new Critter('*');
        previous[2][3] = new Critter('*');
        current[3][1] = new Critter('*');
        previous[3][1] = new Critter('*');
    }
    
    // Simple pattern in middle
    private void initializeCase4 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
    previous[1][1] = new Critter(Critter.REGULAR);
    current[1][1] = new Critter(Critter.REGULAR);	
    previous[2][3] = new Critter(Critter.REGULAR);
    current[2][3] = new Critter(Critter.REGULAR);
    previous[2][2] = new Critter(Critter.REGULAR);
    current[2][2] = new Critter(Critter.REGULAR);			
    previous[3][1] = new Critter(Critter.REGULAR);
    current[3][1] = new Critter(Critter.REGULAR);	
    previous[3][3] = new Critter(Critter.REGULAR);
    current[3][3] = new Critter(Critter.REGULAR);
    }

    // Pattern near edges
    private void initializeCase5 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
        previous[MIN_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);
        current[MIN_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);
        previous[MIN_ROW][MAX_COLUMN] = new Critter(Critter.REGULAR);
        current[MIN_ROW][MAX_COLUMN] = new Critter(Critter.REGULAR);	
        previous[MAX_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);
        current[MAX_ROW][MIN_COLUMN] = new Critter(Critter.REGULAR);	
        previous[MAX_ROW][MAX_COLUMN] = new Critter(Critter.REGULAR);
        current[MAX_ROW][MAX_COLUMN] = new Critter(Critter.REGULAR);	
        previous[0][2] = new Critter(Critter.REGULAR);
        current[0][2] = new Critter(Critter.REGULAR);	
        previous[2][1] = new Critter(Critter.REGULAR);
        current[2][1] = new Critter(Critter.REGULAR);	
    }
	
	//fertile critter
    private void initializeCase6 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
            previous[2][MIN_COLUMN] = new Critter(Critter.FERTILE);
            current[2][MIN_COLUMN] = new Critter(Critter.FERTILE);	    
            previous[1][1] = new Critter(Critter.REGULAR);
            current[1][1] = new Critter(Critter.REGULAR);	    
            previous[4][1] = new Critter(Critter.FERTILE);
            current[4][1] = new Critter(Critter.FERTILE);	    
        }
    }

    // Complex pattern, no fertile critters
    private void initializeCase7 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
        current[1][4] = new Critter ('*');
        previous[1][4] = new Critter ('*');
        current[2][5] = new Critter ('*');
        previous[2][5] = new Critter ('*');
        current[3][3] = new Critter ('*');
        previous[3][3] = new Critter ('*');
        current[3][4] = new Critter ('*');
        previous[3][4] = new Critter ('*');
        current[3][5] = new Critter ('*');
        previous[3][5] = new Critter ('*');
    }

    // Complex pattern, with fertile critters
    private void initializeCase8 ()
    {
        int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c] = new Critter(Critter.EMPTY);
                current[r][c] = new Critter(Critter.EMPTY);
            }
        }
        current[2][2]= new Critter (Critter.FERTILE);
        previous[2][2] = new Critter (Critter.FERTILE);
        current[2][3] = new Critter (Critter.REGULAR);
        previous[2][3] = new Critter (Critter.REGULAR);
        current[2][4] = new Critter (Critter.FERTILE);
        previous[2][4] = new Critter (Critter.FERTILE);
        current[4][1] = new Critter (Critter.REGULAR);
        previous[4][1] = new Critter (Critter.REGULAR);
    }

	// displays the array 
    private void display ()
    {
        int r;
        int c;
        System.out.println("\t\tGeneration: " + generation);
        System.out.println("  PREVIOUS GENERATION" + "\t   CURRENT GENERATION");
        System.out.println(HORIZONTAL_COUNT + " \t " + HORIZONTAL_COUNT);
        for (r = 0; r < ROWS; r++)
        {
        System.out.println(HORIZONTAL_LINE + " \t " + HORIZONTAL_LINE);
        System.out.print(r + "|");
        for (c = 0; c < COLUMNS; c++)
        {
            previous[r][c].display();
            System.out.print('|');
        }
        System.out.print("\t" + r);
        System.out.print(" |");
        for (c = 0; c < COLUMNS; c++)
        {
            current[r][c].display();
            System.out.print('|');
        }
        System.out.println();
    }
    System.out.println(HORIZONTAL_LINE + " \t " + HORIZONTAL_LINE);
	if(Mode.debug == true)
		{
			System.out.println("display called");
		}
    }

	// when called a turn is run. all the methods that scan/move critters are called in this method.
    public void runTurn ()
    {
		checkTopRowBirths();
		checkTopRowDeaths();
		checkBottomRowBirths();
		checkBottomRowDeaths();
		checkTopLeftBirth();
		checkTopLeftDeaths();
		checkBotLeftBirths();
		checkBotLeftDeaths();
		checkTopRightBirths();
		checkTopRightDeaths();
		checkBotRightBirths();
		checkBotRightDeaths();
		checkInsideBirths();
		checkInsideDeaths();
		checkLeftColBirths();
		checkLeftColDeaths();
		checkRightColBirths();
		checkRighColDeaths();
		generation++;
		display();
		copyPrev();
    }

	// deep copy of the old current location to previous 
	public void copyPrev()
	{
		int r;
        int c;
        for (r = 0; r < ROWS; r++)
        {
            for (c = 0; c < COLUMNS; c++)
            {
                previous[r][c].setAppearance(current[r][c].getAppearance());
               
            }
        }
		if(Mode.debug == true)
		{
			System.out.println("running deep copy from current to previous");
		}
	}
	
	// checks the inner top row for births 
	public void checkTopRowBirths()
	{	

		int c;
		
		for(c=1;c<9;c++)
		{
			
			int neighbors = 0;
			if(previous[0][c].getAppearance() == ' ')
			{
				if(previous[0][c-1].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[0][c+1].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[1][c].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[0][c-1].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[0][c+1].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[1][c].getAppearance() == '*')
				{
					neighbors++;
				}
				if(neighbors== 3)
				{
					current[0][c].setAppearance('*');
				}
			}			
		} 
	}

	// checks the inner top row for deaths 
	public void checkTopRowDeaths()
	{	
		int c;
		
		for(c=1;c<9;c++)
		{
			
			int neighbors = 0;
			if((previous[0][c].getAppearance() == '!') ||(previous[0][c].getAppearance() == '*'))
			{
				if((previous[0][c-1].getAppearance() == '!') ||(previous[0][c-1].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[0][c+1].getAppearance() == '!') ||(previous[0][c+1].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[1][c].getAppearance() == '!') ||(previous[1][c].getAppearance() == '*'))
				{	
					neighbors++;
				}
			}	
			if(((neighbors==0)||(neighbors==1)) || ((neighbors<3) && (neighbors<9)))
			{
				current[0][c].setAppearance(' ');
			}		
		}	 
	}
	
	// checks the inner bottom row for births 
	public void checkBottomRowBirths()
	{
		int c;
		
		for(c=1;c<9;c++)
		{
			
			int neighbors = 0;
			if(previous[9][c].getAppearance() == ' ')
			{
				if(previous[9][c-1].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[9][c+1].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[8][c].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[9][c-1].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[9][c+1].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[8][c].getAppearance() == '*')
				{
					neighbors++;
				}
				if(neighbors== 3)
				{
					current[9][c].setAppearance('*');
				}
			}			
		}	 
	}

	// checks the inner bottom row for deaths 
	public void checkBottomRowDeaths()
	{
		int c;
		
		for(c=1;c<9;c++)
		{
			
			int neighbors = 0;
			if((previous[9][c].getAppearance() == '!') ||(previous[9][c].getAppearance() == '*'))
			{
				if((previous[9][c-1].getAppearance() == '!') ||(previous[9][c-1].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[9][c+1].getAppearance() == '!') ||(previous[9][c+1].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[8][c].getAppearance() == '!') ||(previous[8][c].getAppearance() == '*'))
				{	
					neighbors++;
				}
			}	
			if(((neighbors==0)||(neighbors==1)) || ((neighbors<3) && (neighbors<9)))
			{
				current[9][c].setAppearance(' ');
			}		
		}	 
	}
	
	// checks the top left corner for births 
	public void checkTopLeftBirth()
	{
		int neighbors = 0;
		
		if(previous[0][0].getAppearance() == ' ')
		{
			if(previous[0][1].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][0].getAppearance()== '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][1].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[0][1].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[0][1].getAppearance()== '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[0][1].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(neighbors ==3)
			{
				current[0][0].setAppearance('*');
			}
		}		
	}
	
	// checks the top left corner for deaths 
	public void checkTopLeftDeaths()
	{
		int neighbors =0;
		if ((previous[0][0].getAppearance() == '!') || (previous[0][0].getAppearance() == '*'))
		{
			if((previous[1][0].getAppearance() == '!') ||(previous[1][0].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[0][1].getAppearance() == '!') ||(previous[0][1].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[1][1].getAppearance() == '!') ||(previous[1][1].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if(((neighbors==0)||(neighbors==1)) || ((neighbors>3) && (neighbors<9)))
			{
				current[0][0].setAppearance(' ');
			}
			
		} 
	}	
	
	// checks the bottom left corner for births 
	public void checkBotLeftBirths()
	{
		int neighbors = 0;
		
		if(previous[9][0].getAppearance() == ' ')
		{
			if(previous[9][1].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][0].getAppearance()== '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][8].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[9][1].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][0].getAppearance()== '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][8].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(neighbors ==3)
			{
				current[9][0].setAppearance('*');
			}
		}		
	}	

	// checks the bottom left corner for deaths 
	public void checkBotLeftDeaths()
	{
		int neighbors =0;
		if ((previous[9][0].getAppearance() == '!') || (previous[9][0].getAppearance() == '*'))
		{
			if((previous[9][1].getAppearance() == '!') ||(previous[9][1].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[8][0].getAppearance() == '!') ||(previous[8][0].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[8][8].getAppearance() == '!') ||(previous[8][8].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if(((neighbors==0)||(neighbors==1)) || ((neighbors>3) && (neighbors<9)))
			{
				current[9][0].setAppearance(' ');
			}
			
		} 

	}
	
	//checks the top right corner for births 
	public void checkTopRightBirths()
	{
		int neighbors = 0;
		
		if(previous[0][9].getAppearance() == ' ')
		{
			if(previous[0][8].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][9].getAppearance()== '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][8].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[0][8].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][9].getAppearance()== '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[1][8].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(neighbors ==3)
			{
				current[0][9].setAppearance('*');
			}
		}		
	}	

	// checks the top right corner for deaths 
	public void checkTopRightDeaths()
	{
		int neighbors =0;
		if ((previous[0][9].getAppearance() == '!') || (previous[0][9].getAppearance() == '*'))
		{
			if((previous[0][8].getAppearance() == '!') ||(previous[0][8].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[1][9].getAppearance() == '!') ||(previous[1][9].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[1][8].getAppearance() == '!') ||(previous[1][8].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if(((neighbors==0)||(neighbors==1)) || ((neighbors>3) && (neighbors<9)))
			{
				current[0][9].setAppearance(' ');
			}
			
		} 

	}

	// checks the bottom right corner for births 
	public void checkBotRightBirths()
	{
		int neighbors = 0;
		
		if(previous[9][9].getAppearance() == ' ')
		{
			if(previous[9][8].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][9].getAppearance()== '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][8].getAppearance() == '!')
			{
				neighbors = neighbors + 2;
			}
			if(previous[9][8].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][9].getAppearance()== '*')
			{
				neighbors = neighbors + 2;
			}
			if(previous[8][8].getAppearance() == '*')
			{
				neighbors = neighbors + 2;
			}
			if(neighbors ==3)
			{
				current[9][9].setAppearance('*');
			}
		}		
	}	

	// checks the botttom right corner for deaths 
	public void checkBotRightDeaths()
	{
		int neighbors =0;
		if ((previous[9][9].getAppearance() == '!') || (previous[9][9].getAppearance() == '*'))
		{
			if((previous[9][8].getAppearance() == '!') ||(previous[9][8].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[8][9].getAppearance() == '!') ||(previous[8][9].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if((previous[8][8].getAppearance() == '!') ||(previous[8][8].getAppearance() == '*'))
			{	
				neighbors++;
			}
			if(((neighbors==0)||(neighbors==1)) || ((neighbors>3) && (neighbors<9)))
			{
				current[9][9].setAppearance(' ');
			}
			
		} 

	}

	//checks the inside grid for births 
	public void checkInsideBirths()
	{
		int r;
        int c;
        for (r = 1; r < ROWS-1; r++)
        {
			
            for (c = 1; c < COLUMNS-1; c++)
            {
				int neighbors=0;
				if(previous[r][c].getAppearance()== ' ') 
				{
	
					if(previous[r+1][c].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r-1][c].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r][c+1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r][c-1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r+1][c+1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r-1][c-1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r+1][c-1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}
					if(previous[r-1][c+1].getAppearance() == '!')
					{
						neighbors = neighbors +2;
					}

					if(previous[r+1][c].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r-1][c].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r][c+1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r][c-1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r+1][c+1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r-1][c-1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r+1][c-1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(previous[r-1][c+1].getAppearance() == '*')
					{
						neighbors++;
					}
					if(neighbors ==3)
					{
						current[r][c].setAppearance('*');
					}
				}
			}          	
		}		
	}
	
	// checks the inside grid for deaths 
	public void checkInsideDeaths()
	{
		int r;
        int c;
        for (r = 1; r < ROWS-1; r++)
        {
            for (c = 1; c < COLUMNS-1; c++)
            {
				int neighbors=0;
				if((previous[r][c].getAppearance()== '!')||(previous[r][c].getAppearance()== '*'))           	
				{
					if((previous[r][c+1].getAppearance() == '!') || (previous[r][c+1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r+1][c].getAppearance() == '!') || (previous[r+1][c].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r][c-1].getAppearance() == '!') || (previous[r][c-1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r-1][c].getAppearance() == '!') || (previous[r-1][c].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r+1][c+1].getAppearance() == '!') || (previous[r+1][c+1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r+1][c-1].getAppearance() == '!') || (previous[r+1][c-1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r-1][c+1].getAppearance() == '!') || (previous[r-1][c+1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if((previous[r-1][c-1].getAppearance() == '!') || (previous[r-1][c-1].getAppearance() == '*'))
					{
						neighbors++;
					}
					if(((neighbors==0)||(neighbors==1)) || ((neighbors>3) && (neighbors<9)))
					{
						current[r][c].setAppearance(' ');
					}
				}	
            }
        }
	}
	
	// checks the the far left column for births 
	public void checkLeftColBirths()
	{
		int r;
		
		for(r=1;r<9;r++)
		{
			
			int neighbors = 0;
			if(previous[r][0].getAppearance() == ' ')
			{
				if(previous[r][1].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r-1][0].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r+1][0].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r][1].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[r-1][0].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[r+1][0].getAppearance() == '*')
				{
					neighbors++;
				}
				if(neighbors== 3)
				{
					current[r][0].setAppearance('*');
				}
			}			
		}	 
	}

	// checks the far left column for deaths 
	public void checkLeftColDeaths()
	{
		int r;
		
		for(r=1;r<9;r++)
		{
			
			int neighbors = 0;
			if((previous[r][0].getAppearance() == '!') ||(previous[r][0].getAppearance() == '*'))
			{
				if((previous[r][1].getAppearance() == '!') ||(previous[r][1].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[r+1][0].getAppearance() == '!') ||(previous[r+1][0].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[r-1][0].getAppearance() == '!') ||(previous[r-1][0].getAppearance() == '*'))
				{	
					neighbors++;
				}
			}	
			if(((neighbors==0)||(neighbors==1)) || ((neighbors<3) && (neighbors<9)))
			{
				current[r][0].setAppearance(' ');
			}		
		}	 
	}

	// checks the far right colum for births 
	public void checkRightColBirths()
	{
			int r;
		
		for(r=1;r<9;r++)
		{
			
			int neighbors = 0;
			if(previous[r][9].getAppearance() == ' ')
			{
				if(previous[r-1][9].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r+1][9].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r][8].getAppearance() == '!')
				{
					neighbors = neighbors+2;
				}
				if(previous[r-1][9].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[r+1][9].getAppearance() == '*')
				{
					neighbors++;
				}
				if(previous[r][8].getAppearance() == '*')
				{
					neighbors++;
				}
				if(neighbors== 3)
				{
					current[r][9].setAppearance('*');
				}
			}			
		}	 
	}
	
	// checks the far right column for deaths 
	public void checkRighColDeaths()
	{
		int r;
		
		for(r=1;r<9;r++)
		{
			
			int neighbors = 0;
			if((previous[r][9].getAppearance() == '!') ||(previous[r][9].getAppearance() == '*'))
			{
				if((previous[r][8].getAppearance() == '!') ||(previous[r][8].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[r+1][9].getAppearance() == '!') ||(previous[r+1][9].getAppearance() == '*'))
				{	
					neighbors++;
				}
				if((previous[r-1][9].getAppearance() == '!') ||(previous[r-1][9].getAppearance() == '*'))
				{	
					neighbors++;
				}
			}	
			if(((neighbors==0)||(neighbors==1)) || ((neighbors<3) && (neighbors<9)))
			{
				current[r][9].setAppearance(' ');
			}		
		}	 
	
	}
}		
		 
	








