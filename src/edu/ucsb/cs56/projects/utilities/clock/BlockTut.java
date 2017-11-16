package edu.ucsb.cs56.projects.utilities.clock;
import javax.swing.*;
import java.awt.*;


/**
 * Used by TimePanel to build BinaryClock's graphical representation.
 * This class was intended to be nearly identical to Block, but capable of
 * providing text labels on the blocks, for tutorial purposes. Currently bugged.
 * @@@ To Do:
 * @@@ - Make functional. Currently, it won't render at all.
 * @@@ - See Block.java.
 * @author Peter Bennion
 * @version for UCSB CS56, W12, choice points 2
*/
public class BlockTut extends JLabel
{
    protected boolean on;
    protected Color colorOn;
    protected Color colorOff;

    int x, y, w, h;

    /**
        Constructor
        @param s Text for label display
    */
    public BlockTut(String s)
    {
        super(s);

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

        this.colorOn = Color.red;
        this.colorOff = Color.blue;
        this.on = false;

        setBackground(colorOff);
        repaint();
    }

    /**
        Constructor
        @param on fill color when on
        @param off fill color when off
        @param s Text for label display
    */
    public BlockTut(Color on, Color off, String s)
    {
        super(s);

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

        this.colorOn = Color.red;
        this.colorOff = Color.blue;
        this.on = false;

        setBackground(colorOff);
        repaint();
    }

    /**
        Toggles the block's 'on' state. For testing purposes.
    */
    public void toggle()
    {
        on = !on;

        repaint();
    }

    /**
        Takes a char '1' or '0' and tells the block which state it should be in.
        @param c A char representing the desired state, '1' for on,
                 anything else ('0') for off.
    */
    public void input(char c)
    {
        if(c=='1')
            on = true;
        else on = false;

        repaint();
    }

    /**
        Used by the system to render the block. Currently bugged and non-functional
        @param g A variable representing graphics. Only available to the system.
    */
    public void paintComponent(Graphics g)
    {
        if(on)
            setBackground(colorOn);
        else setBackground(colorOff);
    }

}
