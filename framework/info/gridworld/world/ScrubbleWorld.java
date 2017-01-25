package info.gridworld.world;

import info.gridworld.gui.ScrubbleGame;

public class ScrubbleWorld extends World<Tile>
{
	/**
	 * Scrubble game
	 */
    ScrubbleGame game;
    
    /**
     * creates a new ScrubbleWorld
     * sets message with instructions
     * @param g scrubblegame
     */
    public ScrubbleWorld( ScrubbleGame g )
    {
        game = g;
        setMessage( "Place a word on the board or receive a new hand. You must accumulate at least 100 points to win the game. " +
                "Keep an eye on the clock. Time is short!" );
    }
    
    /**
     * 
     * @return scrubbleGame
     */
    public ScrubbleGame getGame()
    {
        return game;
    }
    
}
