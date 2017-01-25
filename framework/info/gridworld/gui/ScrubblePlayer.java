package info.gridworld.gui;

import info.gridworld.actor.*;

import java.util.ArrayList;

public class ScrubblePlayer 
{
	/**
	 * Instance variable of an int to hold the score
	 */
    private int score;
    
    /**
	 * ArrayList of Tiles in hand
	 */
    ArrayList<Tile> myTiles;

    /**
	 * Array of vowels
	 */
    char[] vowels = { 'A', 'E', 'I', 'O', 'U' };
    
    /**
     * array of consonants
     */
    char[] consonants = {'B', 'C', 'D','F', 'G', 'H', 'J', 'K',
        'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y',
        'Z' };
    
    /**
     * random number
     */
    int rand;
    
    /**
     * Constructs an Scrubble player object.
     */
    public ScrubblePlayer()
    {
        score = 0;
        newHand();
    }
    
    /**
	 * Makes a new random hand for the player
	 */
    public void newHand()
    {

        myTiles = new ArrayList<Tile>();
        /**for ( int k = 0; k < 4; k++ )
        {
            rand = ( int )( Math.random() * vowels.length );
            Tile temp = new Tile( vowels[ rand ]);
            myTiles.add( temp );
        }
        for ( int k = 4; k < 15; k++ )
        {
            rand = ( int )( Math.random() * consonants.length );
            Tile temp = new Tile( consonants[ rand ]);
            myTiles.add( temp );
        }**/
        myTiles.add(new Tile('S'));
        myTiles.add(new Tile('C'));
        myTiles.add(new Tile('R'));
        myTiles.add(new Tile('U'));
        myTiles.add(new Tile('B'));
        myTiles.add(new Tile('B'));
        myTiles.add(new Tile('L'));
        myTiles.add(new Tile('E'));

    }
    
    /**
     * Gets the player name.
     * 
     * @return the player name
     */
    public ScrubblePlayer getPlayer()
    {
        return this;
    }
    
    /**
	 * access tiles in hand
	 * 
	 * @return a list of tiles in hand
	 */
    public ArrayList<Tile> getTiles()
    {
    	return myTiles;
    }

	/**
	 * Access the score
	 * 
	 * @return the integer score
	 */
    public int getScore()
    {
        return score;
    }    
    
    /**
	 * Changes the score to newScore
	 * 
	 * @param newScore
	 *            the new score to be set
	 */
    public void setScore( int newScore )
    {
    	score = newScore;
    }

    /**
     * does nothing
     */
	public void play() 
	{
		// do nothing
	}
}
