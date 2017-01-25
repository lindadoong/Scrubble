package info.gridworld.gui;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import info.gridworld.world.ScrubbleWorld;


public class ScrubbleGame
{
    /** The world */
    private ScrubbleWorld world;

    /** The Scrubble player */
    private ScrubblePlayer player;
    
    /**
     * Hash set of valid english words
     */
    private HashSet<String> valids;
    
    /**
     * score that you must have to pass
     */
    private int neededScore;

    /**
     * creates a new scrubble game
     * initializes neededScore to 50
     * reads in file of valid english words and adds it to valids
     */
    public ScrubbleGame()
    {
        world = new ScrubbleWorld( this );
        
        valids = new HashSet<>();
        player = new ScrubblePlayer();
        neededScore = 50;
        
        File file = new File( "/Users/lindadoong/Documents/Scrubble/framework/info/gridworld/gui/ValidWords.txt" );

        Scanner scan = null;
        try
        {
            scan = new Scanner( file );
        }
        catch ( FileNotFoundException e )
        {
            System.out.println( "File not found" );
        }

        scan.nextLine();

        while ( scan.hasNextLine() )
        {
            String word = scan.nextLine();
            if ( word.length() >= 3 )
            {
                valids.add( word );
            }
        }
        
        world.show();
    }
    
    /**
     * @return list of valid words
     */
    public HashSet<String> getValidWords()
    {
        return valids;
    }

    /**
     * @return scrubble player
     */
    public ScrubblePlayer getPlayer()
    {
        return player;
    }

    /**
     * Plays the game until it is over (no player can play).
     */
    public void playGame()
    {
        player.play();
    }
    
    /**
     * ends the game
     */
    public void end()
    {
        world.setMessage( toString( player.getScore()) );
    }
    
    /**
     * @return world
     */
    public ScrubbleWorld getWorld()
    {
        return world;
    }
    
    /**
     * checks the validity of the word
     * @param str
     * @return if word is valid
     */
    public boolean checkValidity( String str )
    {
    	return valids.contains(str.toLowerCase());
    }


    /**
     * Creates a string with the current game state. (used for the GUI message).
     */
    public String toString( int humanScore )
    {
        String result = "";
        if ( humanScore >= neededScore )
        {
             result = "You won!";
        }
        else
        {
        	result = "Sorry, you lost.";
        }
        return result;
    }
}
