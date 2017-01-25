/*
 * AP(r) Computer Science GridWorld Case Study: Copyright(c) 2005-2006 Cay S.
 * Horstmann (http://horstmann.com)
 * 
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */

package info.gridworld.gui;

import info.gridworld.actor.Actor;

import info.gridworld.actor.Tile;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * Makes the menus for constructing new occupants and grids, and for invoking
 * methods on existing occupants. <br />
 * This code is not tested on the AP CS A and AB exams. It contains GUI
 * implementation details that are not intended to be understood by AP CS
 * students.
 */
public class MenuMaker<T>
{
	/**
	 * ScrubblePlayer human
	 */
    ScrubblePlayer human;

    /**
     * word that will be used to see if user input word is valid
     */
    public static String word = "";

    /**
     * hand of tiles that human has
     */
    ArrayList<Tile> tiles;
    
    /**
     * arraylist of locations on which a tile has been placed
     */
    static ArrayList<Location> locs = new ArrayList<Location>();
    JPopupMenu menu;


    /**
     * Constructs a menu maker for a given world.
     * 
     * @param parent
     *            the frame in which the world is displayed
     * @param resources
     *            the resource bundle
     * @param displayMap
     *            the display map
     * @param h human ScrubblePlayer
     */
    public MenuMaker(
        WorldFrame<T> parent,
        ResourceBundle resources,
        DisplayMap displayMap,
        ScrubblePlayer h )
    {
        this.parent = parent;
        this.resources = resources;
        this.displayMap = displayMap;
        human = h;
        tiles = human.getTiles();
    }


    /**
     * Constructs a menu maker for a given world.
     * 
     * @param parent
     *            the frame in which the world is displayed
     * @param resources
     *            the resource bundle
     * @param displayMap
     *            the display map
     */
    public MenuMaker(
        WorldFrame<T> parent,
        ResourceBundle resources,
        DisplayMap displayMap )
    {
        this.parent = parent;
        this.resources = resources;
        this.displayMap = displayMap;
    }

    /**
     * Makes a menu that displays all public constructors of a collection of
     * classes.
     * @param loc
     *            the location of the occupant to be constructed
     * @return the menu to pop up
     */
    public JPopupMenu makeConstructorMenu( Location loc )
    {
        this.currentLocation = loc;
        menu = new JPopupMenu();

        int index = 0;
        boolean first = true;
        while ( index < tiles.size() )
        {
            if ( first )
            {
                first = false;
            }
            else
            {
                menu.addSeparator();
            }
            menu.add( new OccupantItem( (Tile)tiles.get( index ) ) );
            
            index++;
        }
        menu.addSeparator();
        menu.add( new RemoveItem( ));
        menu.addSeparator();
        menu.add( new UseItem() );
        return menu;
    }


    /**
     * Adds menu items that call all public constructors of a collection of
     * classes to a menu
     * 
     * @param menu
     *            the menu to which the items should be added
     * @param classes
     *            the collection of classes
     */
    public void addConstructors( JMenu menu, Collection<Class> classes )
    {
        boolean first = true;
        Iterator<Class> iter = classes.iterator();
        while ( iter.hasNext() )
        {
            if ( first )
                first = false;
            else
                menu.addSeparator();
            Class cl = iter.next();
            Constructor[] cons = cl.getConstructors();
            for ( int i = 0; i < cons.length; i++ )
            {
                menu.add( new GridConstructorItem( cons[i] ) );
            }
        }
    }

    /**
     * @return user inputed word
     */
    public String getWord()
    {
        return parent.getWord();
    }


    /**
     * A menu item that shows a method or constructor.
     */
    private class MCItem extends JMenuItem
    {
        public String getDisplayString(
            Class retType,
            String name,
            Class[] paramTypes )
        {
            StringBuffer b = new StringBuffer();
            b.append( "<html>" );
            if ( retType != null )
                appendTypeName( b, retType.getName() );
            b.append( " <font color='blue'>" );
            appendTypeName( b, name );
            b.append( "</font>( " );
            for ( int i = 0; i < paramTypes.length; i++ )
            {
                if ( i > 0 )
                    b.append( ", " );
                appendTypeName( b, paramTypes[i].getName() );
            }
            b.append( " )</html>" );
            return b.toString();
        }


        public void appendTypeName( StringBuffer b, String name )
        {
            int i = name.lastIndexOf( '.' );
            if ( i >= 0 )
            {
                String prefix = name.substring( 0, i + 1 );
                if ( !prefix.equals( "java.lang" ) )
                {
                    b.append( "<font color='gray'>" );
                    b.append( prefix );
                    b.append( "</font>" );
                }
                b.append( name.substring( i + 1 ) );
            }
            else
                b.append( name );
        }


        public Object makeDefaultValue( Class type )
        {
            if ( type == int.class )
                return new Integer( 0 );
            else if ( type == boolean.class )
                return Boolean.FALSE;
            else if ( type == double.class )
                return new Double( 0 );
            else if ( type == String.class )
                return "";
            else if ( type == Color.class )
                return Color.BLACK;
            else if ( type == Location.class )
                return currentLocation;
            else if ( Grid.class.isAssignableFrom( type ) )
                return currentGrid;
            else
            {
                try
                {
                    return type.newInstance();
                }
                catch ( Exception ex )
                {
                    return null;
                }
            }
        }
    }


    private abstract class ConstructorItem extends MCItem
    {
        public ConstructorItem( Constructor c )
        {
            setText( getDisplayString( null,
                c.getDeclaringClass().getName(),
                c.getParameterTypes() ) );
            this.c = c;
        }


        public Object invokeConstructor()
        {
            Class[] types = c.getParameterTypes();
            Object[] values = new Object[types.length];

            for ( int i = 0; i < types.length; i++ )
            {
                values[i] = makeDefaultValue( types[i] );
            }

            if ( types.length > 0 )
            {
                PropertySheet sheet = new PropertySheet( types, values );
                JOptionPane.showMessageDialog( this,
                    sheet,
                    resources.getString( "dialog.method.params" ),
                    JOptionPane.QUESTION_MESSAGE );
                values = sheet.getValues();
            }

            try
            {
                return c.newInstance( values );
            }
            catch ( InvocationTargetException ex )
            {
                parent.new GUIExceptionHandler().handle( ex.getCause() );
                return null;
            }
            catch ( Exception ex )
            {
                parent.new GUIExceptionHandler().handle( ex );
                return null;
            }
        }

        private Constructor c;
    }

    private class OccupantItem extends MCItem implements ActionListener
    {
        Tile my;

        /**
         * creates a new occupantItem of tiles
         * @param tile
         */
        public OccupantItem( Tile tile )
        {
            setText( "Place '" + tile.getChar() + "'" );
            addActionListener( this );
            // setIcon(displayMap.getIcon(c.getDeclaringClass(), 16, 16));
            my = tile;
        }

        /**
         * adds tile to the grid 
         */
        @SuppressWarnings("unchecked")
        public void actionPerformed( ActionEvent event )
        {
            parent.setWord( parent.getWord() + my.getChar() );

            locs.add( currentLocation );
            if ( parent.getWorld().getGrid().get( currentLocation ) == null )
            {
            	my.putSelfInGrid( (Grid<Tile>) parent.getWorld().getGrid(), currentLocation, my );
                for ( Tile t: tiles )
                {
                	if ( t.getChar() == my.getChar() )
                	{
                		tiles.remove( t );
                		break;
                	}
                }
                makeConstructorMenu( currentLocation );
            }
            else
            {
            	parent.getWorld().setMessage( "You cannot move tiles that have already been placed.");
            }
            parent.repaint();
        }
    }


    private class GridConstructorItem extends ConstructorItem
                    implements
                    ActionListener
    {
        public GridConstructorItem( Constructor c )
        {
            super( c );
            addActionListener( this );
            setIcon( displayMap.getIcon( c.getDeclaringClass(), 16, 16 ) );
        }


        @SuppressWarnings("unchecked")
        public void actionPerformed( ActionEvent event )
        {
            Grid<T> newGrid = (Grid<T>)invokeConstructor();
            parent.setGrid( newGrid );
        }
    }
    
    private class UseItem extends MCItem implements ActionListener
    {
    	public UseItem()
    	{
    		setText("Use tile");
    		addActionListener( this );
    	}
    	
    	public void actionPerformed( ActionEvent evt )
    	{
    		Tile t = (Tile) parent.getWorld().getGrid().get( currentLocation );
    		locs.add( currentLocation );
    		parent.setWord( parent.getWord() + t.getChar() );
    		parent.setConnected( true );
    	}
    }

    private class RemoveItem extends MCItem implements ActionListener
    {
        public RemoveItem()
        {
        	setText("Remove tile");
            addActionListener( this );
        }
        
    	@Override
    	public void actionPerformed(ActionEvent arg0) 
    	{
    		if ( parent.getWord().length() > 1 )
    		{
    			parent.setWord( parent.getWord().substring( 0, parent.getWord().length() - 1));
    		}
    		Tile old = (Tile)parent.getGrid().get( currentLocation );
    		tiles.add( new Tile( old.getChar() ));
    		makeConstructorMenu( currentLocation );
    		parent.getWorld().remove( currentLocation );
    		((Tile) parent.getGrid().get( currentLocation )).removeSelfFromGrid( (Grid<Tile>) parent.getGrid(), currentLocation );
    		locs.remove( locs.size() - 1 );
    		parent.repaint();
    	}
    }

    private T occupant;

    private Grid<T> currentGrid;

    private Location currentLocation;

    private WorldFrame<T> parent;

    private DisplayMap displayMap;

    private ResourceBundle resources;
}


class PropertySheet extends JPanel
{
    /**
     * Constructs a property sheet that shows the editable properties of a given
     * object.
     *
     */
    public PropertySheet( Class[] types, Object[] values )
    {
        this.values = values;
        editors = new PropertyEditor[types.length];
        setLayout( new FormLayout() );
        for ( int i = 0; i < values.length; i++ )
        {
            JLabel label = new JLabel( types[i].getName() );
            add( label );
            if ( Grid.class.isAssignableFrom( types[i] ) )
            {
                label.setEnabled( false );
                add( new JPanel() );
            }
            else
            {
                editors[i] = getEditor( types[i] );
                if ( editors[i] != null )
                {
                    editors[i].setValue( values[i] );
                    add( getEditorComponent( editors[i] ) );
                }
                else
                    add( new JLabel( "?" ) );
            }
        }
    }


    /**
     * Gets the property editor for a given property, and wires it so that it
     * updates the given object.
     *            the descriptor of the property to be edited
     * @return a property editor that edits the property with the given
     *         descriptor and updates the given object
     */
    public PropertyEditor getEditor( Class type )
    {
        PropertyEditor editor;
        editor = defaultEditors.get( type );
        if ( editor != null )
            return editor;
        editor = PropertyEditorManager.findEditor( type );
        return editor;
    }


    /**
     * Wraps a property editor into a component.
     * 
     * @param editor
     *            the editor to wrap
     * @return a button (if there is a custom editor), combo box (if the editor
     *         has tags), or text field (otherwise)
     */
    public Component getEditorComponent( final PropertyEditor editor )
    {
        String[] tags = editor.getTags();
        String text = editor.getAsText();
        if ( editor.supportsCustomEditor() )
        {
            return editor.getCustomEditor();
        }
        else if ( tags != null )
        {
            // make a combo box that shows all tags
            final JComboBox comboBox = new JComboBox( tags );
            comboBox.setSelectedItem( text );
            comboBox.addItemListener( new ItemListener()
            {
                public void itemStateChanged( ItemEvent event )
                {
                    if ( event.getStateChange() == ItemEvent.SELECTED )
                        editor.setAsText( (String)comboBox.getSelectedItem() );
                }
            } );
            return comboBox;
        }
        else
        {
            final JTextField textField = new JTextField( text, 10 );
            textField.getDocument().addDocumentListener( new DocumentListener()
            {
                public void insertUpdate( DocumentEvent e )
                {
                    try
                    {
                        editor.setAsText( textField.getText() );
                    }
                    catch ( IllegalArgumentException exception )
                    {
                    }
                }


                public void removeUpdate( DocumentEvent e )
                {
                    try
                    {
                        editor.setAsText( textField.getText() );
                    }
                    catch ( IllegalArgumentException exception )
                    {
                    }
                }


                public void changedUpdate( DocumentEvent e )
                {
                }
            } );
            return textField;
        }
    }


    public Object[] getValues()
    {
        for ( int i = 0; i < editors.length; i++ )
            if ( editors[i] != null )
                values[i] = editors[i].getValue();
        return values;
    }

    private PropertyEditor[] editors;

    private Object[] values;

    private static Map<Class, PropertyEditor> defaultEditors;


    // workaround for Web Start bug
    public static class StringEditor extends PropertyEditorSupport
    {
        public String getAsText()
        {
            return (String)getValue();
        }


        public void setAsText( String s )
        {
            setValue( s );
        }
    }

    static
    {
        defaultEditors = new HashMap<Class, PropertyEditor>();
        defaultEditors.put( String.class, new StringEditor() );
        defaultEditors.put( Location.class, new LocationEditor() );
        defaultEditors.put( Color.class, new ColorEditor() );
    }
}
