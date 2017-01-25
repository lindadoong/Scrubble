package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Tile 
{
    private int value;

    private char c;
    
    public Tile constructedTile;

    int[] values = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1,
        1, 1, 4, 4, 8, 4, 10 };


    public Tile( char ch )
    {
        int index = Character.getNumericValue( ch ) - 10;
        value = values[ index ];
        c = ch;
    }

    public Tile constructTile( Tile t )
    {
    	char c = t.getChar();
    	if ( c == 'A' )
    	{
    		t = new TileA();
    	}
    	else if ( c == 'B' )
    	{
    		t = new TileB();
    	}
    	else if ( c == 'C' )
    	{
    		t = new TileC();
    	}
    	else if ( c == 'D' )
    	{
    		t = new TileD();
    	}
    	else if ( c == 'E' )
    	{
    		t = new TileE();
    	}
    	else if ( c == 'F' )
    	{
    		t = new TileF();
    	}
    	else if ( c == 'G' )
    	{
    		t = new TileG();
    	}
    	else if ( c == 'H' )
    	{
    		t = new TileH();
    	}
    	else if ( c == 'I')
    	{
    		t = new TileI();
    	}
    	else if ( c == 'J' )
    	{
    		t = new TileJ();
    	}
    	else if ( c == 'K' )
    	{
    		t = new TileK();
    	}
    	else if ( c == 'L' )
    	{
    		t = new TileL();
    	}
    	else if ( c == 'M' )
    	{
    		t = new TileM();
    	}
    	else if ( c == 'N' )
    	{
    		t = new TileN();
    	}
    	else if ( c == 'O' )
    	{
    		t = new TileO();
    	}
    	else if ( c == 'P' )
    	{
    		t = new TileP();
    	}
    	else if ( c == 'Q') 
    	{
    		t = new TileQ();
    	}
    	else if ( c == 'R' )
    	{
    		t = new TileR();
    	}
    	else if ( c == 'S' )
    	{
    		t = new TileS();
    	}
    	else if ( c == 'T' )
    	{
    		t = new TileT();
    	}
    	else if ( c == 'U' )
    	{
    		t = new TileU();
    	}
    	else if ( c == 'V' )
    	{
    		t = new TileV();
    	}
    	else if ( c == 'W' )
    	{
    		t = new TileW();
    	}
    	else if ( c == 'X' )
    	{
    		t = new TileX();
    	}
    	else if ( c == 'Y' )
    	{
    		t = new TileY();
    	}
    	else
    	{
    		t = new TileZ();
    	}
    	return t;
    }
    
    public char getChar()
    {
        return c;
    }


    public int getVal()
    {
        return value;
    }
    
    public void putSelfInGrid( Grid<Tile> gr, Location loc, Tile t )
    {
    	gr.put( loc, constructTile( t ) );
		//gr.put(loc, t);
    }
    
    public void removeSelfFromGrid( Grid<Tile> gr, Location loc )
    {
        gr.remove(loc);
    }
}
