package info.gridworld.world;
import java.awt.Color;

/**
 *  Represents a tile of the specified color (colored block).
 *
 *  @author  George Peck
 *  @version Nov 25, 2007
 *
 *  @author Sources: Cay Horstmann
 */
public class Tile
{
    private Color color;

    /**
     * 
     * @param color of tile
     */
    public Tile( Color color )
    {
        this.color = color;
    }

    /**
     * 
     * @return color of tile
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * 
     * @param color sets color of tile
     */
    public void setColor( Color color )
    {
        this.color = color;
    }

    /**
     * 
     * @return string on tile
     */
    public String getText()
    {
        return "";
    }
}
