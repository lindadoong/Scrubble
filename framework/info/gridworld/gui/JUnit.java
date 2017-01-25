package info.gridworld.gui;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import info.gridworld.actor.Tile;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.ScrubbleWorld;

public class JUnit
{
    ScrubblePlayer human;
    ScrubbleGame game;
    ScrubbleWorld world;
    WorldFrame frame;
    Grid<Tile> grid;
    
	/**
	 * Test if a game and all its elements are constructed
	 */
    @Before
    public void setUp()
    {
        game = new ScrubbleGame();
        world = new ScrubbleWorld( game );
        human = new ScrubblePlayer();
        frame = new WorldFrame( world );
        grid = frame.getGrid();
    }

	/**
	 * Test the constructor for ScrubblePlayer
	 */
    @Test
    public void testScrubblePlayerConstructor()
    {
        System.out.println( human );
        assertEquals( 0, human.getScore() );
    }
    
	/**
	 * Test if a hand can be reset
	 */
    @Test
    public void testScrubblePlayerNewHand()
    {
        assertEquals( 15, human.getTiles().size() );
    }
    
	/**
	 * Test if the ScrubblePlayer can set the newest score
	 */
    @Test
    public void testScrubblePlayerSetScore()
    {
        human.setScore( 5 );
        assertEquals( 5, human.getScore() );
    }
    
	/**
	 * Test if the ScrubblePlayer can be accessed
	 */
    @Test
    public void testScrubblePlayerGetPlayer()
    {
        assertEquals( human, human.getPlayer() );
    }
    
	/**
	 * Test in a ScrubbleGame can be constructed
	 */
    @Test
    public void testScrubbleGameConstructor()
    {
        assertTrue( game.getValidWords().size() > 0 );
        assertTrue( game.getPlayer() != null );
    }
    
	/**
	 * Check the validity of the words...in case of bullshit
	 */
    @Test
    public void testScrubbleGameCheckValidity()
    {
        assertTrue( game.checkValidity( "zyzzyvas" ));
        assertTrue( game.checkValidity( "mitochondria" ));
        assertFalse( game.checkValidity( "huehoehaoaheuaheeh" ));
    }
    
	/**
	 * Test the booleans for the Place and Exchange methods
	 */
    @Test
    public void testGUIControllerPlaceExchangeMethodsAndBooleans()
    {
    	GUIController gui = frame.getGUIController();
    	assertFalse( gui.getPlaced() );
    	assertFalse( gui.getExchanged() );
    	gui.place();
    	assertTrue( gui.getPlaced() );
    	gui.exchange();
    	assertTrue( gui.getExchanged() );
    	gui.done();
    	assertFalse( gui.getPlaced() );
    	assertFalse( gui.getExchanged() );
    }
    
	/**
	 * Calculate the score if there are no tiles placed on bonus squares
	 */
	@Test
	public void testCalculateScoreForNoBonus() {

		// System.err.println( "testCalculateScoreForNoBonus" );
		Tile tileA = new Tile('A');
		tileA.putSelfInGrid(grid, new Location(0, 4), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 5), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 6), tileA);
		// world.put(new Location( 0, 4 ), tileA);
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(0, 4));
		locs.add(new Location(0, 5));
		locs.add(new Location(0, 6));
		// System.err.println( "testCalculateScoreForNoBonus2" );
		// frame.calculateScore(locs);
		System.err.println(frame.calculateScore(locs));
		// frame.updateScore(frame.calculateScore(locs));
		// System.err.println(human.getScore());
		assertEquals(3, frame.getScore());

	}

	/**
	 * 
	 * Calculate the score if there is one tile placed on a double letter square
	 */
	@Test
	public void testCalculateScoreForDoubleLetterScore() {
		// System.err.println( "testCalculateScoreForNoBonus" );
		Tile tileA = new Tile('A');
		tileA.putSelfInGrid(grid, new Location(0, 4), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 5), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 3), tileA);
		// world.put(new Location( 0, 4 ), tileA);
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(0, 4));
		locs.add(new Location(0, 5));
		locs.add(new Location(0, 3));
		// System.err.println( "testCalculateScoreForNoBonus2" );
		// frame.calculateScore(locs);
		System.err.println(frame.calculateScore(locs));
		// frame.updateScore(frame.calculateScore(locs));
		// System.err.println(human.getScore());
		assertEquals(4, frame.getScore());

	}

	/**
	 * 
	 * Calculate the score if there is one tile placed on a triple letter square
	 */
	@Test
	public void testCalculateScoreForTripleLetterScore() {
		// System.err.println( "testCalculateScoreForNoBonus" );
		Tile tileA = new Tile('A');
		tileA.putSelfInGrid(grid, new Location(1, 4), tileA);
		tileA.putSelfInGrid(grid, new Location(1, 5), tileA);
		tileA.putSelfInGrid(grid, new Location(1, 3), tileA);
		// world.put(new Location( 0, 4 ), tileA);
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(1, 4));
		locs.add(new Location(1, 5));
		locs.add(new Location(1, 3));
		// System.err.println( "testCalculateScoreForNoBonus2" );
		// frame.calculateScore(locs);
		System.err.println(frame.calculateScore(locs));
		// frame.updateScore(frame.calculateScore(locs));
		// System.err.println(human.getScore());
		assertEquals(5, frame.getScore());

	}

	/**
	 * 
	 * Calculate the score if there is one tile placed on a double word square
	 */
	@Test
	public void testCalculateScoreForDoubleWordScore() {
		// System.err.println( "testCalculateScoreForNoBonus" );
		Tile tileA = new Tile('A');
		tileA.putSelfInGrid(grid, new Location(1, 0), tileA);
		tileA.putSelfInGrid(grid, new Location(1, 1), tileA);
		tileA.putSelfInGrid(grid, new Location(1, 2), tileA);
		// world.put(new Location( 0, 4 ), tileA);
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(1, 0));
		locs.add(new Location(1, 1));
		locs.add(new Location(1, 2));
		// System.err.println( "testCalculateScoreForNoBonus2" );
		// frame.calculateScore(locs);
		System.err.println(frame.calculateScore(locs));
		// frame.updateScore(frame.calculateScore(locs));
		// System.err.println(human.getScore());
		assertEquals(6, frame.getScore());

	}

	/**
	 * 
	 * Calculate the score if there is one tile placed on a triple word square
	 */
	@Test
	public void testCalculateScoreForTripleWordScore() {
		// System.err.println( "testCalculateScoreForNoBonus" );
		Tile tileA = new Tile('A');
		tileA.putSelfInGrid(grid, new Location(0, 0), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 1), tileA);
		tileA.putSelfInGrid(grid, new Location(0, 2), tileA);
		// world.put(new Location( 0, 4 ), tileA);
		ArrayList<Location> locs = new ArrayList<Location>();
		locs.add(new Location(0, 0));
		locs.add(new Location(0, 1));
		locs.add(new Location(0, 2));
		// System.err.println( "testCalculateScoreForNoBonus2" );
		// frame.calculateScore(locs);
		System.err.println(frame.calculateScore(locs));
		// frame.updateScore(frame.calculateScore(locs));
		// System.err.println(human.getScore());
		assertEquals(9, frame.getScore());

	}
}