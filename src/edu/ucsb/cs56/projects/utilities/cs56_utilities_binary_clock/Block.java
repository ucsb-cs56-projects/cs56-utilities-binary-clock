package edu.ucsb.cs56.projects.utilities.cs56_utilities_binary_clock;
import javax.swing.*;
import java.awt.*;


/**
 * Used by TimePanel to build BinaryClock's graphical representation.
 * @@@ To Do:
 * @@@ - Fix draw bug. Currently, only background color will render.
 * @@@ - Making these blocks into Shapes, and drawing them directly on
 *          the TimePanel may help
 * @@@ - May want to lose the Canvas parent. Swing and awt sometimes
 *          have issues with each other.
 * @author Peter Bennion
 * @version for UCSB CS56, W12, choice points 2
*/
public class Block extends Canvas
{
    protected boolean on;
    protected Color colorOn;
    protected Color colorOff;

    int x, y, w, h;

    /**
        Constructor
    */
    public Block()
    {
        super();

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

        this.colorOn  = Color.red;
        this.colorOff = Color.blue;
        this.on = false;

        setBackground(colorOff);
        repaint();
    }

    /**
        Constructor
        @param on fill color when on
        @param off fill color when off
    */
    public Block(Color on, Color off)
    {
        super();

        x = getX();
        y = getY();
        w = getWidth();
        h = getHeight();

        this.colorOn = on;
        this.colorOff = off;
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

        if(on)
        {
            setBackground(colorOn);
        } else
        {
            setBackground(colorOff);
        }

        repaint();
    }

    /**
        Used by the system to render the block. Currently bugged and will only display background color.
        @param g A variable representing graphics. Only available to the system.
    */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        GradientPaint gradient;
        Color brushColor;

        if(on)
        {
            brushColor = colorOn;
            setBackground(colorOn);
        } else
        {
            brushColor = colorOff;
            setBackground(colorOff);
        }

        gradient = new GradientPaint(x + (w/2), y, brushColor, x + (w/2), y + h, Color.BLACK);

        g2d.setPaint(gradient);
        g2d.fillRect(x+5, y+5, w-10, h-10);
    }

}